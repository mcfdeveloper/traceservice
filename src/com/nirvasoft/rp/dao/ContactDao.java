package com.nirvasoft.rp.dao;

import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.nirvasoft.rp.shared.ContactData;
import com.nirvasoft.rp.shared.ContactResult;

public class ContactDao {

	public ContactResult getContact(String division,Connection conn) throws SQLException {
	ContactResult response = null;

	String query = "";
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	try {
		query = "SELECT division,township,phoneNo FROM Contact where division like ?";

		preparedStatement = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		preparedStatement.setString(1, "%" + division + "%");
		resultSet = preparedStatement.executeQuery();	

		ArrayList<ContactData> datalist = new ArrayList<ContactData>();
		int x = 0;
			while (resultSet.next()) {
				ContactData data = new ContactData();
				data.setDivision(resultSet.getString("division"));
				data.setTownship(resultSet.getString("township"));
				data.setPhoneNo(resultSet.getString("phoneNo"));									
				datalist.add(data);
			}
			
			ContactData[] dataarr = new ContactData[datalist.size()];
			for (int i = 0; i < datalist.size(); i++) {
				dataarr[i] = datalist.get(i);
			}


			preparedStatement.close();
			resultSet.close();
			preparedStatement = null;
			resultSet = null;		

			response = new ContactResult();
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
}
