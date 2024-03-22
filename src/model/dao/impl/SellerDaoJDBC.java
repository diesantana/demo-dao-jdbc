package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		PreparedStatement preparedSt = null;

		try {

			preparedSt = conn.prepareStatement(
					"INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES " 
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			preparedSt.setString(1, obj.getName());
			preparedSt.setString(2, obj.getEmail());
			preparedSt.setDate(3, Date.valueOf(obj.getDate()));
			preparedSt.setDouble(4, obj.getBaseSalary());
			preparedSt.setInt(5, obj.getDepartment().getId());

			int rows = preparedSt.executeUpdate();

			if (rows > 0) {

				ResultSet result = preparedSt.getGeneratedKeys();
				
				while (result.next()) {
					int generatedId = result.getInt(1);
					obj.setId(generatedId);
				}
				DB.closeResultSet(result);
				
			} else {
				// Lança uma exceção se nenhuma linha foi alterada
				throw new DbException("Unexpected error: No rows Affected!");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(preparedSt);
			
		}
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
					"SELECT seller.*, department.Name AS DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.departmentId = department.Id " + "WHERE seller.Id = ?");

			preparedSt.setInt(1, id);
			result = preparedSt.executeQuery();

			while (result.next()) {

				Department department = instantiateDepartment(result);
				Seller seller = instantiateSeller(department, result);
				return seller;
			}

			return null;

		} catch (SQLException e) {

			throw new DbException(e.getMessage());

		} finally {
			DB.closeStatement(preparedSt);
			DB.closeResultSet(result);
		}
	}

	private Seller instantiateSeller(Department department, ResultSet result) throws SQLException {
		Seller seller = new Seller();
		seller.setId(result.getInt("Id"));
		seller.setName(result.getString("Name"));
		seller.setEmail(result.getString("Email"));
		seller.setDate(result.getDate("BirthDate").toLocalDate());
		seller.setBaseSalary(result.getDouble("BaseSalary"));
		seller.setDepartment(department);

		return seller;
	}

	private Department instantiateDepartment(ResultSet result) throws SQLException {
		Department department = new Department();
		department.setId(result.getInt("DepartmentId"));
		department.setName(result.getString("DepName"));

		return department;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement preparedSt = null;
		ResultSet result = null;

		try {
			preparedSt = conn.prepareStatement(
					"SELECT seller.*, department.Name AS DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.departmentId = department.Id " + "ORDER BY Name");

			result = preparedSt.executeQuery();
			List<Seller> sellers = new ArrayList<Seller>();

			// Map armazena o id do departamento e um departamento
			Map<Integer, Department> map = new HashMap<Integer, Department>();

			while (result.next()) {
				// Instancia o departamento se já existir, se não existir o valor será null
				Department department = map.get(result.getInt("DepartmentId"));

				// verifica se é null se sim, significa que o Departamento ainda não foi
				// instanciado.
				if (department == null) {
					// instancia um novo departamento
					department = instantiateDepartment(result);
					// passa o novo departamento para o Map evitar duplicidade
					map.put(department.getId(), department);
				}

				Seller seller = instantiateSeller(department, result);
				sellers.add(seller);
			}

			return sellers;

		} catch (SQLException e) {

			throw new DbException(e.getMessage());

		} finally {
			DB.closeStatement(preparedSt);
			DB.closeResultSet(result);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {

		PreparedStatement preparedSt = null;
		ResultSet result = null;

		try {
			preparedSt = conn.prepareStatement(
					"SELECT seller.*, department.Name AS DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.departmentId = department.Id " + "WHERE DepartmentId = ? " + "ORDER BY Name");

			preparedSt.setInt(1, department.getId());
			result = preparedSt.executeQuery();

			List<Seller> sellers = new ArrayList<Seller>();

			while (result.next()) {
				Seller seller = instantiateSeller(department, result);
				sellers.add(seller);
			}

			return sellers;

		} catch (SQLException e) {

			throw new DbException(e.getMessage());

		} finally {
			DB.closeStatement(preparedSt);
			DB.closeResultSet(result);
		}
	}

}
