package com.photoshare.fmi.photoshare.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Image.class)
public abstract class Image_ {

	public static volatile SingularAttribute<Image, Integer> id;
	public static volatile SingularAttribute<Image, String> description;
	public static volatile SingularAttribute<Image, User> userId;
	public static volatile SingularAttribute<Image, String> fileName;
	public static volatile CollectionAttribute<Image, Collection> collectionCollection;
	public static volatile CollectionAttribute<Image, Category> categoryCollection;

}

