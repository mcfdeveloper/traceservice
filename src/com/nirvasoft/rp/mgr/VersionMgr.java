//package com.nirvasoft.rp.mgr;
//
//import java.sql.Connection;
//import java.util.ArrayList;
//
//import com.nirvasoft.rp.dao.VersionDao;
//import com.nirvasoft.rp.framework.ConnAdmin;
//import com.nirvasoft.rp.shared.VersionReq;
//import com.nirvasoft.rp.shared.VersionRes;
//import com.nirvasoft.rp.util.GeneralUtil;
//import com.nirvasoft.rp.util.ServerGlobal;
//import com.nirvasoft.rp.util.ServerUtil;
//
//public class VersionMgr {
//	public VersionRes getVersion(VersionReq data) {
//		VersionRes ret = new VersionRes();
//		Connection conn = null;
//		try {
//			conn = ConnAdmin.getConn("", "");
//			ret = new VersionDao().getVersion(data, conn);
//		} catch (Exception e) {
//			ret.setCode("0014");
//			ret.setDesc("Server error!");
//			if (ServerGlobal.isWriteLog()) {
//				ArrayList<String> l_err = new ArrayList<String>();
//				l_err.add("Time :" + GeneralUtil.getTime());
//				l_err.add("Get Version Res : " + e.getMessage());
//				l_err.add("=============================================================================");
//				GeneralUtil.writeLog(l_err, "\\Mobile\\log\\");
//			}
//		} finally {
//			ServerUtil.closeConnection(conn);
//		}
//		return ret;
//	}
//}
