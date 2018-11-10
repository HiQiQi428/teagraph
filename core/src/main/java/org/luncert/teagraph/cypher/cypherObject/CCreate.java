package org.luncert.teagraph.cypher.cypherObject;

import org.luncert.teagraph.cypher.cypherObject.nonpiece.CPattern;

import lombok.Data;

@Data
public class CCreate extends UpdatingClause {

    CPattern pattern;

}