package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyBoard is a Querydsl query type for StudyBoard
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudyBoard extends EntityPathBase<StudyBoard> {

    private static final long serialVersionUID = -1853827105L;

    public static final QStudyBoard studyBoard = new QStudyBoard("studyBoard");

    public final NumberPath<Integer> boardno = createNumber("boardno", Integer.class);

    public final StringPath content = createString("content");

    public final ListPath<FileData, QFileData> fileList = this.<FileData, QFileData>createList("fileList", FileData.class, QFileData.class, PathInits.DIRECT2);

    public final NumberPath<Integer> studyno = createNumber("studyno", Integer.class);

    public final StringPath timestamp = createString("timestamp");

    public final StringPath title = createString("title");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public final NumberPath<Integer> userno = createNumber("userno", Integer.class);

    public QStudyBoard(String variable) {
        super(StudyBoard.class, forVariable(variable));
    }

    public QStudyBoard(Path<? extends StudyBoard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudyBoard(PathMetadata metadata) {
        super(StudyBoard.class, metadata);
    }

}

