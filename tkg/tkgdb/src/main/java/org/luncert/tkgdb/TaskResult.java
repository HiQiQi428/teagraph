package org.luncert.tkgdb;

import lombok.Getter;

@Getter
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

    public TaskResult(Task task, String content) {
        taskId = task.getTaskId();
        groupId = task.getGroupId();
        this.content = content;
    }

}