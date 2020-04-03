package com.nirvasoft.rp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.nirvasoft.rp.shared.RegisterCheckRequest;

public class RegisterTraceMyanmarDao {

	public boolean checkPhoneNo(String phoneNo, Connection pConn) throws Exception {
		boolean isExist = false;
		String l_Query = "SELECT * FROM RegisterTraceMyanmar WHERE phoneno=? AND registersuccess='true'";
		PreparedStatement pstmt = pConn.prepareStatement(l_Query);
		pstmt.setString(1, phoneNo);
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			isExist = true;
		}

		return isExist;
	}

	public boolean checkPhoneNoAndOtp(String phoneNo, String otp, Connection pConn) throws Exception {
		boolean isExist = false;
		String l_Query = "SELECT * FROM RegisterTraceMyanmar WHERE phoneno=? AND registersuccess<>'true' AND otp=?";
		PreparedStatement pstmt = pConn.prepareStatement(l_Query);
		pstmt.setString(1, phoneNo);
		pstmt.setString(2, otp);
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			isExist = true;
		}

		return isExist;
	}

	public boolean insertRegister(RegisterCheckRequest reqObj, String otp, String today, Connection conn)
			throws Exception {
		String query = "INSERT INTO RegisterTraceMyanmar(createddatetime, modifieddatetime, phoneno, division, district, township, otp, registersuccess) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

		int i = 1;
		ps.setString(i++, today);
		ps.setString(i++, today);
		ps.setString(i++, reqObj.getPhoneNo());
		ps.setString(i++, reqObj.getDivision());
		ps.setString(i++, reqObj.getDistrict());
		ps.setString(i++, reqObj.getTownship());
		ps.setString(i++, otp);
		ps.setString(i++, "false");

		int result = 0;
		result = ps.executeUpdate();

		boolean saveSuccess = false;

		if (result > 0) {
			saveSuccess = true;
		}

		ps.close();

		return saveSuccess;
	}

	public boolean updateRegister(String phoneNo, String otp, String today, Connection pConn) throws Exception {
		String l_Query = "UPDATE RegisterTraceMyanmar SET registersuccess='true', modifieddatetime=? WHERE phoneno=? AND registersuccess='false' AND otp=?";
		PreparedStatement pstmt = pConn.prepareStatement(l_Query);
		pstmt.setString(1, today);
		pstmt.setString(2, phoneNo);
		pstmt.setString(3, otp);

		int rst = pstmt.executeUpdate();
		pstmt.close();

		if (rst > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateRegisterLocation(RegisterCheckRequest reqObj, String today, Connection pConn)
			throws Exception {
		String l_Query = "UPDATE RegisterTraceMyanmar SET modifieddatetime=?, division=?, district=?, township=? WHERE phoneno=?;";
		PreparedStatement pstmt = pConn.prepareStatement(l_Query);
		pstmt.setString(1, today);
		pstmt.setString(2, reqObj.getDivision());
		pstmt.setString(3, reqObj.getDistrict());
		pstmt.setString(4, reqObj.getTownship());
		pstmt.setString(5, reqObj.getPhoneNo());

		int rst = pstmt.executeUpdate();
		pstmt.close();

		if (rst > 0) {
			return true;
		} else {
			return false;
		}
	}

}
