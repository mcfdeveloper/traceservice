package com.nirvasoft.rp.mgr;

import java.sql.Connection;
import java.sql.SQLException;

import com.nirvasoft.rp.dao.SummaryTraceMyanmarDao;
import com.nirvasoft.rp.framework.ConnAdmin;
import com.nirvasoft.rp.shared.SummaryResponse;

public class SummaryTraceMyanmarMgr {

	public SummaryResponse getSummary() {
		SummaryResponse resMgrObj = new SummaryResponse();
		SummaryTraceMyanmarDao l_dao = new SummaryTraceMyanmarDao();

		Connection conn = null;

		try {
			conn = ConnAdmin.getConn("001", "");

			resMgrObj = l_dao.getSummary(conn);
		} catch (Exception e) {
			e.printStackTrace();

			resMgrObj.setCode("0014");
			resMgrObj.setDesc("Selected fail.");
			resMgrObj.setError(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					if (!conn.isClosed())
						conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return resMgrObj;
	}

}
