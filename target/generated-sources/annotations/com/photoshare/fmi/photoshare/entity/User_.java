package com.photoshare.fmi.photoshare.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, Integer> id;
	public static volatile SingularAttribute<User, String> lastName;
	public static volatile SingularAttribute<User, String> username;
	public static volatile SingularAttribute<User, String> email;
	public static volatile CollectionAttribute<User, Collection> collectionCollection;
	public static volatile SingularAttribute<User, String> avatar;
	public static volatile SingularAttribute<User, String> firstName;
	public static volatile SingularAttribute<User, String> password;
	public static volatile CollectionAttribute<User, Image> imageCollection;

}

