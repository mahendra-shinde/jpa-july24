package com.mahendra.app;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import com.mahendra.model.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;

public class Main3 {

	/**
	 * Insert Operation Demo 
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Transient Object
		Employee emp1 = new Employee(110089, fromString("01/01/1990") ,"Joe", "Biden", "M", fromString("10/3/2022"));
		
		EntityTransaction tn = null;
		
		EntityManager em = DatabaseUtil.getFactory().createEntityManager();
		
		try {
		tn = em.getTransaction();
		tn.begin();	// Start the transaction
		em.persist(emp1);	// "persist" method required an OPEN transaction
		tn.commit();		// Save the changes
		}catch(PersistenceException ex)
		{
			if(tn != null ) {
				tn.rollback(); // Undo the changes
			}
		}
		System.out.println("Employee "+emp1.getFirstName());
	}
	
	private static Date fromString(String sdate) {
		try {
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT); // mm-dd-yyy
		return df.parse(sdate);
		}catch(ParseException ex) {
			return new Date(); // Return Current Date on exception
		}
	}

}

	