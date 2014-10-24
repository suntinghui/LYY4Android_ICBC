package com.people.icbc.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.people.icbc.client.ApplicationEnvironment;
import com.people.icbc.client.Constants;
import com.people.icbc.sqlite.DataDao;
import com.people.icbc.util.StringUtil;
import com.people.icbc.view.GestureLockView;
import com.people.icbc.view.LocusPassWordView;
import com.people.icbc.view.GestureLockView.OnGestureFinishListener;
import com.people.icbc.view.LocusPassWordView.OnCompleteListener;
import com.people.icbc.R;

// 锁屏 设置
@SuppressLint("ResourceAsColor")
public class LockScreenSettingActivity extends BaseActivity {
	private LocusPassWordView lpwv;
	private String password;
	private boolean needverify = true;

	private String password2;
	private boolean isfirst = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setpassword_activity);
		lpwv = (LocusPassWordView) this.findViewById(R.id.mLocusPassWordView);
		lpwv.setOnCompleteListener(new OnCompleteListener() {
			@Override
			public void onComplete(String mPassword) {
				if (isfirst) {
					password = mPassword;
					showToast("请再次输入密码");
					isfirst = false;
					lpwv.clearPassword();
				} else {
					password2 = mPassword;
					if (password.equals(password2)) {
						lpwv.resetPassWord(password);
						lpwv.clearPassword();
						showToast("密码修改成功,请记住密码.");
						startActivity(new Intent(
								LockScreenSettingActivity.this,
								MainActivity.class));
						finish();
					} else {
						showToast("两次的密码不符请重新输入");
						lpwv.clearPassword();
					}

				}

			}
		});
	}

	// 获取返回键的响应事件
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			Intent it = new Intent();
			DataDao dao = new DataDao(getApplication());
			dao.add(Constants.kGESTRUECLOSE, 0);
			it.putExtra("isOpen",
					ApplicationEnvironment.getInstance().getPreferences(this)
							.getBoolean(Constants.kGESTRUECLOSE, false));
			setResult(Activity.RESULT_OK, it);
			LockScreenSettingActivity.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
