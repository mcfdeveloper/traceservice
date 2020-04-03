package com.nirvasoft.rp.mgr;

import java.sql.Connection;
import com.nirvasoft.rp.util.ServerUtil;
import java.sql.SQLException;
import com.nirvasoft.rp.framework.ConnAdmin;
import com.nirvasoft.rp.shared.ContactResult;
import com.nirvasoft.rp.dao.ContactDao;

public class ContactMgr {

	public ContactResult getContact(String division) {
	ContactResult response = null;

	Connection conn = null;

	try {
		conn = ConnAdmin.getConn("001", "");
		response = new ContactDao().getContact(division,conn);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		ServerUtil.closeConnection(conn);
	}

	return response;
}
}
