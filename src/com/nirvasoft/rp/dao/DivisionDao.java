package com.nirvasoft.rp.dao;

import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.nirvasoft.rp.shared.DivisionData;
import com.nirvasoft.rp.shared.DivisionResult;
import com.nirvasoft.rp.shared.FleepRequest;
import com.nirvasoft.rp.shared.FleepResponse;
import com.nirvasoft.rp.shared.UserData;


public class DivisionDao {

	public DivisionResult getDivision(Connection conn) throws SQLException {
	DivisionResult response = null;

	String query = "";
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	try {
		query = "SELECT division,covid_check,covid_postive,covid_dead FROM division";

		preparedStatement = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		resultSet = preparedStatement.executeQuery();	

		ArrayList<DivisionData> datalist = new ArrayList<DivisionData>();
		int x = 0;
			while (resultSet.next()) {
				DivisionData data = new DivisionData();
				//data.setAutokey(resultSet.getLong("autokey"));
				data.setDivision(resultSet.getString("division"));
				data.setCheck(resultSet.getString("covid_check"));
				data.setPositive(resultSet.getString("covid_postive"));			
				data.setDead(resultSet.getString("covid_dead"));						
				datalist.add(data);
			}
			
			DivisionData[] dataarr = new DivisionData[datalist.size()];
			for (int i = 0; i < datalist.size(); i++) {
				dataarr[i] = datalist.get(i);
			}


			preparedStatement.close();
			resultSet.close();
			preparedStatement = null;
			resultSet = null;		

			response = new DivisionResult();
			response.setCode("0000");
			response.setDesc("Selected successfully.");
			response.setData(dataarr);
		
	} finally {
		if (resultSet != null) {
			resultSet.close();
		}

		if (preparedStatement != null) {
			preparedStatement.close();
		}
	}

		return response;
	}
	public FleepResponse insertFleep(FleepRequest req, Connection aConnection) throws SQLException {
		FleepResponse ret=new FleepResponse();
		String today = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
		String sql = "INSERT INTO Fleep(userid, fromLocation, toLocation, fleepNo, remark, depatureDateTime, arrivalDateTime,createddatetime,t1) Values (?,?,?,?,?,?,?,?,?)" ;
		
		PreparedStatement stmt = aConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		int i = 1;
			stmt.setString(i++, req.getPhoneNo());
			stmt.setString(i++, req.getFromLocation());
			stmt.setString(i++, req.getToLocation());
			stmt.setString(i++, req.getFleepNo());
			stmt.setString(i++, req.getRemark());
			stmt.setString(i++, req.getDepatureDateTime());
			stmt.setString(i++, req.getArrivalDateTime());
			stmt.setString(i++, today);
			stmt.setString(i++, req.getT1());
		
		long responseAutokey = 0;
		responseAutokey = stmt.executeUpdate();
		if (responseAutokey > 0) {
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				responseAutokey = rs.getLong(1);
			}
			ret.setCode("0000");
			ret.setDesc("Saved Successfully.");
			ret.setSkey(responseAutokey);
			ret.setFleetNo(req.getFleepNo());
		}
		else{
			ret.setCode("0014");
			ret.setDesc("Saved Fail.");
			
		}
		return ret;
	}
}
