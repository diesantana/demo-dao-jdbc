package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
		PreparedStatement preparedSt = null;
		ResultSet result  = null;
		try {
			
			preparedSt = conn.prepareStatement("SELECT * FROM department WHERE Id = ?");
			
			preparedSt.setInt(1, id);
			
			result = preparedSt.executeQuery();
			
			while(result.next()) {
				Department dep = new Department();
				dep.setId(result.getInt("Id"));
				dep.setName(result.getString("Name"));
				return dep;
			}
			return null;

		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(result);
			DB.closeStatement(preparedSt);
		}
		
	}

	@Override
	public List<Department> findAll() {
		Statement st = null;
		ResultSet result  = null;
		
		try {
			st = conn.createStatement();
			
			result = st.executeQuery("SELECT * FROM department");
			List<Department> list = new ArrayList<Department>();
			
			while(result.next()) {
				Department dep = new Department();
				dep.setId(result.getInt("Id"));
				dep.setName(result.getString("Name"));
				list.add(dep);
			}
			return list;
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(result);
			DB.closeStatement(st);
		}
	}

}
