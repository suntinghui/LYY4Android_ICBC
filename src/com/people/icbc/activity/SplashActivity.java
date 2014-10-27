package com.people.icbc.activity;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.people.icbc.R;
import com.people.icbc.client.ApplicationEnvironment;
import com.people.icbc.client.Constants;

public class SplashActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN); // 设置全屏

		setContentView(R.layout.activity_splash);
		// 用来清空当面付余额记录的
//		Editor editor = ApplicationEnvironment.getInstance().getPreferences()
//				.edit();
//		editor.putString(Constants.FACE_SUM2, null);
//		editor.commit();

		new SplashTask().execute();
	}

	class SplashTask extends AsyncTask<Object, Object, Object> {

		@Override
		protected Object doInBackground(Object... arg0) {
			try {
				Thread.sleep(1500);
				return null;

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

		}

		@Override
		protected void onPostExecute(Object result) {
			Intent intent0 = new Intent(SplashActivity.this,
					HomepageActivity.class);
			SplashActivity.this.startActivity(intent0);
			SplashActivity.this.finish();
		}

	}

}
