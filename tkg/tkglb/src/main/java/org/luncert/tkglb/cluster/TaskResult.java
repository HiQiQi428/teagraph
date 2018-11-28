package org.luncert.tkglb.cluster;

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

}