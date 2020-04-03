package com.nirvasoft.rp.mgr;

import java.sql.Connection;
import com.nirvasoft.rp.util.ServerUtil;
import java.sql.SQLException;
import com.nirvasoft.rp.framework.ConnAdmin;
import com.nirvasoft.rp.shared.DivisionResult;
import com.nirvasoft.rp.shared.FleepRequest;
import com.nirvasoft.rp.shared.FleepResponse;
import com.nirvasoft.rp.shared.UserData;
import com.nirvasoft.rp.shared.UserList;
import com.nirvasoft.rp.dao.DivisionDao;
import com.nirvasoft.rp.dao.UserDao;


public class DivisionMgr {

	public DivisionResult getDivision() {
		DivisionResult response = null;

	Connection conn = null;

	try {
		conn = ConnAdmin.getConn("001", "");
		response = new DivisionDao().getDivision(conn);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		ServerUtil.closeConnection(conn);
	}

	return response;
}
	public FleepResponse saveFleep(FleepRequest req) {
		FleepResponse response=new FleepResponse();

	Connection conn = null;

	try {
		conn = ConnAdmin.getConn("001", "");
		 response = new DivisionDao().insertFleep(req,conn);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		ServerUtil.closeConnection(conn);
	}

	return response;
}
}
