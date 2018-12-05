package org.luncert.tkglb.cypher;

import lombok.Builder;
import lombok.Data;

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