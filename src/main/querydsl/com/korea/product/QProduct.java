package com.korea.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 364753119L;

    public static final QProduct product = new QProduct("product");

    public final StringPath category = createString("category");

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final StringPath padmin = createString("padmin");

    public final StringPath pdesc = createString("pdesc");

    public final StringPath pid = createString("pid");

    public final StringPath pimgname = createString("pimgname");

    public final StringPath pname = createString("pname");

    public final NumberPath<Integer> pprice = createNumber("pprice", Integer.class);

    public final NumberPath<Integer> pquantity = createNumber("pquantity", Integer.class);

    public final StringPath today = createString("today");

    public QProduct(String variable) {
        super(Product.class, forVariable(variable));
    }

    public QProduct(Path<? extends Product> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduct(PathMetadata metadata) {
        super(Product.class, metadata);
    }

}

