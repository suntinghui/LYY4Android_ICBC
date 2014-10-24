package com.people.icbc.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.WindowManager;

import com.people.icbc.R;

public class SplashActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //设置全屏
		
		setContentView(R.layout.activity_splash);
		
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
			Intent intent0 = new Intent(SplashActivity.this, HomepageActivity.class);
			SplashActivity.this.startActivity(intent0);
			SplashActivity.this.finish();
		}

	}

}
