package org.luncert.tkglb.cluster.bean;

import org.luncert.tkglb.cypher.CPiece.PieceType;

import io.netty.channel.Channel;
import lombok.Data;

/**
 * 保存neo4j节点信息
 */
@Data
public class DBNode {

    private final String id;

    private volatile int readTime = 0;

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

    public void execute(Task task) {
        PieceType pieceType = task.getPiece().getPieceType();
        if (pieceType == PieceType.Update)
            changeStatus(NodeStatus.Updating);
        else {
            changeStatus(NodeStatus.Reading);
            readTime++;
        }
        channel.writeAndFlush(task.toPropsString());
    }

    public void executeFinished() {
        changeStatus(NodeStatus.Ready);
    }

    public void disconnected() {
        changeStatus(NodeStatus.Invaliable);
    }
    
}