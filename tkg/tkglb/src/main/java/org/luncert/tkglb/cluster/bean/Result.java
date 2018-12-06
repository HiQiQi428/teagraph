package org.luncert.tkglb.cluster.bean;

import lombok.Getter;

@Getter
public class Result {

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