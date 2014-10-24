package com.people.icbc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;

import com.people.icbc.R;

public class HomepageActivity extends BaseActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // 设置全屏

		setContentView(R.layout.activity_homepage);

		Button btn1 = (Button) this.findViewById(R.id.btn1);
		btn1.getBackground().setAlpha(50);
		btn1.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn1:
			Intent intent1 = new Intent(this, LoginActivity.class);
			startActivity(intent1);
			break;

		}
	}

}
