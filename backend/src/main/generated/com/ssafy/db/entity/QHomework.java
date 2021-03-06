package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHomework is a Querydsl query type for Homework
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHomework extends EntityPathBase<Homework> {

    private static final long serialVersionUID = -473149902L;

    public static final QHomework homework = new QHomework("homework");

    public final StringPath content = createString("content");

    public final StringPath endDate = createString("endDate");

    public final NumberPath<Integer> homeworkno = createNumber("homeworkno", Integer.class);

    public final NumberPath<Integer> isActive = createNumber("isActive", Integer.class);

    public final StringPath startDate = createString("startDate");

    public final NumberPath<Integer> studyno = createNumber("studyno", Integer.class);

    public final StringPath title = createString("title");

    public final ListPath<UserHomework, QUserHomework> userHomeworkList = this.<UserHomework, QUserHomework>createList("userHomeworkList", UserHomework.class, QUserHomework.class, PathInits.DIRECT2);

    public final NumberPath<Integer> userno = createNumber("userno", Integer.class);

    public QHomework(String variable) {
        super(Homework.class, forVariable(variable));
    }

    public QHomework(Path<? extends Homework> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHomework(PathMetadata metadata) {
        super(Homework.class, metadata);
    }

}

