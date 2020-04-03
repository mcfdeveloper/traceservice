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

import com.nirvasoft.rp.shared.ContactData;
import com.nirvasoft.rp.shared.ContactResult;
import com.nirvasoft.rp.dao.DAOManager;
import com.nirvasoft.rp.mgr.ContactMgr;

@Path("/service003")
public class Service003 {
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
	@Path("getContactList")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ContactResult getContactList(ContactData request) {
		getPath();
		ContactResult response = null;

		response = new ContactMgr().getContact(request.getDivision());

		return response;
	}
}
