package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudy is a Querydsl query type for Study
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudy extends EntityPathBase<Study> {

    private static final long serialVersionUID = 471210823L;

    public static final QStudy study = new QStudy("study");

    public final ListPath<Board, QBoard> boardlist = this.<Board, QBoard>createList("boardlist", Board.class, QBoard.class, PathInits.DIRECT2);

    public final StringPath category = createString("category");

    public final StringPath description = createString("description");

    public final ListPath<Homework, QHomework> homeworkList = this.<Homework, QHomework>createList("homeworkList", Homework.class, QHomework.class, PathInits.DIRECT2);

    public final StringPath interests = createString("interests");

    public final StringPath maker = createString("maker");

    public final NumberPath<Integer> memberno = createNumber("memberno", Integer.class);

    public final ListPath<Schedule, QSchedule> scheduleList = this.<Schedule, QSchedule>createList("scheduleList", Schedule.class, QSchedule.class, PathInits.DIRECT2);

    public final ListPath<StudyBoard, QStudyBoard> studyBoardList = this.<StudyBoard, QStudyBoard>createList("studyBoardList", StudyBoard.class, QStudyBoard.class, PathInits.DIRECT2);

    public final ListPath<StudyMember, QStudyMember> studyMemberList = this.<StudyMember, QStudyMember>createList("studyMemberList", StudyMember.class, QStudyMember.class, PathInits.DIRECT2);

    public final StringPath studyname = createString("studyname");

    public final NumberPath<Integer> studyno = createNumber("studyno", Integer.class);

    public final StringPath timestamp = createString("timestamp");

    public QStudy(String variable) {
        super(Study.class, forVariable(variable));
    }

    public QStudy(Path<? extends Study> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudy(PathMetadata metadata) {
        super(Study.class, metadata);
    }

}

