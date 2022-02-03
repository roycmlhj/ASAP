package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserHomework is a Querydsl query type for UserHomework
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserHomework extends EntityPathBase<UserHomework> {

    private static final long serialVersionUID = -310382051L;

    public static final QUserHomework userHomework = new QUserHomework("userHomework");

    public final StringPath filedata = createString("filedata");

    public final StringPath filename = createString("filename");

    public final NumberPath<Integer> homeworkno = createNumber("homeworkno", Integer.class);

    public final NumberPath<Integer> isDone = createNumber("isDone", Integer.class);

    public final NumberPath<Integer> userhomeworkno = createNumber("userhomeworkno", Integer.class);

    public final NumberPath<Integer> userno = createNumber("userno", Integer.class);

    public QUserHomework(String variable) {
        super(UserHomework.class, forVariable(variable));
    }

    public QUserHomework(Path<? extends UserHomework> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserHomework(PathMetadata metadata) {
        super(UserHomework.class, metadata);
    }

}

