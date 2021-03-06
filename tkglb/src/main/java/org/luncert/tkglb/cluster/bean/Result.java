package org.luncert.tkglb.cluster.bean;

import com.alibaba.fastjson.JSON;

import lombok.Data;

/**
 * 描述任务执行结果
 */
@Data
public class Result {

    /**
     * 组内id
     */
    private int taskId;

    /**
     * 所在组id
     */
    private int groupId;

    /**
     * payload
     */
    private String content;

    public String toJSONString() {
        return JSON.toJSONString(this);
    }

    public static Result fromJSONString(String raw) {
        return JSON.parseObject(raw, Result.class);
    }

}