package com.nirvasoft.rp.mgr;

import com.nirvasoft.rp.shared.FleepListDataset;
import com.nirvasoft.rp.shared.NotificationDataSet;
import java.sql.Connection;
import com.nirvasoft.rp.util.ServerUtil;
import java.sql.SQLException;
import com.nirvasoft.rp.framework.ConnAdmin;
import com.nirvasoft.rp.shared.UserData;
import com.nirvasoft.rp.shared.UserList;
import com.nirvasoft.rp.dao.NotiDao;

public class NotiMgr {
	public NotificationDataSet getAllNoti(String searchText,String city,String township) {
		NotificationDataSet response = null;

		Connection conn = null;

		try {
			conn = ConnAdmin.getConn("001", "");
			response = new NotiDao().getAllNoti(searchText,city,township, conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ServerUtil.closeConnection(conn);
		}

		return response;
	}
	public FleepListDataset getAllFleepData(String searchText) {
		FleepListDataset response = null;

		Connection conn = null;

		try {
			conn = ConnAdmin.getConn("001", "");
			response = new NotiDao().getAllFleepData(searchText, conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ServerUtil.closeConnection(conn);
		}

		return response;
	}

}
