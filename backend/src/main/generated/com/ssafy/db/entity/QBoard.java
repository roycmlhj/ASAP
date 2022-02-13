package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = 455343204L;

    public static final QBoard board = new QBoard("board");

    public final StringPath boarddescription = createString("boarddescription");

    public final StringPath boardname = createString("boardname");

    public final NumberPath<Integer> boardno = createNumber("boardno", Integer.class);

    public final StringPath category = createString("category");

    public final StringPath contactlink = createString("contactlink");

    public final NumberPath<Integer> hit = createNumber("hit", Integer.class);

    public final StringPath interests = createString("interests");

    public final NumberPath<Integer> isRecruit = createNumber("isRecruit", Integer.class);

    public final StringPath link = createString("link");

    public final NumberPath<Integer> maxmember = createNumber("maxmember", Integer.class);

    public final StringPath nickname = createString("nickname");

    public final NumberPath<Integer> studyno = createNumber("studyno", Integer.class);

    public final StringPath timestamp = createString("timestamp");

    public final NumberPath<Integer> userno = createNumber("userno", Integer.class);

    public QBoard(String variable) {
        super(Board.class, forVariable(variable));
    }

    public QBoard(Path<? extends Board> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoard(PathMetadata metadata) {
        super(Board.class, metadata);
    }

}

