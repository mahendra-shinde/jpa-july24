package com.mahendra.dao;

import java.util.LinkedList;
import java.util.List;

import com.mahendra.model.Employee;

import jakarta.persistence.*;

public class EmployeeDAO {

	private EntityManagerFactory factory = null;

	public EmployeeDAO() {
		factory = Persistence.createEntityManagerFactory("PU1");
		
	}
	
	public Employee findByEmpId(Integer empId) {
		EntityManager em = factory.createEntityManager();
		try {
		Employee e = em.find(Employee.class, empId);
		return e;
		}catch(PersistenceException ex) {
			System.out.println(ex.getMessage());
			return null;
		}finally{
			em.close();
		}
	}
	
	public List<Employee> findByHireYear(int year) {
		List<Employee> list = new LinkedList<>();
		EntityManager em = factory.createEntityManager();
		try {
		TypedQuery<Employee> q = em.createQuery("from Employee e where extract(YEAR from  e.joinningDate) = ?1", Employee.class);
		q.setParameter(1, year);
		list = q.getResultList();
		}catch(PersistenceException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
		return list;
	}
	
	public Employee save(Employee e) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction tn =  null;
		try {
			tn = em.getTransaction();
			tn.begin();	// Start the transaction
			em.persist(e);	// "persist" method required an OPEN transaction
			tn.commit();		// Save the changes
			}catch(PersistenceException ex)
			{
				if(tn != null ) {
					tn.rollback(); // Undo the changes
				}
			}
		return e;
	}
	
}


