package com.nirvasoft.rp.framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import com.nirvasoft.rp.dao.DAOManager;
import com.nirvasoft.database.ConnMgr;
import com.nirvasoft.rp.shared.Constant;
import com.nirvasoft.rp.util.FileUtil;
import com.nirvasoft.rp.util.ReadFile;
import com.nirvasoft.rp.util.ServerUtil;

import password.DESedeEncryption;

public class ConnAdmin {

	public static String servername = "";

	public static String port = "";
	public static String instance = "";
	public static String dbname = "";
	public static String dbUsr = "";
	public static String dbPwd = "";
	public static String connType = "";
	static String path = "";
	static String url = "";
	static DESedeEncryption myEncryptor;

//	public static Connection getAnotherConn(String fileName, String oId) {
//
//		Connection conn = null;
//		if (!oId.equals("")) {
//			readAnotherConnectionString(fileName, oId);
//			conn = (new ConnMgr(servername, Integer.parseInt(port), instance, dbname, dbUsr, dbPwd,
//					Integer.parseInt(connType))).getConn();
//		}
//		return conn;
//	}
	

	public static Connection getConn(String oId, String externalpath) {
		Connection ret = null;
		String driver = "", url = "", userID = "", password = "";
		try {
			myEncryptor = new DESedeEncryption();
			ArrayList<String> oracleConnList;
			oracleConnList = ReadFile.readConnection(DAOManager.AbsolutePath + "WEB-INF//reference//TraceConfig.txt");
			//oracleConnList = ReadFile.readConnection(DAOManager.AbsolutePath + "WEB-INF//data//ConnectionConfig.txt");
			if (oracleConnList.size() > 0) {
				driver = oracleConnList.get(0).split("Driver:")[1];
				url = oracleConnList.get(1).split("URL:")[1];
				userID = oracleConnList.get(2).split("UserName:")[1];
				password = oracleConnList.get(3).split("Password:")[1];
			}
			Class.forName(driver);
			ret = DriverManager.getConnection(url, userID, myEncryptor.decrypt(password));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

//	public static Connection getConn(String oId, String externalpath) {
//		Connection ret = null;
//		String driver = "", url = "", userID = "", password = "";
//		try {
//			myEncryptor = new DESedeEncryption();
//			ArrayList<String> oracleConnList;
////			oracleConnList = ReadFile.readConnection(
////					System.getenv(ConnAdmin.readEnvConfig("envName1", "")) + "/data/" + "ConnectionConfig.txt");
////			if (oracleConnList.size() > 0) {
//				driver = "net.sourceforge.jtds.jdbc.Driver";  //oracleConnList.get(0).split("Driver:")[1];
//				url = "jdbc:jtds:sqlserver://52.187.13.89:1433/TrackDB;instance=MOBILECLOUD";   //oracleConnList.get(1).split("URL:")[1];
//				userID = "sa";   //oracleConnList.get(2).split("UserName:")[1];
//				password = "HwPV+v0uOs0=";  //oracleConnList.get(3).split("Password:")[1];
//			//}
//			Class.forName(driver);
//			ret = DriverManager.getConnection(url, userID, myEncryptor.decrypt(password));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return ret;
//	}

	
	
//	public static Connection getConnV1(String oId, String externalpath) {
//		Connection conn = null;
//		if (!oId.equals("")) {
//			readConnectionString(oId, externalpath);
//			conn = (new ConnMgr(servername, Integer.parseInt(port), instance, dbname, dbUsr, dbPwd,
//					Integer.parseInt(connType))).getConn();
//		}
//		return conn;
//	}
//
//	public static Connection getOracleConn(String oId) {
//
//		Connection conn = null;
//		if (!oId.equals("")) {
//			readOracleConnectionString(oId);
//			conn = (new ConnMgr(servername, Integer.parseInt(port), instance, dbname, dbUsr, dbPwd,
//					Integer.parseInt(connType))).getConn();
//		}
//		return conn;
//	}
//
//	private static void readAnotherConnectionString(String fileName, String pOID) {
//		String l_ret = "";
//		ArrayList<String> arl = new ArrayList<String>();
//		path = ServerSession.serverPath + "data//" + fileName;
//
//		try {
//			arl = FileUtil.readFile(path);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		for (int i = 0; i < arl.size(); i++) {
//			if (!arl.get(i).equals("")) {
//				if (arl.get(i).startsWith(pOID)) {
//					l_ret = arl.get(i);
//					break;
//				}
//			}
//		}
//		String[] l_split = l_ret.split(",");
//		servername = l_split[1];
//		port = l_split[2];
//		instance = l_split[3];
//		dbname = l_split[4];
//		dbUsr = l_split[5];
//		dbPwd = ServerUtil.decryptPIN(l_split[6]);
//		connType = l_split[7];
//	}
//
//	public static String readConfig(String param) {
//		String l_ret = "";
//		ArrayList<String> arl = new ArrayList<String>();
//		String path = System.getenv(ConnAdmin.readEnvConfig("envName1", "")) + "/reference/config.txt";
//		try {
//			arl = FileUtil.readFile(path);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		for (int i = 0; i < arl.size(); i++) {
//			if (!arl.get(i).equals("")) {
//				if (arl.get(i).startsWith(param)) {
//					l_ret = arl.get(i);
//					break;
//				}
//			}
//		}
//		String[] l_split = l_ret.split(":");
//		if (!l_split[0].equals("")) {
//			return l_ret = l_split[1];
//		}
//		return l_ret;
//	}
//
//	private static void readConnectionString(String pOID, String externalpath) {
//		String l_ret = "";
//		ArrayList<String> arl = new ArrayList<String>();
//		if (externalpath.equals("")) {
//			path = ServerSession.serverPath + "data//ConncetionConfig.txt";
//		} else {
//			path = externalpath + "data//ConncetionConfig.txt";
//		}
//
//		try {
//			arl = FileUtil.readFile(path);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		for (int i = 0; i < arl.size(); i++) {
//			if (!arl.get(i).equals("")) {
//				if (arl.get(i).startsWith(pOID)) {
//					l_ret = arl.get(i);
//					break;
//				}
//			}
//		}
//		String[] l_split = l_ret.split(",");
//		servername = l_split[1];
//		port = l_split[2];
//		instance = l_split[3];
//		dbname = l_split[4];
//		dbUsr = l_split[5];
//		dbPwd = ServerUtil.decryptPIN(l_split[6]);
//		connType = l_split[7];
//	}
//
	public static String readEnvConfig(String param, String externalpath) {
		String l_ret = "";
		ArrayList<String> arl = new ArrayList<String>();
		// externalpath = ServerSession.serverPath + "reference//config.txt";
		//externalpath = ServerSession.serverPath + "WEB-INF/" + "reference/config.txt";
//		try {
//			arl = FileUtil.readFile(externalpath);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		for (int i = 0; i < arl.size(); i++) {
			if (!arl.get(i).equals("")) {
				if (arl.get(i).startsWith(param)) {
					l_ret = arl.get(i);
					break;
				}
			}
		}
		String[] l_split = l_ret.split(":");
		if (!l_split[0].equals("")) {
			return l_ret = l_split[1];
		}
		if (l_ret.equals("")) {
			l_ret = Constant.envVar;
		}
		return l_ret;
	}

//	// suwai
//	public static String readExternalUrl(String pOID) {
//		String l_ret = "";
//		ArrayList<String> arl = new ArrayList<String>();
//		String path = System.getenv(ConnAdmin.readEnvConfig("envName1", "")) + "/data/ExternalConnectionConfig.txt";
//		try {
//			arl = FileUtil.readFile(path);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		for (int i = 0; i < arl.size(); i++) {
//			if (!arl.get(i).equals("")) {
//				if (arl.get(i).startsWith(pOID)) {
//					l_ret = arl.get(i);
//					break;
//				}
//			}
//		}
//		String[] l_split = l_ret.split("_");
//		return l_ret = l_split[1].trim();
//	}
//
//	private static void readOracleConnectionString(String pOID) {
//		String l_ret = "";
//		ArrayList<String> arl = new ArrayList<String>();
//		path = ServerSession.serverPath + "data//OracleConncetionConfig.txt";
//
//		try {
//			arl = FileUtil.readFile(path);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		for (int i = 0; i < arl.size(); i++) {
//			if (!arl.get(i).equals("")) {
//				if (arl.get(i).startsWith(pOID)) {
//					l_ret = arl.get(i);
//					break;
//				}
//			}
//		}
//		String[] l_split = l_ret.split(",");
//		servername = l_split[1];
//		port = l_split[2];
//		instance = l_split[3];
//		dbname = l_split[4];
//		dbUsr = l_split[5];
//		dbPwd = ServerUtil.decryptPIN(l_split[6]);
//		connType = l_split[7];
//	}
//
//	public ConnAdmin() {
//		super();
//
//	}
	
	}

