package com.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;

public class CreateJDBC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OracleDataSource dataSource = null;
		try {
			dataSource = new OracleDataSource();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String jdbcUrl = "jdbc:oracle:thin:@disisdb:1521/disismain.isis.gwl.com";
		String dbuserid = "krsbhr";
		String dbpassword = "db123";
		dataSource.setURL(jdbcUrl);
		try {
			Connection conn = dataSource.getConnection(dbuserid,dbpassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Set");
	}
}
