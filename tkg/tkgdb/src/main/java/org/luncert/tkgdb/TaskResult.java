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

    public TaskResult(int taskId, int groupId, String content) {
        this.taskId = taskId;
        this.groupId = groupId;
        this.content = content;
    }
    
    public String toPropsString() {
        StringBuilder builder = new StringBuilder();
        builder.append("taskId=").append(taskId).append('\n')
                .append("groupId=").append(groupId).append('\n')
                .append("content=").append(content);
        return builder.toString();
    }

}