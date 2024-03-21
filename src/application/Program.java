package application;

import java.util.Locale;

import model.dao.DaoFactory;
import model.dao.SellerDao;
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
		System.out.println();
	}

}
