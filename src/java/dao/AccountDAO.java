package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Model.Account;
import dal.DBContext;


public class AccountDAO {

	Connection conn = null; //kết nối sql
	PreparedStatement ps = null; //thực hiện lệnh
	ResultSet rs = null; //kết quả
	
	public Account login(String user, String pass) {
		
		String query = "select * from Account where userName = ? and pass = ?";
		
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1,  user);
			ps.setString(2,  pass);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				return new Account(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getInt(6));
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Account checkAccountIsExists (String user) {
		
		String query = "select * from Account where userName = ?";
		
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1,  user);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				return new Account(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getInt(6));
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Account> getAccountByEmail (String email) {
		
		List<Account> list = new ArrayList<>();
		String query = "select * from Account where email = ?";
		
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1,  email);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Account temp = new Account(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getInt(6));
				
				list.add(temp);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void signUp (String user, String pass, String email) {
		
		String query = "insert into Account values(?, ?, ?, 0, 0);";
		
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1, user);
			ps.setString(2, pass);
			ps.setString(3, email);
			ps.executeUpdate();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public String getUsernameById (String userId){
		
		String result = "";
		String query = "select userName from Account where id = ?";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1,  userId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				result = rs.getString(1);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Account getAccountById (String userId){
		
		String query = "select * from Account where id = ?";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1,  userId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Account temp = new Account(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getInt(6));
				
				return temp;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Account> getAllAccount(){ //for admin
		
		List<Account> list = new ArrayList<>();
		String query = "select a.*, count(b.sellID) as SL from account a left join book b on a.id = b.sellID\r\n" + 
				"group by a.id, a.userName, a.pass, a.email, a.isSeller, a.isAdmin";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Account temp = new Account(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getInt(6));
				
//				if (rs.getInt(5) == 1) {
//					temp.setTotalBooks(rs.getInt(7));
//				}
				
				list.add(temp);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<Account> getTop5Seller(){ //for admin
		
		List<Account> list = new ArrayList<>();
		String query = "select top 5 a.userName, count(b.sellID) as SL \r\n" + 
				"from account a join book b on a.id = b.sellID \r\n" + 
				"group by a.userName order by SL desc";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Account temp = new Account();
				temp.setUsername(rs.getString(1));
				temp.setTotalBooks(rs.getInt(2));
				
				list.add(temp);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void deleteAccount (String id) {
		
		String query = "delete from Account where id = ?";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1,  id);
			ps.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void deleteAllAccount () {
		
		String query = "delete from Account";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void editAccount(Account account) {
		String query = "update Account\r\n" + 
				"set userName = ?, pass = ?, email = ?, isSeller = ?, isAdmin = ?\r\n" + 
				"where id = ?";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1, account.getUsername());
			ps.setString(2, account.getPassword());
			ps.setString(3, account.getEmail());
			ps.setInt(4, account.getIsSeller());
			ps.setInt(5, account.getIsAdmin());
			ps.setInt(6, account.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void resetRole() {
		String query = "update Account\r\n" + 
				"set isSeller = 0, isAdmin = 0";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void setSeller(String id) {
		String query = "update Account\r\n" + 
				"set isSeller = 1\r\n" + 
				"where id = ?";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1,  id);
			ps.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void setAdmin(String id) {
		String query = "update Account\r\n" + 
				"set isAdmin = 1\r\n" + 
				"where id = ?";
		try {
			
			conn = new DBContext().getConnection(); //mở kết nối sql server
			ps = conn.prepareStatement(query);
			ps.setString(1,  id);
			ps.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
