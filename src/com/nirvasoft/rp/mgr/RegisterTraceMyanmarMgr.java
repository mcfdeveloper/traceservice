package com.nirvasoft.rp.mgr;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.nirvasoft.rp.dao.RegisterTraceMyanmarDao;
import com.nirvasoft.rp.framework.ConnAdmin;
import com.nirvasoft.rp.shared.RegisterCheckMgrResponse;
import com.nirvasoft.rp.shared.RegisterCheckRequest;
import com.nirvasoft.rp.shared.RegisterInsertResponse;
import com.nirvasoft.rp.shared.RegisterMgrResponse;
import com.nirvasoft.rp.shared.RegisterOtpSentResponse;
import com.nirvasoft.rp.shared.RegisterUpdateResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RegisterTraceMyanmarMgr {

	public RegisterCheckMgrResponse checkPhoneNo(String phoneNo) {
		RegisterCheckMgrResponse res = new RegisterCheckMgrResponse();
		boolean isExist = false;
		RegisterTraceMyanmarDao l_dao = new RegisterTraceMyanmarDao();

		Connection conn = null;

		try {
			conn = ConnAdmin.getConn("001", "");

			isExist = l_dao.checkPhoneNo(phoneNo, conn);

			if (isExist) {
				res.setCode("0000");
				res.setDesc("Selected successfully.");
				res.setExist(true);
			} else {
				res.setCode("0000");
				res.setDesc("Selected successfully.");
				res.setExist(false);
			}
		} catch (Exception e) {
			e.printStackTrace();

			res.setCode("0014");
			res.setDesc("Selected fail.");
			res.setError(e.getMessage());
			res.setExist(false);
		} finally {
			try {
				if (conn != null) {
					if (!conn.isClosed())
						conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return res;
	}

	public RegisterMgrResponse checkPhoneNoAndOtp(String phoneNo, String otp) {
		RegisterMgrResponse res = new RegisterMgrResponse();
		boolean isExist = false;
		RegisterTraceMyanmarDao l_dao = new RegisterTraceMyanmarDao();

		Connection conn = null;

		try {
			conn = ConnAdmin.getConn("001", "");

			isExist = l_dao.checkPhoneNoAndOtp(phoneNo, otp, conn);

			if (isExist) {
				res.setCode("0000");
				res.setDesc("Selected successfully.");
				res.setExist(true);
			} else {
				res.setCode("0000");
				res.setDesc("Selected successfully.");
				res.setExist(false);
			}
		} catch (Exception e) {
			e.printStackTrace();

			res.setCode("0014");
			res.setDesc("Selected fail.");
			res.setError(e.getMessage());
			res.setExist(false);
		} finally {
			try {
				if (conn != null) {
					if (!conn.isClosed())
						conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return res;
	}

	@SuppressWarnings("unchecked")
	public RegisterOtpSentResponse sendSMS(String url, String phoneNo, String message) {
		RegisterOtpSentResponse res = new RegisterOtpSentResponse();
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(url);
			ClientResponse response = null;
			JSONObject obj = new JSONObject();
			obj.put("phoneNo", "0" + phoneNo.substring(3));
			obj.put("message", message);
			response = webResource.type("application/json").post(ClientResponse.class, obj.toString());
			String jsonStr = response.getEntity(String.class);
			JSONParser parser = new JSONParser();
			Object object = parser.parse(jsonStr);
			JSONObject jsonObject = (JSONObject) object;
			String responseMessage = "";
			responseMessage = (String) jsonObject.get("responseMessage");
			if (responseMessage != null && responseMessage.equals("Send Message Successfully for Modem")) {
				res.setCode("0000");
				res.setDesc("Sent successfully.");
			} else {
				res.setCode("0014");
				res.setDesc("Sent fail.");
			}
		} catch (Exception e) {
			e.printStackTrace();

			res.setCode("0014");
			res.setDesc("Sent fail.");
			res.setError(e.getMessage());
		}

		return res;
	}

	public String generateOTP() {
		String num_list = "0123456789";
		int length = 6;
		char ch;
		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < length; i++) {
			Random randomGenerator = new Random();
			int number = 0;
			number = randomGenerator.nextInt(num_list.length());
			ch = num_list.charAt(number);

			randStr.append(ch);
		}
		String otpcode = String.valueOf(randStr);
		return otpcode;
	}

	public RegisterInsertResponse insertRegister(RegisterCheckRequest reqObj, String otp) {
		RegisterInsertResponse res = new RegisterInsertResponse();
		RegisterTraceMyanmarDao l_dao = new RegisterTraceMyanmarDao();
		Connection conn = null;

		try {
			conn = ConnAdmin.getConn("001", "");

			String today = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());

			boolean saveSuccess = false;
			saveSuccess = l_dao.insertRegister(reqObj, otp, today, conn);

			if (saveSuccess) {
				res.setCode("0000");
				res.setDesc("Saved successfully.");
			} else {
				res.setCode("0014");
				res.setDesc("Saved fail.");
			}
		} catch (Exception e) {
			e.printStackTrace();

			res.setCode("0014");
			res.setDesc("Saved fail.");
			res.setError(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					if (!conn.isClosed())
						conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return res;
	}

	public RegisterUpdateResponse updateRegister(String phoneNo, String otp) {
		RegisterUpdateResponse res = new RegisterUpdateResponse();
		RegisterTraceMyanmarDao l_dao = new RegisterTraceMyanmarDao();
		Connection conn = null;

		try {
			conn = ConnAdmin.getConn("001", "");

			String today = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());

			boolean saveSuccess = false;
			saveSuccess = l_dao.updateRegister(phoneNo, otp, today, conn);

			if (saveSuccess) {
				res.setCode("0000");
				res.setDesc("Updated successfully.");
			} else {
				res.setCode("0014");
				res.setDesc("Updated fail.");
			}
		} catch (Exception e) {
			e.printStackTrace();

			res.setCode("0014");
			res.setDesc("Updated fail.");
			res.setError(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					if (!conn.isClosed())
						conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return res;
	}

	public RegisterUpdateResponse updateRegisterLocation(RegisterCheckRequest reqObj) {
		RegisterUpdateResponse res = new RegisterUpdateResponse();
		RegisterTraceMyanmarDao l_dao = new RegisterTraceMyanmarDao();
		Connection conn = null;

		try {
			conn = ConnAdmin.getConn("001", "");

			String today = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());

			boolean saveSuccess = false;
			saveSuccess = l_dao.updateRegisterLocation(reqObj, today, conn);

			if (saveSuccess) {
				res.setCode("0000");
				res.setDesc("Updated successfully.");
			} else {
				res.setCode("0014");
				res.setDesc("Updated fail.");
			}
		} catch (Exception e) {
			e.printStackTrace();

			res.setCode("0014");
			res.setDesc("Updated fail.");
			res.setError(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					if (!conn.isClosed())
						conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return res;
	}

}
