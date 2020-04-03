package com.nirvasoft.rp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.nirvasoft.rp.shared.UserData;
import com.nirvasoft.rp.shared.UserList;

public class UserDao {

	public static long insert(UserData req, Connection aConnection) throws SQLException {
		String sql = "INSERT INTO Customer(phoneNo, deviceId, latitude, longitude, time, skey, fleepNo) Values (?,?,?,?,?,?,?)" ;
		
		PreparedStatement stmt = aConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		int i = 1;

		
			stmt.setString(i++, req.getPhoneNo());
			stmt.setString(i++, req.getDeviceId());
			stmt.setString(i++, req.getLatitude());
			stmt.setString(i++, req.getLongitude());
			stmt.setString(i++, req.getTime());
			stmt.setLong(i++, req.getSkey());
			stmt.setString(i++, req.getFleepNo());
		
		long responseAutokey = 0;
		responseAutokey = stmt.executeUpdate();
		if (responseAutokey > 0) {
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				responseAutokey = rs.getLong(1);
			}
		}
		return responseAutokey;
	}

	public static long selectLastAutokey(Connection aConn) throws SQLException {
		long key = 0;
		String query = "SELECT MAX(autokey) as lastkey FROM Customer;";
		PreparedStatement pstmt = aConn.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		key = rs.getLong("lastkey");
		pstmt.close();
		rs.close();

		return key;
	}
	
//	public static long update(UserData req, Connection aConnection) throws SQLException {
//		String sql = " UPDATE Customer SET name=?, address=?, age=?, phoneno=? "
//				+ "WHERE recordstatus<>4 And autokey=?;";
//		PreparedStatement stmt = aConnection.prepareStatement(sql);
//		int i = 1;
//		
//		stmt.setString(i++, req.getName());
//		stmt.setString(i++, req.getAddress());
//		stmt.setInt(i++, req.getAge());
//		stmt.setString(i++, req.getPhoneno());
//
//		// autokey=?
//		stmt.setLong(i++, req.getAutokey());
//
//		long responseInt = 0;
//		responseInt = stmt.executeUpdate();
//		return responseInt;
//	}
	
//	public boolean deleteUser(long autokey,Connection conn) throws SQLException{
//		boolean response = false;
//		
//		String query = "update Customer set recordstatus=4 where autokey = ?";
//		PreparedStatement psmt = conn.prepareStatement(query);
//		psmt.setLong( 1, autokey);
//		if (psmt.executeUpdate() > 0) 
//			response = true;
//		psmt.close();		
//		return response;
//		
//	}
//	
//	public UserList getUserList(Connection conn) throws SQLException {
//		UserList response = null;
//
//		String query = "";
//		PreparedStatement preparedStatement = null;
//		ResultSet resultSet = null;
//
//		try {
//			query = "SELECT autokey,name,address,age,phoneno FROM Customer where recordstatus <> 4";
//
//			preparedStatement = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
//					ResultSet.CONCUR_READ_ONLY);
//			resultSet = preparedStatement.executeQuery();	
//
//			ArrayList<UserData> datalist = new ArrayList<UserData>();
//			int x = 0;
//				while (resultSet.next()) {
//					UserData data = new UserData();
//					data.setAutokey(resultSet.getLong("autokey"));
//					data.setName(resultSet.getString("name"));
//					data.setAddress(resultSet.getString("address"));
//					data.setAge(resultSet.getInt("age"));			
//					data.setPhoneno(resultSet.getString("phoneno"));						
//					datalist.add(data);
//				}
//				
//				UserData[] dataarr = new UserData[datalist.size()];
//				for (int i = 0; i < datalist.size(); i++) {
//					dataarr[i] = datalist.get(i);
//				}
//
//
//				preparedStatement.close();
//				resultSet.close();
//				preparedStatement = null;
//				resultSet = null;		
//
//				response = new UserList();
//				response.setMsgCode("0000");
//				response.setMsgDesc("Selected successfully.");
//				response.setData(dataarr);
//			
//		} finally {
//			if (resultSet != null) {
//				resultSet.close();
//			}
//
//			if (preparedStatement != null) {
//				preparedStatement.close();
//			}
//		}
//
//		return response;
//	}
	
	
}
