package org.luncert.teagraph.cypher.cypherObject;

import java.util.List;

import org.luncert.teagraph.cypher.cypherObject.nonpiece.CExpression;

import lombok.Data;

@Data
public class CDelete extends UpdatingClause {

    boolean detach;
    List<CExpression> expressions;

}