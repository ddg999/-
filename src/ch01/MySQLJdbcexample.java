package ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ch01.dto.Employee;

public class MySQLJdbcexample {

	public static void main(String[] args) {

		// 준비물
		//
		String url = "jdbc:mysql://localhost:3306/mydb2?serverTimezone=Asia/Seoul";
		String user = "root"; // 상용서비스에서 절대 루트 계정 사용 금지
		String password = "asd123";

		// 필요 데이터 타입
		// JDBC API 레벨 (자바 개발자들이 개념화 시놓으면 클래스들이다)
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// 1. MySQL 구현체를 사용하겠다는 설정을 해야한다.
			// JDBC 드라이버 로드(MySQL 드라이버)
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. 데이터 베이스 연결 설정
			connection = DriverManager.getConnection(url, user, password);

			// 3. SQL 실행
			statement = connection.createStatement();
			// 딱 2가지는 기억하자! 쿼리를 실행시키는 메서드
			resultSet = statement.executeQuery("SELECT * FROM employee"); // Select 에 사용
			// statement.executeUpdate(" "); --> Insert, Update, Delete 에 사용

			// 구문 분석 -- 파싱

			// 3-1 쿼리 만들어보기
//			String query = "insert into employee values(?, ?, ?, ?, now())";
//			PreparedStatement preparedStatement = connection.prepareStatement(query);
//			preparedStatement.setInt(1, 7);
//			preparedStatement.setString(2, "이순신");
//			preparedStatement.setString(3, "IT");
//			preparedStatement.setString(4, "5000000.00");
//
//			// 실행의 호출은 executeQuery 사용
//			int rowCount = preparedStatement.executeUpdate();
//
//			System.out.println("rowCount : " + rowCount);

			ArrayList<Employee> employees = new ArrayList<>();

			// 4. 결과 처리
			while (resultSet.next()) {
//				System.out.println("USER ID : " + resultSet.getInt("id"));
//				System.out.println("USER NAME : " + resultSet.getString("name"));
//				System.out.println("department : " + resultSet.getString("department"));
//				System.out.println("----------------------");
				Employee e = new Employee();
				e.setId(resultSet.getInt("id"));
				e.setName(resultSet.getString("name"));
				e.setDepartment(resultSet.getString("department"));
				employees.add(e);
			}

			for (Employee employee : employees) {
				System.out.println("id : " + employee.getId());
				System.out.println("name : " + employee.getName());
				System.out.println("department : " + employee.getDepartment());
				System.out.println("----------------------");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	} // end of main

} // end of class
