//package com.nirvasoft.rp.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import com.nirvasoft.rp.shared.VersionReq;
//import com.nirvasoft.rp.shared.VersionRes;
//
//public class VersionDao {
//
//	public static String mTableName = "VersionHistory";
//
//	public VersionRes getVersion(VersionReq data, Connection conn) throws SQLException {
//		VersionRes ret = new VersionRes();
//		String sql = "select version, versionTitle, status, description, t1, t2 from " + mTableName + ""
//				+ " where versionKey >= (select versionKey from " + mTableName
//				+ " where appCode = ? and version = ?)  order by versionKey desc;";
//		PreparedStatement ps = conn.prepareStatement(sql);
//		ps.setString(1, data.getAppCode());
//		ps.setString(2, data.getVersion());
//		ResultSet rs = ps.executeQuery();
//		if (rs.next()) {
//			if (rs.getString("version").equalsIgnoreCase(data.getVersion())) {
//				ret.setVersionStatus(0);
//				ret.setVersionDesc("App is update to date");
//			} else {
//				ret.setVersionStatus(rs.getInt("status"));
//			}
//			ret.setVersion(rs.getString("version"));
//			ret.setVersionTitle(rs.getString("versionTitle"));
//			ret.setVersionDesc(rs.getString("description"));
//			ret.setNotiTitle(rs.getString("t1"));
//			ret.setNotiDesc(rs.getString("t2"));
//		} else {
//			ret.setVersion(data.getVersion());
//			ret.setVersionTitle("");
//			ret.setVersionStatus(0);
//			ret.setVersionDesc("App is update to date");
//		}
//		ps.close();
//		rs.close();
//		ret.setCode("0000");
//		ret.setDesc("Success");
//		return ret;
//	}
//
//}
