package application;

import java.time.LocalDate;
import java.util.Locale;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		
		Department obj = new Department(1, "Books");
		LocalDate date = LocalDate.now();
		
		Seller seller = new Seller(21, "Bob", "bob@gmail.com", date, 2900.0, obj);
		
		System.out.println(seller);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
	}

}
