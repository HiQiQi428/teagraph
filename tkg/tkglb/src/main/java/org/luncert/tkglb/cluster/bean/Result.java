package org.luncert.tkglb.cluster.bean;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
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

    public String toPropsString() {
        StringBuilder builder = new StringBuilder();
        builder.append("taskId=").append(taskId).append('\n')
                .append("groupId=").append(groupId).append('\n')
                .append("content=").append(content);
        return builder.toString();
    }

    public static Result fromPropsString(String raw) throws IOException {
        Result result = new Result();
        Properties props = new Properties();
        props.load(new StringReader(raw));
        props.forEach((key, value) -> {
            if (key.equals("taskId"))
                result.taskId = Integer.valueOf((String) value);
            else if (key.equals("groupId"))
                result.groupId = Integer.valueOf((String) value);
            else
                result.content = (String) value;
        });
        return result;
    }

}