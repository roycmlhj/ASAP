package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 846542477L;

    public static final QUser user = new QUser("user");

    public final NumberPath<Integer> delFlag = createNumber("delFlag", Integer.class);

    public final StringPath email = createString("email");

    public final NumberPath<Integer> exp = createNumber("exp", Integer.class);

    public final StringPath interests = createString("interests");

    public final NumberPath<Integer> isAdmin = createNumber("isAdmin", Integer.class);

    public final NumberPath<Integer> level = createNumber("level", Integer.class);

    public final StringPath mainCategory = createString("mainCategory");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath regidate = createString("regidate");

    public final NumberPath<Integer> userno = createNumber("userno", Integer.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

