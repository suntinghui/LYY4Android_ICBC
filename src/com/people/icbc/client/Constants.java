package com.people.icbc.client;

public class Constants {

	// 当前系统的版本号
	public static final int VERSION = 1;

	public static final String AESKEY = "dynamicode";

	public static final String APPFILEPATH = "/data/data/"
			+ ApplicationEnvironment.getInstance().getApplication()
					.getPackageName();

	// assets下的文件保存路径
	public static final String ASSETSPATH = APPFILEPATH + "/assets/";

	public static final String kUSERNAME = "kUSERNAME";
	public static final String kPASSWORD = "kPASSWORD";
	public static final String kLOCKKEY = "LockKey";
	public static final String kGESTRUECLOSE = "GestureClose";

	public static final String kVERSION = "VERSION";

	public static final String kACCOUNTLIST = "kACCOUNTLIST";

	public static final String IP = "http://111.198.29.38:6443";

	public static boolean GENTOKEN_ONLINE = true;
	public static boolean SHOP_ONLINE = true;
	public static boolean FACE_PAY = true;

	public static final int OVERTIME = 20;// 超时时间

	public static boolean HASSETBLUETOOTH = false;

	public static String LOGGED = "Logged";

	public static String SOTPPACKET = "com.people.sotp.service";

	public static String resultString = "";

	public static String URL;
	public static String VERSION2 = "";

	public static String resultCode;
	public static String resultBalance;

	public static String PSW_DIALOG;
	public static String FACEPAY_CARD;
	public static String FACERECEIVE_CADE;
	public static String FACE_SUM;
	public static String FACE_SUM2 = "FACE_SUM2";


}
