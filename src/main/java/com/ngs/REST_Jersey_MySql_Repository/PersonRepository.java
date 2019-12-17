package com.ngs.REST_Jersey_MySql_Repository;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class PersonRepository {

	Connection con=null;
	public PersonRepository()  {
		String url="jdbc:mysql://localhost:3306/restdb";
		String username="root";
		String password="root";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=(Connection) DriverManager.getConnection(url,username,password);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public List<Person> getPersons(){
		List <Person> persons=new ArrayList<Person>();
		String sql="Select * from student";
		try {
			Statement st=(Statement) con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				Person p1=new Person();
				p1.setId(rs.getInt(1));
				p1.setName(rs.getString(2));
				persons.add(p1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return persons;
	}
	
	public Person getPerson(int id){
		String sql="select * from student where id="+id;
		Person p1=new Person();
		try {
			Statement st=(Statement) con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			if(rs.next()) {
				p1.setId(rs.getInt(1));
				p1.setName(rs.getString(2));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p1;
		
	}
	public void create(Person p1) {
		
		String sql="insert into student values(?,?)";
		try {
			PreparedStatement st=(PreparedStatement) con.prepareStatement(sql);
			st.setInt(1,p1.getId());
			st.setString(2, p1.getName());
			st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void update(Person p1) {
		
		String sql="update student set name=? where id=?";
		try {
			PreparedStatement st=(PreparedStatement) con.prepareStatement(sql);
			st.setString(1, p1.getName());
			st.setInt(2,p1.getId());
			st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void delete(int id) {

		String sql="delete from student where id=?";
		try {
			PreparedStatement st=(PreparedStatement) con.prepareStatement(sql);
			st.setInt(1,id);
			st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
