package com.nirvasoft.rp.mgr;

import java.sql.Connection;
import com.nirvasoft.rp.util.ServerUtil;
import java.sql.SQLException;
import com.nirvasoft.rp.framework.ConnAdmin;
import com.nirvasoft.rp.shared.UserData;
import com.nirvasoft.rp.shared.UserList;
import com.nirvasoft.rp.dao.UserDao;

public class UserMgr {

	public long saveUser(UserList reqData) {
		Connection aConn = null;
		long ret = 0;
		//long autokey = reqData.getData().getAutokey();
		try {
			aConn = ConnAdmin.getConn("001", "");
			//if (autokey == 0) {			
				
			for(int i=0;i<reqData.getData().length;i++){
				ret =  UserDao.insert(reqData.getData()[i], aConn);
			}
				//ret = UserDao.insert(reqData, aConn);

				if (ret != 0) {
					ret = UserDao.selectLastAutokey(aConn);
					//reqData.setAutokey(ret);
					reqData.setMsgDesc("Submit successfully.");
					reqData.setMsgCode("0000");
				}else{
					reqData.setMsgDesc("Submit fail.");
					reqData.setMsgCode("0014");
			//	}
			//} else {
				
//				ret = UserDao.update(reqData, aConn);
//				
//				if(ret != 0){
//					reqData.setDesc("Updated successfully.");
//					reqData.setCode("0000");
//				}else {
//					reqData.setDesc("Updated fail.");
//					reqData.setCode("0014");
//				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			ServerUtil.closeConnection(aConn);
		}
		return ret;
	}
	
//	public UserData deleteUser(UserData reqData){
//		UserData response = new UserData();
//		
//		Connection conn = null;
//		conn=ConnAdmin.getConn("001", "");
//		UserDao dao=new UserDao();
//					
//			try {
//				if(conn != null){	
//					System.out.println("Connection OK!");						
//					if(dao.deleteUser(reqData.getAutokey(),conn)){
//					response.setCode("0000");
//					response.setDesc("Deleted successfully.");
//					}
//					else{			
//					response.setCode("0014");
//					response.setDesc("Deleted fail");
//					}
//				}
//			
//			} catch (SQLException e) {			
//				e.printStackTrace();		
//				response.setDesc("Internal server error");
//			}finally {
//				ServerUtil.closeConnection(conn);
//			}	
//		
//		return response;
//	}
//	
//	public UserList getUserList() {
//		UserList response = null;
//
//		Connection conn = null;
//
//		try {
//			conn = ConnAdmin.getConn("001", "");
//			response = new UserDao().getUserList(conn);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			ServerUtil.closeConnection(conn);
//		}
//
//		return response;
//	}

	
}
