package org.luncert.tkglb.cypher;

import lombok.Builder;
import lombok.Data;

/**
 * 存储从Cypher AST中解析出的一条SingleQuery
 */
@Data
@Builder
public class CPiece {

    public static enum PieceType {
        Read, Update
    }

    public static enum UnionType {
        None, Union, UnionAll
    }

    private PieceType pieceType;
    private UnionType unionType;
    private String content;

}