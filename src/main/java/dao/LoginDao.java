package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.LoginBean;
import interfaces.ILoginDao;
import util.DatabaseConnexion;

public class LoginDao implements ILoginDao {

	@Override
	public boolean loginUser(LoginBean loginBean) {
		String username = loginBean.getUsername();
		String password = loginBean.getPassword();
		
		Connection connection = DatabaseConnexion.getConnection();	
		String usernameInDB = "";
		String passwordInDB = "";
		
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT USERNAME,PASSWORD FROM USER");
			ResultSet resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				usernameInDB = resultSet.getString("username");
				passwordInDB = resultSet.getString("password");
				if(username.equals(usernameInDB) && password.equals(passwordInDB)) {
					return true;
				}
			}
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
