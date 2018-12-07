package org.luncert.tkglb.cluster.bean;

import org.luncert.tkglb.cypher.CPiece;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Task {

    private int taskId;
    private int groupId;
    private CPiece piece;

    public Task(int taskId, int groupId, CPiece piece) {
        this.taskId = taskId;
        this.groupId = groupId;
        this.piece = piece;
    }

    public String toPropsString() {
        StringBuilder builder = new StringBuilder();
        builder.append("taskId=").append(taskId).append('\n')
                .append("groupId=").append(groupId).append('\n')
                .append("query=").append(piece.getContent());
        return builder.toString();
    }

}