package org.luncert.tkglb.cluster.bean;

import com.alibaba.fastjson.JSON;

import org.luncert.tkglb.cypher.CPiece;

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

    public String toJSONString() {
        return JSON.toJSONString(this);
    }

}