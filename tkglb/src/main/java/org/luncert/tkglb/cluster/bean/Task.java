package org.luncert.tkglb.cluster.bean;

import com.alibaba.fastjson.JSON;

import org.luncert.tkglb.cypher.CPiece;

import lombok.Data;

/**
 * 描述任务信息
 */
@Data
public class Task {

    // 任务id，组内唯一，全局不唯一
    private int taskId;

    // 所在组id
    private int groupId;

    // 任务payload
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