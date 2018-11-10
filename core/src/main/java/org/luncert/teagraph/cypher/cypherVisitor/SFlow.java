package org.luncert.teagraph.cypher.cypherVisitor;

import java.util.ArrayList;
import java.util.List;

public class SFlow extends Step {

    private static class Pair {

        String n;
        Step step;
        Pair(String n, Step step) {
            this.n = n;
            this.step = step;
        }

    }

    // Piece -> n
    List<Pair> pieces = new ArrayList<>();

    public SFlow addStep(Step step, String n) {
        pieces.add(new Pair(n, step));
        return this;
    }

    public <T> T handle(ParseTreeList list, T data) {
        Pair cur = null;
        boolean processed = false;
        int i = 0, limit = pieces.size();
        while (i < limit && list.avaliable()) {
            cur = pieces.get(i);
            if (cur.step.handle(list, data) != null) {
                if ("*".equals(cur.n) || "+".equals(cur.n))
                    processed = true;
                else
                    i++;
                list.forward();
            }
            // 匹配失败,判断是否处理过
            else if (processed) {
                processed = false;
                i++;
            }
            // 判断是否可以跳过
            else if ("*".equals(cur.n) || "?".equals(cur.n))
                i++;
            else
                return null;
        }
        // list没有处理完,检查最后一个step是否可以处理多个
        if (list.avaliable()) {
            if ("+".equals(cur.n)) {
                while (cur.step.handle(list, data) != null)
                    list.forward();
            }
            return data;
        }
        // list处理完了,还剩下一个可以忽略的step
        else if (i == limit - 1 && "?".equals((cur = pieces.get(i)).n))
            return data;
        // 还剩下很多个step,说明处理失败了
        else
            return null;
    }

}