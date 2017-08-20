package conndb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.mysql.jdbc.Blob;
import com.mysql.jdbc.Driver;
import com.sun.corba.se.pept.transport.ConnectionCache;
import com.sun.crypto.provider.RSACipher;
import com.sun.org.glassfish.external.statistics.Statistic;
import com.sun.swing.internal.plaf.basic.resources.basic;
import com.sun.xml.internal.bind.v2.runtime.Name;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import allmessage.allmessage;
import javafx.css.PseudoClass;
import sun.security.action.GetBooleanAction;
import sun.util.logging.resources.logging_pt_BR;

public class conndb {
	
	public Connection connection;
	public conndb() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url="jdbc:mysql://localhost:3306/netdemo?useSSL=true";
		try {
			connection=DriverManager.getConnection(url, "root", "1324");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public int insertdb(String ids,String name) 
	{
		String sql="insert into names value(?,?)";
		PreparedStatement pStatement;
		int i=0;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, ids);
			pStatement.setString(2, name);
			i=pStatement.executeUpdate();
			pStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;

	}
	public void deletename(String ids)
	{
		String sql="delete from names where id=?";
		int i=-1;
		try {
			PreparedStatement pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, ids);
			pStatement.executeUpdate();
			pStatement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<String> getnames()
	{
		ArrayList<String> names=new ArrayList<>();
		String sql="select name from names";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				names.add(resultSet.getString(1));
			}
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return names;
	}
	public void submitmessage(String name,Timestamp mestime, String words)
	{
		
		String sql="insert into news value(?,?,?)";
		try {
			PreparedStatement preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setTimestamp(2, mestime);
			preparedStatement.setString(3, words);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<allmessage> getnews()
	{
		String sql="select * from news order by mestime asc";
		ArrayList<allmessage> allnews=new ArrayList<>();
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet rSet=preparedStatement.executeQuery();
			while(rSet.next())
			{
				allmessage temp=new allmessage();
				temp.setName(rSet.getString(1));
				temp.setTimestamp(rSet.getTimestamp(2));
				temp.setMessages(rSet.getString(3));
				allnews.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allnews;
	}
	public int checknames(String ids,String name)
	{
		String sql="select count(*) from names where id=? and name=?";
		int count=0;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, ids);
			preparedStatement.setString(2, name);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			count=resultSet.getInt(1);
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	public void initimgs(String name)
	{
		System.out.println("init imgs");
		String path="/Users/yuzhengwang/Desktop/ymka_logo.jpg";
		String sql="insert into imgs values(?,?); ";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			InputStream inputStream=new FileInputStream(path);
			preparedStatement.setBinaryStream(2, inputStream);
			preparedStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void deleteimgs(String name)
	{
		
		String sql="delete from imgs where name=?;";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.executeUpdate();
//			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	public void updateimgs(String name,String path)
	{
		String sql="update imgs set data=? where name=?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(2, name);
			InputStream inputStream=new FileInputStream(path);
			preparedStatement.setBinaryStream(1, inputStream);
			preparedStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("更新图片完成");
	}
	public void insertfiles(String ids,String filename,String path)
	{
		String sql="insert into files values(?,?,?); ";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ids);
			preparedStatement.setString(2, filename);
			InputStream inputStream=new FileInputStream(path);
			preparedStatement.setBinaryStream(3, inputStream);
			preparedStatement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ArrayList<String>filenamelists()
	{
		ArrayList<String>files=new ArrayList<>();
		String sql="select filename from files;";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				String s;
				s=resultSet.getString(1);
				files.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return files;
		
	}
	public java.sql.Blob getfiles(String filename)
	{
		String sql="select data from files where filename=?";
		java.sql.Blob blob = null;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, filename);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				blob=resultSet.getBlob(1);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blob;
	}
	public java.sql.Blob getimgs(String name)
	{

		String sql="select data from imgs where name=?";
		java.sql.Blob blob = null;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				blob=resultSet.getBlob(1);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blob;
	}
//	public void end()
//	{
//		try {
//			connection.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	public static void main(String[] args)
	{
		
	}

}




































