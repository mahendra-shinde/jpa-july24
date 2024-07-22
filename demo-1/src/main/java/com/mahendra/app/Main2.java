package com.mahendra.app;

import java.util.List;

import com.mahendra.model.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class Main2 {

	public static void main(String[] args) {
		
		EntityManager em = DatabaseUtil.getFactory().createEntityManager(); 
		
		TypedQuery<Employee> q = em.createQuery("from Employee e where e.empId = ?1",Employee.class);
		q.setParameter(1, 492143 );
		/// Easier alternative : Employee e = em.find(Employee.class, 492143);
		
		Employee e = q.getSingleResult();
		
		System.out.println("Employee found !");
		System.out.println(e.getLastName()+" "+e.getFirstName()+" Joined on "+e.getJoiningDate());
	}

}
