package application;

import java.util.Locale;
import java.util.Scanner;

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

		depDao.deleteById(6);
		System.out.println("Delete completed!");
		
		
		sc.close();
	}

}
