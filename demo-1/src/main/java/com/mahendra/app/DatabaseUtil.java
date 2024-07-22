package com.mahendra.app;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DatabaseUtil {
	private EntityManagerFactory factory = null;
	private static DatabaseUtil instance = new DatabaseUtil();
	
	private DatabaseUtil() {
		/**
		 * Three possibilities for exception
		 * 1. META-INF/persistence.xml is Missing or the name does not match
		 * 2. persistance.xml has XML Validation errors
		 * 3. Unit name "PU1" does not exists or do not match with persistence.xml file
		 */
		 factory = Persistence.createEntityManagerFactory("PU1");
	}
	
	public static EntityManagerFactory getFactory() {
		return instance.factory;
	}
}
