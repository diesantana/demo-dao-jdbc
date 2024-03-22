package application;

import java.util.List;
import java.util.Locale;

import db.DbException;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);

		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		
		System.out.println("==== TESTE 1: seller findById ====");
		Seller seller = sellerDao.findById(8);
		if (seller == null) {
			System.out.println("Id não existe na base de dados");
		} else {
			System.out.println(seller);			
		}
		
		
		System.out.println("\n==== TESTE 2: seller findByDepartment ====");
		Department department = new Department(1, "Computers");
		List<Seller> sellers = sellerDao.findByDepartment(department);
		
		if (sellers.size() > 0) {
			
			sellers.forEach(System.out::println);

		} else {
			//throw new DbException("Nenhum vendedor encontrado para este departamento");		
			System.out.println("Nenhum vendedor encontrado para este departamento");		
		}
		
	}

}
