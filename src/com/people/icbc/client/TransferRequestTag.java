package com.people.icbc.client;

import android.annotation.SuppressLint;
import java.util.HashMap;

@SuppressLint("UseSparseArrays")
public class TransferRequestTag {

	public static final int SignUp = 0; // 注册
	public static final int Login = 1; // 登录
	public static final int Accounts = 2; // 消费
	public static final int Generate = 3; // 生成TOKEN

	public static final int getAccountStr = 4;
	public static final int OnlineShop = 5;// 网上商城
	public static final int Verify = 6;// 网上商城验证
	public static final int FacePayVerify = 7;// 网上商城验证
	public static final int Transfer = 8;// 转账

	private static HashMap<Integer, String> requestTagMap = null;

	public static HashMap<Integer, String> getRequestTagMap() {
		if (null == requestTagMap) {
			requestTagMap = new HashMap<Integer, String>();

			requestTagMap.put(SignUp, Constants.IP
					+ "/sslvpn/index.php/login/register");
			requestTagMap.put(Login, Constants.IP
					+ "/sslvpn/index.php/login/login_verify");
			requestTagMap.put(Accounts, Constants.IP + "/list");
			requestTagMap.put(getAccountStr, Constants.IP + "/list");
			requestTagMap.put(Generate, Constants.IP + "/Generate/");
			requestTagMap.put(OnlineShop, Constants.IP + "/online/");
			requestTagMap.put(Verify, Constants.IP + "/checkER");
			requestTagMap.put(FacePayVerify, Constants.IP + "/checkcode");
			requestTagMap.put(Transfer, Constants.IP + "/transfer");

		}

		return requestTagMap;
	}

}
