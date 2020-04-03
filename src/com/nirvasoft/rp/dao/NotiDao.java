package com.nirvasoft.rp.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.nirvasoft.rp.shared.FleepData;
import com.nirvasoft.rp.shared.FleepListDataset;
import com.nirvasoft.rp.shared.NotiData;
import com.nirvasoft.rp.shared.NotificationDataSet;
import com.nirvasoft.rp.shared.UserData;
import com.nirvasoft.rp.shared.UserList;

public class NotiDao {
	public NotificationDataSet getAllNoti(String searchText, String district,String township, Connection conn)
			throws SQLException {
		NotificationDataSet response = null;

		String query = "";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
//			
				searchText = searchText.replace("'", "''");
				district = district.replace("'", "''");
				township = township.replace("'", "''");

			query = "select * from RegisterTraceMyanmar where division like? and district like? and township like? ";
				preparedStatement = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

			int i=1;
					preparedStatement.setString(1, "%" + searchText + "%");
					preparedStatement.setString(2, "%" + district + "%");
					preparedStatement.setString(3, "%" + township + "%");

				resultSet = preparedStatement.executeQuery();
			//}

			int totalRow = 0;
			resultSet.last();
			totalRow = resultSet.getRow();

			if (totalRow == 0) {
				response = new NotificationDataSet();
				response.setMsgCode("0000");
				response.setMsgDesc("Data Not Found.");
				response.setDivision(searchText);
				//response.setPageSize(pageSize);
				//response.setCurrentPage(currentPage);
				response.setData(null);
				//response.setTotalCount(0);
			}

			if (totalRow != 0) {
				resultSet.beforeFirst();
				NotiData[] dataarr = new NotiData[totalRow];
				int x = 0;

				while (resultSet.next()) {
					NotiData data = new NotiData();
					data.setPhoneno(resultSet.getString("phoneno"));
//					data.setName(resultSet.getString("name"));
					data.setDivision(resultSet.getString("division"));
					data.setDistrict(resultSet.getString("district"));
					data.setTownship(resultSet.getString("township"));
					data.setToken(resultSet.getString("token"));
					//data.setDeviceId(resultSet.getString("deviceid"));
					dataarr[x++] = data;
				}

				preparedStatement.close();
				resultSet.close();
				query = "";
				preparedStatement = null;
				resultSet = null;

				response = new NotificationDataSet();
				response.setMsgCode("0000");
				response.setMsgDesc("Selected successfully.");
//				response.setSearchText(searchText);
//				response.setPageSize(pageSize);
//				response.setCurrentPage(currentPage);
				response.setData(dataarr);
				//response.setTotalCount(resultSet.getInt("total"));
			}
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
	public FleepListDataset getAllFleepData(String searchText, Connection conn)
			throws SQLException {
		FleepListDataset response = null;

		String query = "";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
//			
				searchText = searchText.replace("'", "''");
				

			query = "select * from Fleep where userid =? ";
				preparedStatement = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
			     int i=1;
					preparedStatement.setString(1, searchText);
					

				resultSet = preparedStatement.executeQuery();
			//}

			int totalRow = 0;
			resultSet.last();
			totalRow = resultSet.getRow();

			if (totalRow == 0) {
				response = new FleepListDataset();
				response.setMsgCode("0000");
				response.setMsgDesc("Data Not Found.");
				response.setPhneno(searchText);
				//response.setPageSize(pageSize);
				//response.setCurrentPage(currentPage);
				response.setData(null);
				//response.setTotalCount(0);
			}

			if (totalRow != 0) {
				resultSet.beforeFirst();
				FleepData[] dataarr = new FleepData[totalRow];
				int x = 0;

				while (resultSet.next()) {
					FleepData data = new FleepData();
					data.setPhoneno(resultSet.getString("userid"));
					data.setFromLocation(resultSet.getString("fromLocation"));
					data.setTolocation(resultSet.getString("toLocation"));
					data.setFleepno(resultSet.getString("fleepNo"));
					data.setRemark(resultSet.getString("remark"));
					data.setDepaturedatetime(resultSet.getString("depatureDateTime"));
					data.setArrivaldatetime(resultSet.getString("arrivalDateTime"));
					
					dataarr[x++] = data;
				}

				preparedStatement.close();
				resultSet.close();
				query = "";
				preparedStatement = null;
				resultSet = null;

				response = new FleepListDataset();
				response.setMsgCode("0000");
				response.setMsgDesc("Selected successfully.");
//				response.setSearchText(searchText);
//				response.setPageSize(pageSize);
//				response.setCurrentPage(currentPage);
				response.setData(dataarr);
				//response.setTotalCount(resultSet.getInt("total"));
			}
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
	
	
}
