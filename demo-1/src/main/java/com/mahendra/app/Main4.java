package com.mahendra.app;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.mahendra.model.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

public class Main4 {

	/**
	 * Find the employee named "Joe Biden" and update his/her
	 * Date of birth => 10/2//1945 
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManager em = DatabaseUtil.getFactory().createEntityManager();
		
		String firstName = "Joe";
		String lastName = "Biden";
		
		TypedQuery<Employee> q = em.createQuery("from Employee e where e.firstName =?1 and e.lastName =?2 ", Employee.class);
		q.setParameter(1,firstName);
		q.setParameter(2, lastName);
		List<Employee> empList = q.getResultList();
		if(empList.size() >= 1) {
			System.out.println("Found Joe Biden !");
		}else {
			System.out.println("No employee found with name Joe Biden");
			return;
		}
		
		EntityTransaction tn = em.getTransaction();
		try {
			tn.begin();
			Employee e1 = empList.get(0);
			e1.setBirthDate(fromString("10/2/1945"));
			em.merge(e1);
			tn.commit();
			System.out.println("Record Updated");
		}catch(PersistenceException ex) {
			if(tn != null ) {
				tn.rollback();
			}
		}
		
		
		
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
