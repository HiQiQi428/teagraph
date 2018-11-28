package org.luncert.tkglb.cluster;

import org.luncert.tkglb.cypher.CPiece.PieceType;

import io.netty.channel.Channel;
import lombok.Data;

/**
 * 保存neo4j节点信息
 */
@Data
public class DBNode {

    public static enum NodeStatus {
        Ready, Reading, Updating, Invaliable
    }

    /**
     * 读次数
     */
    private int readTime;

    private NodeStatus status;

    private Channel channel;

    public DBNode(Channel channel) {
        this.readTime = 0;
        this.status = NodeStatus.Ready;
        this.channel = channel;
    }

    public void execute(Task task) {
        PieceType pieceType = task.getPiece().getPieceType();
        if (pieceType == PieceType.Update) {
            status = NodeStatus.Updating;
        }
        else {
            status = NodeStatus.Reading;
            readTime++;
        }
        channel.writeAndFlush(task);
    }

    /**
     * 设置节点状态为Ready
     */
    public void changeStatus(NodeStatus status) {
        this.status = status;
    }
    
}