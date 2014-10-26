package com.people.icbc.activity;

import com.people.icbc.R;
import com.people.icbc.client.Constants;
import com.people.icbc.sqlite.DataDao;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class HomepageActivity extends BaseActivity implements OnClickListener {
	public DataDao dao = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN); // 设置全屏

		setContentView(R.layout.activity_homepage);

		Button btn1 = (Button) this.findViewById(R.id.btn1);
		btn1.getBackground().setAlpha(50);
		btn1.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn1:
			initView();
			break;

		}
	}

	public void initView() {
		dao = new DataDao(getApplication());
		if (dao.find(Constants.LOGGED)) {
			Intent intent = new Intent(HomepageActivity.this,
					LockScreenActivity.class);
			HomepageActivity.this.startActivity(intent);
			HomepageActivity.this.finish();
		} else {
			Intent intent0 = new Intent(HomepageActivity.this,
					LoginActivity.class);
			HomepageActivity.this.startActivity(intent0);
			HomepageActivity.this.finish();
		}

	}
}
