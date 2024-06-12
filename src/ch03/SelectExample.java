package ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectExample {

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/mydb2?serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "asd123";

		// Connection 객체를 얻어서 select 구문을 직접 만들어보세요
		// mydb2 사용, employee 테이블에 값을 찾는 코드를 작성하세요

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);

			String table = "employee";
			String query = "SELECT * FROM " + table + " WHERE salary >= ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "4000000");
			resultset = preparedStatement.executeQuery();

			while (resultset.next()) {
				System.out.println("사번 : " + resultset.getInt("id"));
				System.out.println("이름 : " + resultset.getString("name"));
				System.out.println("부서 : " + resultset.getString("department"));
				System.out.println("급여 : " + resultset.getLong(("salary")));
				System.out.println("고용일 : " + resultset.getDate(("hire_date")));
				System.out.println("---------------------------");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	} // end of main

}
