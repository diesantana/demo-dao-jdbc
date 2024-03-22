package application;

import java.util.Locale;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DepartmentDao depDao = DaoFactory.createDepartmentDao();
		
		System.out.println("========= TEST 1 (DEPARTMENT): insert()  =========");
		Department music = new Department("Music");
		
		depDao.insert(music);
		System.out.println("Insert completed!");
		
		
		
		sc.close();
	}

}
