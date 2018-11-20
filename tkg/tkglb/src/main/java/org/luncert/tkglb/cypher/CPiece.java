package org.luncert.tkglb.cypher;

import lombok.Data;

@Data
public class CPiece {

    public static enum PieceType {
        Read, Update
    }

    public static enum UnionType {
        None, Union, UnionAll
    }

    private PieceType pieceType = PieceType.Read;
    private UnionType unionType = UnionType.None;
    private String content;

}