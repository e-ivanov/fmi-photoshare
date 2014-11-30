package com.photoshare.fmi.photoshare.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Collection.class)
public abstract class Collection_ {

	public static volatile SingularAttribute<Collection, Integer> id;
	public static volatile SingularAttribute<Collection, User> userId;
	public static volatile SingularAttribute<Collection, String> name;
	public static volatile CollectionAttribute<Collection, Image> imageCollection;

}

