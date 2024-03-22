package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao{
	
	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement preparedSt = null;
		
		try {
			
			preparedSt = conn.prepareStatement("INSERT INTO department (Name) VALUES (?)");
			
			preparedSt.setString(1, obj.getName());
			
			int rows = preparedSt.executeUpdate();
			
			if (rows == 0) {
				throw new DbException("Unexpected error: No rows affected");
			}
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(preparedSt);
		}
	}

	@Override
	public void update(Department obj) {
		PreparedStatement preparedSt = null;
		
		try {
			
			preparedSt = conn.prepareStatement("UPDATE department SET Name = ? WHERE Id = ?");
			
			preparedSt.setString(1, obj.getName());
			preparedSt.setInt(2, obj.getId());
			
			int rows = preparedSt.executeUpdate();
			
			if (rows == 0) {
				throw new DbException("Unexpected error: No rows affected");
			}
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(preparedSt);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement preparedSt = null;
		
		try {
			
			preparedSt = conn.prepareStatement("DELETE FROM department WHERE Id = ?");
			
			preparedSt.setInt(1, id);
			
			int rows = preparedSt.executeUpdate();
			
			if (rows == 0) {
				throw new DbException("Unexpected error: No rows affected");
			}
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(preparedSt);
		}
		
	}

	@Override
	public Department findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
