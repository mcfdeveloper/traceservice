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

import com.nirvasoft.rp.shared.DivisionData;
import com.nirvasoft.rp.shared.DivisionResult;
import com.nirvasoft.rp.shared.FleepListDataset;
import com.nirvasoft.rp.shared.FleepRequest;
import com.nirvasoft.rp.shared.FleepResponse;
import com.nirvasoft.rp.shared.NotificationDataSet;
import com.nirvasoft.rp.shared.RequestFromIonic;
import com.nirvasoft.rp.shared.ResponseFromService;
import com.nirvasoft.rp.shared.UserData;
import com.nirvasoft.rp.shared.UserList;
import com.nirvasoft.rp.dao.DAOManager;
import com.nirvasoft.rp.mgr.DivisionMgr;
import com.nirvasoft.rp.mgr.NotiMgr;

@Path("/service004")
public class Service004 {
	@Context
	HttpServletRequest request;

	@Context
	private HttpServletResponse response;
	public void getPath() {
		DAOManager.AbsolutePath = request.getServletContext().getRealPath("/") + "/";
	}

	@javax.ws.rs.core.Context
	ServletContext context;
	
	@POST
	@Path("getDivisionList")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public DivisionResult getUserList(DivisionData request) {
		getPath();
		DivisionResult response = null;

		response = new DivisionMgr().getDivision();

		return response;
	}
	@POST
	@Path("saveFleep")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public FleepResponse saveFleepData(FleepRequest request) {
		getPath();
		FleepResponse response = null;
		response = new DivisionMgr().saveFleep(request);

		return response;
	}
	@POST
	@Path("getAllFleepList")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public FleepListDataset getAllFleep(FleepListDataset request) {
		getPath();
		FleepListDataset response = null;
			response = new NotiMgr().getAllFleepData(request.getPhneno().trim());
		
			return response;
	}
	
}
