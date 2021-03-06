package com.people.icbc.activity;

import com.people.icbc.client.Constants;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class DemoReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.e("SOTP", "收到回应。。。");

		String type = intent.getStringExtra("SOTP");

		if (type.equals("genTOKEN")) {
			if (Constants.SHOP_ONLINE) {
				Log.e("SOTPSHOP", intent.getStringExtra("key").trim());
				Intent intent2 = new Intent(context, ConfirmOrderActivity.class);
				intent2.putExtra("token", intent.getStringExtra("key").trim());
				intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent2);
			} else if (Constants.GENTOKEN_ONLINE) {
				Log.e("SOTPONLINE", intent.getStringExtra("key").trim());
				Intent intent0 = new Intent(context,
						OnlineAccountsInfoActivity.class);
				intent0.putExtra("token", intent.getStringExtra("key").trim());
				intent0.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent0);
			} else if (Constants.FACE_PAY) {
				Log.e("FACEPAY", intent.getStringExtra("key").trim());
				Intent intent1 = new Intent(context, FacePayActivity.class);
				intent1.putExtra("token", intent.getStringExtra("key").trim());
				intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent1);
			} else {
				Log.e("SOTPOFFLINE", intent.getStringExtra("key").trim());
				Intent intent1 = new Intent(context, AccountsInfoActivity.class);
				intent1.putExtra("token", intent.getStringExtra("key").trim());
				intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent1);
			}

		}

	}

}
