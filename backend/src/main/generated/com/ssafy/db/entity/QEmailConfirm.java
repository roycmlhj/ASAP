package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEmailConfirm is a Querydsl query type for EmailConfirm
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmailConfirm extends EntityPathBase<EmailConfirm> {

    private static final long serialVersionUID = 1970574406L;

    public static final QEmailConfirm emailConfirm = new QEmailConfirm("emailConfirm");

    public final StringPath code = createString("code");

    public final StringPath email = createString("email");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath timestamp = createString("timestamp");

    public QEmailConfirm(String variable) {
        super(EmailConfirm.class, forVariable(variable));
    }

    public QEmailConfirm(Path<? extends EmailConfirm> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmailConfirm(PathMetadata metadata) {
        super(EmailConfirm.class, metadata);
    }

}

