package org.luncert.tkglb.cluster.bean;

import org.luncert.tkglb.cypher.CPiece.PieceType;
import org.luncert.tkglb.datastruct.BiHeap;
import org.luncert.tkglb.datastruct.BiHeap.Node;

import io.netty.channel.Channel;
import lombok.Data;

/**
 * 保存neo4j节点信息
 */
@Data
public class DBNode extends BiHeap.Node {

    private final String id;

    private volatile int readTime = 0;

    // 当前状态
    private volatile NodeStatus status = NodeStatus.Ready;

    private final Channel channel;

    public DBNode(Channel channel) {
        this.id = channel.id().asLongText();
        this.channel = channel;
    }

    /**
     * 更新节点状态
     */
    private void changeStatus(NodeStatus status) {
        this.status = status;
    }

    /**
     * 执行任务
     * @param task
     */
    public void execute(Task task) {
        PieceType pieceType = task.getPiece().getPieceType();
        if (pieceType == PieceType.Update)
            changeStatus(NodeStatus.Updating);
        else {
            changeStatus(NodeStatus.Reading);
            readTime++;
        }
        /*
        与数据库节点的通信使用JSON进行传输，因为
        直接使用Marshalling进行传输报错了，之前
        还没解决这个问题，暂时先用的JSON传输
        */        
        channel.writeAndFlush(task.toJSONString());
    }

    /**
     * 更新节点状态为ready
     */
    public void executeFinished() {
        changeStatus(NodeStatus.Ready);
    }

    /**
     * 更新节点状态为invaliable
     */
    public void disconnected() {
        changeStatus(NodeStatus.Invaliable);
    }

    @Override
    public int compare(Node node) {
        DBNode n = (DBNode) node;
        return readTime < n.getReadTime() ? -1
            : (readTime == n.getReadTime() ? 0 : 1);
	}
    
}