package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		SellerDao sellerDao = DaoFactory.createSellerDao();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		
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
		
		
		System.out.println("\n==== TESTE 3: seller findAll ====");
		List<Seller> sellersAll = sellerDao.findAll();
		sellersAll.forEach(System.out::println);
		
		
		System.out.println("\n===== TESTE 4: seller insert() =====");
		Seller greg = new Seller(null, "Greg Yellow", "greg@gmail.com", LocalDate.parse("13/08/2002", format), 3750.0, new Department(3, "Fashion"));
		
		sellerDao.insert(greg);
		System.out.println("Done! Id: " + greg.getId());
		
		
	}

}
