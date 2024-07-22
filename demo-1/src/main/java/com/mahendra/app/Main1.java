package com.mahendra.app;

import java.util.List;

import com.mahendra.model.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

public class Main1 {

	public static void main(String[] args) {
		try {
		EntityManagerFactory factory = DatabaseUtil.getFactory();
		EntityManager em = factory.createEntityManager();
		
		List<Employee> empList = null;
		
		TypedQuery<Employee> q = em.createQuery("from Employee e",Employee.class);
		empList = q.getResultList();
		System.out.println("Found "+empList.size()+" employees");
		for(Employee e : empList) {
			System.out.println(e.getLastName()+" "+e.getFirstName());
		}
		}catch(PersistenceException ex) {
			ex.printStackTrace();
			return;
		}
	}

}
