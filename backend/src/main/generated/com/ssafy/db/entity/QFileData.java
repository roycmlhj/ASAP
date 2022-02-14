package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFileData is a Querydsl query type for FileData
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFileData extends EntityPathBase<FileData> {

    private static final long serialVersionUID = -724019960L;

    public static final QFileData fileData = new QFileData("fileData");

    public final NumberPath<Integer> boardno = createNumber("boardno", Integer.class);

    public final StringPath filename = createString("filename");

    public final NumberPath<Integer> fileno = createNumber("fileno", Integer.class);

    public final StringPath filepath = createString("filepath");

    public final StringPath ogfilename = createString("ogfilename");

    public QFileData(String variable) {
        super(FileData.class, forVariable(variable));
    }

    public QFileData(Path<? extends FileData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFileData(PathMetadata metadata) {
        super(FileData.class, metadata);
    }

}

