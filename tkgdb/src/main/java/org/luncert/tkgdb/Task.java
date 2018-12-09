package org.luncert.tkgdb;

import com.alibaba.fastjson.JSON;

import lombok.Data;

@Data
public class Task {

    private int taskId;
    private int groupId;
    private CPiece piece;

    public Task(int taskId, int groupId, CPiece piece) {
        this.taskId = taskId;
        this.groupId = groupId;
        this.piece = piece;
    }

    public static Task fromJSONString(String raw) {
        return JSON.parseObject(raw, Task.class);
    }

}