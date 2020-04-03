package com.nirvasoft.rp.service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

import com.nirvasoft.rp.dao.DAOManager;
import com.nirvasoft.rp.dao.UserDao;
import com.nirvasoft.rp.mgr.NotiMgr;
import com.nirvasoft.rp.shared.FleepListDataset;
import com.nirvasoft.rp.shared.NotiData;
import com.nirvasoft.rp.shared.NotiResponse;
import com.nirvasoft.rp.shared.NotificationDataSet;
import com.nirvasoft.rp.shared.ResponseData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.net.ssl.HttpsURLConnection;

import org.json.JSONException;
import org.json.JSONObject;


@Path("/serviceNoti")

public class ServiceNoti {
	@Context
	HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	@javax.ws.rs.core.Context
	ServletContext context;
	public static final String FCM_URL = "https://fcm.googleapis.com/fcm/send";
	public void getPath() {
		DAOManager.AbsolutePath = request.getServletContext().getRealPath("/") + "/";
	}

	@POST
	@Path("getAllNotification")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public NotiResponse getAllNotification(NotificationDataSet request) {
		getPath();
		NotificationDataSet response = null;
		NotiResponse res=new NotiResponse();
		ResponseData sessionState = new ResponseData();
			response = new NotiMgr().getAllNoti(request.getDivision().trim(),request.getDistrict().trim(),request.getTownship());
			res=callFcmFirebase(response,request.getTitle(),request.getDescription());
			return res;
	}
	
	public NotiResponse callFcmFirebase(NotificationDataSet req,String title,String description) {
		getPath();
		NotiResponse response=new NotiResponse();
		//Connection conn = null;
		String fcmServerKey;
		//conn = ConnAdmin.getConn("001", "");
		fcmServerKey = "AAAAzuhKstY:APA91bGLFsh32EJoFGDpiEPVWC48nCIz5VNtRqZRmg67nPc-VQgB8357_OElxt_m5cqDdKLo-4UvT1ghDmELdygFVQsEFQcbvvIdd14V2K3Rvp5ATRLpH2QB4LoSweFMjqNJP-DR1Dgf";
		int responseCode = -1;
		String responseBody = "";
		

		try {
			
			for(int i=0;i<req.getData().length;i++){
				//ret =  UserDao.insert(reqData.getData()[i], aConn);
				byte[] postData = getPostTokenData(req.getData()[i],title,description);
			//}
			//byte[] postData = getPostTokenData(req);

			URL url = new URL(FCM_URL);
			HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();
			// System.out.println("FCM URL : " + url);
			// set timeputs to 10 seconds
			httpURLConnection.setConnectTimeout(10000);
			httpURLConnection.setReadTimeout(10000);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("Content-Type",
					"application/json;application/x-www-form-urlencoded;charset=UTF-8");
			httpURLConnection.setRequestProperty("Content-Length", Integer.toString(postData.length));
//			httpURLConnection.setRequestProperty("Authorization", "key=" + FCM_SERVER_API_KEY);
			httpURLConnection.setRequestProperty("Authorization", "key=" + fcmServerKey);

			OutputStream out = httpURLConnection.getOutputStream();
			out.write(postData);
			out.close();
			responseCode = httpURLConnection.getResponseCode();
			System.out.println("FCM Call: "+responseCode);

			if (responseCode == 200) { // success
				responseBody = convertStreamToString(httpURLConnection.getInputStream());
				response.setCode("0000");
				response.setDesc("Sent successfully.");
				response.setResponseCode(String.valueOf(responseCode));
				response.setResponseBody(responseBody);
			} else { // failure
				responseBody = convertStreamToString(httpURLConnection.getErrorStream());
				response.setCode("0014");
				response.setDesc("Sent fail.");
				response.setResponseCode(String.valueOf(responseCode));
				response.setResponseBody(responseBody);
			}
			if (response.getCode().equals("0000") && response.getResponseCode().equals("200")) {
//				new NotiDao().updateFCMResponse("sent", response.getResponseCode(), response.getResponseBody(),
//						req.getAutokey(), conn);
			}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();

			// System.out.println("FCM IOException : " + ioe.getMessage());

			response.setCode("0014");
			response.setDesc(ioe.getMessage());
			response.setResponseCode(String.valueOf(responseCode));
			response.setResponseBody(responseBody);
		} catch (Exception e) {
			e.printStackTrace();

			// System.out.println("FCM Exception : " + e.getMessage());

			response.setCode("0014");
			response.setDesc(e.getMessage());
			response.setResponseCode(String.valueOf(responseCode));
			response.setResponseBody(responseBody);
		}
		return response;
		
	}
	public static String convertStreamToString(InputStream inStream) throws Exception {
		
		InputStreamReader inputStream = new InputStreamReader(inStream);
		BufferedReader bReader = new BufferedReader(inputStream);

		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = bReader.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}
	private static byte[] getPostTokenData(NotiData data,String title,String description) throws JSONException {
		HashMap<String, String> dataMap = new HashMap<>();
		dataMap.put("title","title");
		dataMap.put("body","description");
		JSONObject contentObj = new JSONObject(dataMap);
		JSONObject finalObj = new JSONObject();
		finalObj.put("notification", contentObj);
		finalObj.put("to",data.getToken());
		// finalObj.put("priority", "high");
		// finalObj.put("content_available", true);
		// finalObj.put("mutable-content", true);
		// finalObj.put("collapse_key", "Updates Available");
		// System.out.println(finalObj.toString());
		return finalObj.toString().getBytes(Charset.forName("UTF-8"));
	}

}
