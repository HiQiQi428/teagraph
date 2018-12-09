package org.luncert.tkgdb;

import com.alibaba.fastjson.JSON;

import lombok.Data;

@Data
public class TaskResult {

    /**
     * 组内id
     */
    private int taskId;

    /**
     * 所在组id
     */
    private int groupId;

    private String content;

    public TaskResult(int taskId, int groupId, String content) {
        this.taskId = taskId;
        this.groupId = groupId;
        this.content = content;
    }

    public String toJSONString() {
        return JSON.toJSONString(this);
    }

    public static TaskResult fromJSONString(String raw) {
        return JSON.parseObject(raw, TaskResult.class);
    }

}