package org.luncert.teagraph.cypher.cypherVisitor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTree;

public class CVFlow extends CVStep {

    Map<CVPiece, String> pieces = new HashMap<>();

    public CVFlow addStep(CVPiece piece, String n) {
        pieces.put(piece, n);
        return this;
    }

    public int handle(ParseTree tree, int i) {
        Iterator<Map.Entry<CVPiece,String>> iterator = pieces.entrySet().iterator();
        Map.Entry<CVPiece,String> pre = null, cur;
        if (iterator.hasNext()) {
            cur = iterator.next();
            while (iterator.hasNext()) {
                if (pre != null
                    && ("*".equals(pre.getValue())
                    || "+".equals(pre.getValue()))
                    && pre.getKey().handle(tree.getChild(i)))
                {
                    i++;
                }
                else if (cur.getKey().handle(tree.getChild(i))) {
                    i++;
                    pre = cur;
                    cur = iterator.next();
                }
                else if ("?".equals(cur.getValue())
                    || "*".equals(cur.getValue()))
                {
                    // cur模式匹配已经失败,*和?表示是可以忽略的模式,直接跳过获取下一个模式,并吧多重性设置为不可匹配
                    cur.setValue(null);
                    pre = cur;
                    cur = iterator.next();
                }
                else return i;
            }
        }
        return i;
    }

}