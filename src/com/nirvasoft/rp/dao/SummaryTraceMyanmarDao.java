package com.nirvasoft.rp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.nirvasoft.rp.shared.SummaryResponse;

public class SummaryTraceMyanmarDao {

	public SummaryResponse getSummary(Connection pConn) throws Exception {
		SummaryResponse resObj = new SummaryResponse();
		String l_Query = "SELECT * FROM SummaryTraceMyanmar ORDER BY createdDateTime DESC;";
		PreparedStatement pstmt = pConn.prepareStatement(l_Query);
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			resObj.setCode("0000");
			resObj.setDesc("Selected successfully.");
			resObj.setCreatedDateTime(rs.getString("CreatedDateTime"));
			resObj.setPositive(rs.getString("Positive"));
			resObj.setPatientUnderInvestigation(rs.getString("PatientUnderInvestigation"));
			resObj.setHospitalQuarantine(rs.getString("HospitalQuarantine"));
			resObj.setHotelQuarantine(rs.getString("HotelQuarantine"));
			resObj.setFacilityQuarantine(rs.getString("FacilityQuarantine"));
			resObj.setRecoveries(rs.getString("Recoveries"));
		} else {
			resObj.setCode("0014");
			resObj.setDesc("Selected fail.");
		}

		return resObj;
	}

}
