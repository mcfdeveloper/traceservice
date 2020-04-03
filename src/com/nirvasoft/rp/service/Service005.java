package com.nirvasoft.rp.service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.nirvasoft.rp.shared.RequestFromIonic;
import com.nirvasoft.rp.shared.ResponseFromService;
import com.nirvasoft.rp.shared.UserData;
import com.nirvasoft.rp.shared.UserList;
import com.nirvasoft.rp.dao.DAOManager;
import com.nirvasoft.rp.mgr.UserMgr;

@Path("/service005")
public class Service005 {
	@Context
	HttpServletRequest request;
	public void getPath() {
		DAOManager.AbsolutePath = request.getServletContext().getRealPath("/") + "/";
	}

	@Context
	private HttpServletResponse response;

	@javax.ws.rs.core.Context
	ServletContext context;
	
//	private void getPath() {
//		//ServerSession.serverPath = request.getServletContext().getRealPath("/") + "/";
//		com.nirvasoft.rp.core.SingletonServer.setAbsPath(request.getServletContext().getRealPath("/") + "/");
//	}

	@POST
	@Path("loginUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseFromService login(RequestFromIonic res) {
		getPath();
		
		ResponseFromService l_res = new ResponseFromService();

		l_res = validateLogin(res);

		System.out.println(l_res);

		return l_res;
	}

	public ResponseFromService validateLogin(RequestFromIonic req) {
		ResponseFromService res = new ResponseFromService();

		if (req.getSessionid() == null || req.getSessionid().trim().equals("")) {
			res.setCode("0014");
			res.setDesc("Invalid Session ID.");
		} else {
			res.setCode("0000");
			res.setDesc("Success Session ID.");
		}

		return res;
	}
	
	@POST
	@Path("saveUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserList saveUser(UserList aData) {	
		getPath();
	
		long l_rs = new UserMgr().saveUser(aData);

		if (l_rs != 0) {
			aData.setMsgDesc(aData.getMsgDesc());
			aData.setMsgCode(aData.getMsgCode());
			//aData.setAutokey(aData.getAutokey());
		} else {
			aData.setMsgDesc(aData.getMsgDesc());
			aData.setMsgCode(aData.getMsgCode());
		}
	
		return aData;
	}
	
//	@POST
//	@Path("deleteUser")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public UserData deleteUser(UserData aData){
//
//		UserData res=new UserData();
//		res=new UserMgr().deleteUser(aData);
//		
//		return res;
//	}
//	
//	@POST
//	@Path("getUserList")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public UserList getUserList(UserData request) {
//		UserList response = null;
//
//		response = new UserMgr().getUserList();
//
//		return response;
//	}

}
