package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLite {
//	private static String PASSWORD;
//	public static void setter(String password) {
//		PASSWORD = password;
//	}
//	public static void main(String[] args) {
//		String jdbcUrl = "jdbc:sqlite:/C:\\sqlite\\sqlite-dll-win64-x64-3410200\\usersdb.db";
//		try {
//			Connection connection = DriverManager.getConnection(jdbcUrl);
////			String insert = "insert into users values('Faculty','010')";
////			String insert = "Delete from users";
//			PreparedStatement pstmt=connection.prepareStatement(insert);
//			pstmt.executeUpdate();
//			
//			
//			String sql = "SELECT rowid, * FROM users";
//			Statement statement = connection.createStatement();
//			ResultSet result=statement.executeQuery(sql);
//			//statement.executeQuery(sql);
//			
//			while(result.next()) {
//				Integer id = result.getInt("rowid");
//				String name = result.getString("name");
//				String ID = result.getString("ID");
//				System.out.println(id+"|"+name+" | "+ID);
//			}
//		} catch (SQLException e) {
//			System.out.print("Error connecting to SQLdatabase");
//			e.printStackTrace();
//		}
//	}
	private static Connection connect() {
		// SQLite connection string
		String url = "jdbc:sqlite:lib/usersdb.db";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	public void delete() {
		String sql = "DELETE FROM users";
		try {
			Connection conn = this.connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.execute();
			
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	

	public void insert(String name, String capacity) {
		String sql = "INSERT INTO users(name, ID) VALUES(?,?)";

		try {
			Connection conn = this.connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, capacity);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public void insertInformation(String first, String last, String gender,String School,String date,String program,String semster,String course,String grade,String personal,String academic) {
		String sql = "INSERT INTO draft(first, last,gender,school,date,program,semester,course,grade,personal,academic) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

		try {
			Connection conn = this.connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, first);
			pstmt.setString(2, last);
			pstmt.setString(3, gender);
			pstmt.setString(4, School);
			pstmt.setString(5, date);
			pstmt.setString(6, program);
			pstmt.setString(7, semster);
			pstmt.setString(8, course);
			pstmt.setString(9, grade);
			pstmt.setString(10, personal);
			pstmt.setString(11, academic);
			
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public ArrayList<String> getInformation(String last_name) throws SQLException {
		ArrayList<String> information = new ArrayList<>();
		String sql;
		if(last_name == null) {
			sql = "SELECT * FROM draft ORDER BY rowid DESC LIMIT 1";
		}
		else {
			sql = "SELECT * FROM draft WHERE last='"+last_name+"'";
		}
		
		Connection conn = connect();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			
			String first = rs.getString("first");
			String last =rs.getString("last");
			String gender =rs.getString("gender");
			String school =rs.getString("school");
			String date = rs.getString("date");
			String program = rs.getString("program");
			String semester = rs.getString("semester");
			String course = rs.getString("course");
			String grade = rs.getString("grade");
			String personal = rs.getString("personal");
			String academic = rs.getString("academic");
			information.add(first);
			information.add(last);
			information.add(gender);
			information.add(school);
			information.add(date);
			information.add(program);
			information.add(semester);
			information.add(course);
			information.add(grade);
			information.add(personal);
			information.add(academic);
		}
		rs.close();
		
		return information;
		
	}
	public ArrayList<String> getfaculty() throws SQLException {
		ArrayList<String> faculty = new ArrayList<>();
		String sql = "SELECT * FROM faculty";
		Connection conn = connect();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			
			String name = rs.getString("name");
			String title = rs.getString("title");
			String school = rs.getString("school");
			String department = rs.getString("department");
			String email = rs.getString("email");
			String phone = rs.getString("phone");
			
			faculty.add(name);
			faculty.add(title);
			faculty.add(school);
			faculty.add(department);
			faculty.add(email);
			faculty.add(phone);
			
		}
		rs.close();
		
		return faculty;
		
	}
	
	public void addcourse(String course) {
		String sql = "INSERT INTO course(course) VALUES(?)";
		try {
			Connection conn = this.connect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, course);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void selectAll() {
		String sql = "SELECT rowid, * FROM users";

		try {
			Connection conn = this.connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// loop through the result set
			while (rs.next()) {
				Integer id = rs.getInt("rowid");
				String name = rs.getString("name");
				String ID = rs.getString("ID");
				System.out.println(id+"|"+name+" | "+ID); 
			}
			//this.ID = rs.getString("ID");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public void  selectCourse() {
		String sql = "SELECT * FROM course";
		try {
			Connection conn = this.connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// loop through the result set
			while (rs.next()) {
				
				String course = rs.getString("course");
				
				System.out.println(course); 
			}
			//this.ID = rs.getString("ID");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public ArrayList<String> getcourse() throws SQLException {
		ArrayList<String> course = new ArrayList<>();
		String sql = "SELECT * FROM course";
		Connection conn = connect();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			
			String result = rs.getString("course");
			course.add(result);
		}
		rs.close();
		
		return course;
		
	}
	
	public ArrayList<String> getprogram() throws SQLException {
		ArrayList<String> course = new ArrayList<>();
		String sql = "SELECT * FROM program";
		Connection conn = connect();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			
			String result = rs.getString("name");
			course.add(result);
		}
		rs.close();
		
		return course;
		
	}
	public ArrayList<String> getsemster() throws SQLException {
		ArrayList<String> semster = new ArrayList<>();
		String sql = "SELECT * FROM semester";
		Connection conn = connect();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			
			String result = rs.getString("semester");
			semster.add(result);
		}
		rs.close();
		
		return semster;
		
	}
	public ArrayList<String> getcharacteristics() throws SQLException {
		ArrayList<String> semster = new ArrayList<>();
		String sql = "SELECT * FROM characteristics";
		Connection conn = connect();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			
			String result = rs.getString("characteristics");
			semster.add(result);
		}
		rs.close();
		
		return semster;
		
	}
	public ArrayList<String> getpersonal() throws SQLException {
		ArrayList<String> semster = new ArrayList<>();
		String sql = "SELECT * FROM personal";
		Connection conn = connect();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			
			String result = rs.getString("personal");
			semster.add(result);
		}
		rs.close();
		
		return semster;
		
	}
	public void removecourse(String course) {
		String sql = "delete from course where course='"+course+"'";
		Connection conn = SQLite.connect();
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static  String getpass() {
		String sql = "SELECT rowid, * FROM users";
		String ID =null;

		try {
			Connection conn = connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ID = rs.getString("ID");
			rs.close();

			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return ID;
		
	}
	public static  String getname() {
		String sql = "SELECT rowid, * FROM users";
		String name =null;

		try {
			Connection conn = connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			name = rs.getString("name");
			rs.close();

			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return name;
		
	}

	public static void main(String[] args) throws SQLException {

		SQLite app = new SQLite();
		//app.delete();
		// insert three new rows
		//app.insert("Faculty", "1234");
		//app.selectAll();
		//System.out.print(SQLite.getname());
		//app.removecourse("CS151: Object-Oriented Design");
		ArrayList<String> al=app.getcharacteristics();
		for(int i=0;i<al.size();i++) {
			System.out.println(al.get(i));
		}
		
	}

}
