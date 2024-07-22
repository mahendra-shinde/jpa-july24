package com.mahendra.app;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.mahendra.dao.EmployeeDAO;
import com.mahendra.model.Employee;

public class Main {

	public static void main(String[] args) {
		EmployeeDAO dao = new EmployeeDAO();
		System.out.println("List all employees who joined in 2020");
		/*
		 * List<Employee> list = dao.findByHireYear(2020); for(Employee e : list) {
		 * System.out.println(e.getLastName()+" "+e.getFirstName()+" "+e.getJoiningDate(
		 * )); }
		 */
		
		Employee newEmp = new Employee();
		newEmp.setEmpId(130013);
		newEmp.setFirstName("Bruce");
		newEmp.setLastName("Wyne");
		newEmp.setGender("M");
		newEmp.setBirthDate(fromString("1/3/1976"));
		newEmp.setJoiningDate(fromString("12/3/1990"));
		
		dao.save(newEmp);
		
		Employee e2 = dao.findByEmpId(130013);
		System.out.println(e2.getLastName()+" "+e2.getFirstName());

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
