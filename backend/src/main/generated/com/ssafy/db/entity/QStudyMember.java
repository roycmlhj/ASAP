package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudyMember is a Querydsl query type for StudyMember
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudyMember extends EntityPathBase<StudyMember> {

    private static final long serialVersionUID = -1328037695L;

    public static final QStudyMember studyMember = new QStudyMember("studyMember");

    public final NumberPath<Integer> isJoin = createNumber("isJoin", Integer.class);

    public final NumberPath<Integer> position = createNumber("position", Integer.class);

    public final NumberPath<Integer> studyno = createNumber("studyno", Integer.class);

    public final StringPath studyTime = createString("studyTime");

    public final NumberPath<Integer> userno = createNumber("userno", Integer.class);

    public QStudyMember(String variable) {
        super(StudyMember.class, forVariable(variable));
    }

    public QStudyMember(Path<? extends StudyMember> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudyMember(PathMetadata metadata) {
        super(StudyMember.class, metadata);
    }

}

