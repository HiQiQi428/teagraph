package org.luncert.tkgdb.bean;

import lombok.Data;

@Data
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