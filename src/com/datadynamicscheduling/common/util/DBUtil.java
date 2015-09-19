package com.datadynamicscheduling.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class DBUtil {

	private DBUtil() {
	}

	public Connection getDBConnection() {
		Map<String, String> dbProperties = PropertyReader.getDBProperties();

		String username = dbProperties.get(AppConstants.DB.USERNAME);
		String password = dbProperties.get(AppConstants.DB.PASSWORD);
		String url = dbProperties.get(AppConstants.DB.URL);
		String driverClass = dbProperties.get(AppConstants.DB.DRIVER);

		Connection con = null;
		try {
			Class.forName(driverClass);
			con = DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void main(String[] args) throws SQLException {
		Connection con = new DBUtil().getDBConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from input_system");
		while (rs.next()) {
			System.out.println(rs.getString("item_id"));
		}
		con.close();
	}
}
