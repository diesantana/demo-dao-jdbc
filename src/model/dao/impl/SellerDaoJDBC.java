package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{
	
	private Connection conn;
	
	

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		
		PreparedStatement preparedSt = null;
		ResultSet result = null;
		
		try {
			preparedSt = conn.prepareStatement(
					"SELECT seller.*, department.Name AS DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.departmentId = department.Id "
					+ "WHERE seller.Id = ?");
			
			preparedSt.setInt(1, id);
			result = preparedSt.executeQuery();
			
			while (result.next()) {
				Department department = new Department();
				department.setId(result.getInt("DepartmentId"));
				department.setName(result.getString("DepName"));
				
				Seller seller = new Seller();
				seller.setId(id);
				seller.setName(result.getString("Name"));
				seller.setEmail(result.getString("Email"));
				seller.setDate(result.getDate("BirthDate").toLocalDate());
				seller.setBaseSalary(result.getDouble("BaseSalary"));
				seller.setDepartment(department);
				
				return seller;
			}
			
			return null;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
