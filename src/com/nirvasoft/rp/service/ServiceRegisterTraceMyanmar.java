package com.nirvasoft.rp.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.nirvasoft.rp.mgr.RegisterTraceMyanmarMgr;
import com.nirvasoft.rp.dao.DAOManager;
import com.nirvasoft.rp.shared.RegisterCheckMgrResponse;
import com.nirvasoft.rp.shared.RegisterCheckRequest;
import com.nirvasoft.rp.shared.RegisterCheckResponse;
import com.nirvasoft.rp.shared.RegisterInsertResponse;
import com.nirvasoft.rp.shared.RegisterMgrResponse;
import com.nirvasoft.rp.shared.RegisterOtpSentResponse;
import com.nirvasoft.rp.shared.RegisterRequest;
import com.nirvasoft.rp.shared.RegisterResponse;
import com.nirvasoft.rp.shared.RegisterSmsConfigResponse;
import com.nirvasoft.rp.shared.RegisterUpdateResponse;
import com.nirvasoft.rp.shared.ValidationResponse;
import com.nirvasoft.rp.util.ServerGlobal;
import com.nirvasoft.rp.util.ServerUtil;

@Path("/serviceRegisterTraceMyanmar")
public class ServiceRegisterTraceMyanmar {

	@Context
	HttpServletRequest request;

	public void getPath() {
		DAOManager.AbsolutePath = request.getServletContext().getRealPath("/") + "/";
	}

	@POST
	@Path("register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RegisterResponse register(RegisterRequest reqObj) {
		getPath();

		RegisterResponse resObj = new RegisterResponse();

		ValidationResponse valid1 = new ValidationResponse();
		valid1 = validatePhoneNoAndOtp(reqObj.getPhoneNo(), reqObj.getOtp());
		if (!valid1.getCode().equals("0000")) {
			resObj.setCode(valid1.getCode());
			resObj.setDesc(valid1.getDesc());

			return resObj;
		}

		RegisterTraceMyanmarMgr mgr = new RegisterTraceMyanmarMgr();
		RegisterMgrResponse res1 = new RegisterMgrResponse();
		res1 = mgr.checkPhoneNoAndOtp(reqObj.getPhoneNo().trim(), reqObj.getOtp().trim());

		if (!res1.getCode().equals("0000")) {
			resObj.setCode(res1.getCode());
			resObj.setDesc(res1.getDesc());
			resObj.setError(res1.getError());

			return resObj;
		} else {
			if (res1.isExist()) {
				RegisterUpdateResponse res2 = new RegisterUpdateResponse();
				if (reqObj.getPhoneNo() != null && !reqObj.getPhoneNo().trim().equals("")) {
					reqObj.setPhoneNo(reqObj.getPhoneNo().trim());
				}
				if (reqObj.getOtp() != null && !reqObj.getOtp().trim().equals("")) {
					reqObj.setOtp(reqObj.getOtp().trim());
				}
				res2 = mgr.updateRegister(reqObj.getPhoneNo(), reqObj.getOtp());
				if (!res2.getCode().equals("0000")) {
					resObj.setCode(res2.getCode());
					resObj.setDesc(res2.getDesc());
					resObj.setError(res2.getError());

					return resObj;
				} else {
					resObj.setCode(res2.getCode());
					resObj.setDesc("Registered successfully.");
				}
			} else {
				resObj.setCode(res1.getCode());
				resObj.setDesc("Your Otp is wrong.");

				return resObj;
			}
		}

		return resObj;
	}

	@POST
	@Path("checkRegister")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RegisterCheckResponse checkRegister(RegisterCheckRequest reqObj) {
		getPath();
		RegisterCheckResponse resObj = new RegisterCheckResponse();
		ValidationResponse valid1 = new ValidationResponse();
		valid1 = this.validatePhoneNo(reqObj.getPhoneNo());
		if (!valid1.getCode().equals("0000")) {
			resObj.setCode(valid1.getCode());
			resObj.setDesc(valid1.getDesc());
			return resObj;
		}

		RegisterTraceMyanmarMgr mgr = new RegisterTraceMyanmarMgr();
		RegisterCheckMgrResponse res1 = new RegisterCheckMgrResponse();
		res1 = mgr.checkPhoneNo(reqObj.getPhoneNo().trim());
		if (!res1.getCode().equals("0000")) {
			resObj.setCode(res1.getCode());
			resObj.setDesc(res1.getDesc());
			resObj.setError(res1.getError());

			return resObj;
		} else {
			if (res1.isExist()) {
				resObj.setCode("0001");
				resObj.setDesc("Phone No. already registered.");

				return resObj;
			} else {
				String otp = mgr.generateOTP();
				RegisterInsertResponse res2 = new RegisterInsertResponse();
				if (reqObj.getPhoneNo() != null && !reqObj.getPhoneNo().trim().equals("")) {
					reqObj.setPhoneNo(reqObj.getPhoneNo().trim());
				}
				if (reqObj.getDivision() != null && !reqObj.getDivision().trim().equals("")) {
					reqObj.setDivision(reqObj.getDivision().trim());
				}
				if (reqObj.getDistrict() != null && !reqObj.getDistrict().trim().equals("")) {
					reqObj.setDistrict(reqObj.getDistrict().trim());
				}
				if (reqObj.getTownship() != null && !reqObj.getTownship().trim().equals("")) {
					reqObj.setTownship(reqObj.getTownship().trim());
				}
				res2 = mgr.insertRegister(reqObj, otp);
				if (!res2.getCode().equals("0000")) {
					resObj.setCode(res2.getCode());
					resObj.setDesc(res2.getDesc());
					resObj.setError(res2.getError());

					return resObj;
				} else {
					String url = "";
					RegisterSmsConfigResponse res3 = new RegisterSmsConfigResponse();
					res3 = ServerUtil.readSMSConig();
					if (!res3.getCode().equals("0000")) {
						resObj.setCode(res3.getCode());
						resObj.setDesc(res3.getDesc());
						resObj.setError(res3.getError());

						return resObj;
					} else {
						url = ServerGlobal.getSendOtpUrl();
					}

					RegisterOtpSentResponse res4 = new RegisterOtpSentResponse();
					res4 = mgr.sendSMS(url, reqObj.getPhoneNo().trim(), "Your register code is " + otp + ".");
					if (!res4.getCode().equals("0000")) {
						resObj.setCode(res4.getCode());
						resObj.setDesc(res4.getDesc());
						resObj.setError(res4.getError());

						return resObj;
					} else {
						resObj.setCode(res4.getCode());
						resObj.setDesc(res4.getDesc());
					}
				}
			}
		}

		return resObj;
	}

	private ValidationResponse validatePhoneNo(String phoneNo) {
		ValidationResponse obj = new ValidationResponse();

		if (phoneNo != null && !phoneNo.trim().equals("")) {
			obj.setCode("0000");
			obj.setDesc("Validation is successful.");
		} else {
			obj.setCode("0014");
			obj.setDesc("Invalid Phone No.");
		}

		return obj;
	}

	private ValidationResponse validatePhoneNoAndOtp(String phoneNo, String otp) {
		ValidationResponse obj = new ValidationResponse();

		if (phoneNo == null || phoneNo.trim().equals("")) {
			obj.setCode("0014");
			obj.setDesc("Invalid Phone No.");
			return obj;
		}

		if (otp == null || otp.trim().equals("")) {
			obj.setCode("0014");
			obj.setDesc("Invalid Otp.");

			return obj;
		}

		obj.setCode("0000");
		obj.setDesc("Validation is successful.");

		return obj;
	}

	private ValidationResponse validateAll(RegisterCheckRequest reqObj) {
		ValidationResponse obj = new ValidationResponse();

		if (reqObj.getPhoneNo() == null || reqObj.getPhoneNo().trim().equals("")) {
			obj.setCode("0014");
			obj.setDesc("Invalid Phone No.");

			return obj;
		}

		if (reqObj.getDivision() == null || reqObj.getDivision().trim().equals("")) {
			obj.setCode("0014");
			obj.setDesc("Invalid Division.");

			return obj;
		}

		if (reqObj.getDistrict() == null || reqObj.getDistrict().trim().equals("")) {
			obj.setCode("0014");
			obj.setDesc("Invalid District.");

			return obj;
		}

		if (reqObj.getTownship() == null || reqObj.getTownship().trim().equals("")) {
			obj.setCode("0014");
			obj.setDesc("Invalid Township.");

			return obj;
		}

		obj.setCode("0000");
		obj.setDesc("Validation is successful.");

		return obj;
	}

	@POST
	@Path("updateRegister")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RegisterResponse updateRegister(RegisterCheckRequest reqObj) {
		getPath();

		RegisterResponse resObj = new RegisterResponse();

		ValidationResponse valid1 = new ValidationResponse();
		valid1 = validateAll(reqObj);
		if (!valid1.getCode().equals("0000")) {
			resObj.setCode(valid1.getCode());
			resObj.setDesc(valid1.getDesc());

			return resObj;
		}

		if (reqObj.getPhoneNo() != null && !reqObj.getPhoneNo().trim().equals("")) {
			reqObj.setPhoneNo(reqObj.getPhoneNo().trim());
		}
		if (reqObj.getDivision() != null && !reqObj.getDivision().trim().equals("")) {
			reqObj.setDivision(reqObj.getDivision().trim());
		}
		if (reqObj.getDistrict() != null && !reqObj.getDistrict().trim().equals("")) {
			reqObj.setDistrict(reqObj.getDistrict().trim());
		}
		if (reqObj.getTownship() != null && !reqObj.getTownship().trim().equals("")) {
			reqObj.setTownship(reqObj.getTownship().trim());
		}
		RegisterTraceMyanmarMgr mgr = new RegisterTraceMyanmarMgr();
		RegisterUpdateResponse res2 = new RegisterUpdateResponse();
		res2 = mgr.updateRegisterLocation(reqObj);
		if (!res2.getCode().equals("0000")) {
			resObj.setCode(res2.getCode());
			resObj.setDesc(res2.getDesc());
			resObj.setError(res2.getError());

			return resObj;
		} else {
			resObj.setCode(res2.getCode());
			resObj.setDesc("Updated successfully.");
		}

		return resObj;
	}

}
