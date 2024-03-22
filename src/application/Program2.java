package application;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DepartmentDao depDao = DaoFactory.createDepartmentDao();
		
		System.out.println("========= TEST 1 (DEPARTMENT): insert()  =========");
		Department music = new Department("Music");
		
		//depDao.insert(music);
		//System.out.println("Insert completed!");
		
		System.out.println("\n\n========= TEST 2 (DEPARTMENT): update()  =========");
		music.setId(7);
		music.setName("Sports");
		
		//depDao.update(music);
		//System.out.println("Update completed!");
		
		System.out.println("\n\n========= TEST 3 (DEPARTMENT): deleteById()  =========");
		System.out.println("Enter the id to delete: ");
		int id = sc.nextInt();
		depDao.deleteById(id);
		System.out.println("Delete completed!");
		
		System.out.println("\n\n========= TEST 4 (DEPARTMENT): findById()  =========");

		Department dep = depDao.findById(7);
		if (dep == null) {
			System.out.println("Departamento não encontrado!");
		}
		else {
			System.out.println(dep);			
		}
		
		System.out.println("\n\n========= TEST 5 (DEPARTMENT): findAll()  =========");

		List<Department> departments = depDao.findAll();
		
		if (departments.isEmpty()) {
			
			System.out.println("Nenhum Departamento encontrado!");	
		}
		else {
			departments.forEach(System.out::println);
		}
					
		DB.closeConcection();
		sc.close();
	}

}
