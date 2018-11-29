package org.luncert.tkgdb;

import lombok.Getter;

@Getter
public class Task {

    private int taskId;
    private int groupId;
    private CPiece piece;

    public Task(int taskId, int groupId, CPiece piece) {
        this.taskId = taskId;
        this.groupId = groupId;
        this.piece = piece;
    }

}