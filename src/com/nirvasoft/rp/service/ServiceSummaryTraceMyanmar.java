package com.nirvasoft.rp.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.nirvasoft.rp.dao.DAOManager;
import com.nirvasoft.rp.mgr.SummaryTraceMyanmarMgr;
import com.nirvasoft.rp.shared.SummaryResponse;

@Path("/serviceSummaryTraceMyanmar")
public class ServiceSummaryTraceMyanmar {

	@Context
	HttpServletRequest request;

	public void getPath() {
		DAOManager.AbsolutePath = request.getServletContext().getRealPath("/") + "/";
	}

	@GET
	@Path("getSummary")
	@Produces(MediaType.APPLICATION_JSON)
	public SummaryResponse getSummary() {
		getPath();

		SummaryResponse resObj = new SummaryResponse();
		SummaryTraceMyanmarMgr mgr = new SummaryTraceMyanmarMgr();
		resObj = mgr.getSummary();

		return resObj;
	}

}
