package com.people.icbc.activity;

import java.util.HashMap;

import com.people.icbc.R;
import com.people.icbc.client.ApplicationEnvironment;
import com.people.icbc.client.Constants;
import com.people.icbc.client.TransferRequestTag;
import com.people.network.LKAsyncHttpResponseHandler;
import com.people.network.LKHttpRequest;
import com.people.network.LKHttpRequestQueue;
import com.people.network.LKHttpRequestQueueDone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TransferActivity extends BaseActivity implements OnClickListener {
	private TextView tv_receive, tv_pay = null;
	private EditText et_sum, et_psw = null;
	private Button btn_confirm = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transfer);
		initView();

	}

	public void initView() {
		tv_receive = (TextView) findViewById(R.id.tv_receive);
		tv_pay = (TextView) findViewById(R.id.tv_pay);
		et_sum = (EditText) findViewById(R.id.et_sum);
		et_psw = (EditText) findViewById(R.id.et_psw);
		btn_confirm = (Button) findViewById(R.id.btn_confirm);
		btn_confirm.setOnClickListener(this);
		tv_receive.setText("收款卡号：" + Constants.FACERECEIVE_CADE);
		tv_pay.setText("付款卡号：" + Constants.FACEPAY_CARD);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_confirm:
			if (et_sum.getText().toString().length() == 0) {
				showToast("请输入金额");
			} else if (et_psw.getText().toString().length() == 0) {
				showToast("请输入密码");
			} else {
				if (et_psw
						.getText()
						.toString()
						.trim()
						.equals(ApplicationEnvironment.getInstance()
								.getPreferences()
								.getString(Constants.kPASSWORD, ""))) {

					TransferVerify();

				} else {
					showToast("密码错误请重新输入");
				}
			}
			break;

		default:
			break;
		}

	}

	private void TransferVerify() {
		HashMap<String, Object> tempMap = new HashMap<String, Object>();
		// token
		tempMap.put("from", Constants.FACEPAY_CARD);
		tempMap.put("to", Constants.FACERECEIVE_CADE);
		tempMap.put("money", et_sum.getText().toString().trim());
		tempMap.put("psw", et_psw.getText().toString().trim());
		LKHttpRequest req1 = new LKHttpRequest(TransferRequestTag.Transfer,
				tempMap, TransferHandler());

		new LKHttpRequestQueue().addHttpRequest(req1).executeQueue(
				"正在加载数据请稍候。。。", new LKHttpRequestQueueDone() {
					@Override
					public void onComplete() {
						super.onComplete();
						BaseActivity.getTopActivity().hideDialog(
								ADPROGRESS_DIALOG);
						setResult(RESULT_OK);
						finish();
					}

				});

	}

	public LKAsyncHttpResponseHandler TransferHandler() {

		return new LKAsyncHttpResponseHandler() {
			@Override
			public void successAction(Object obj) {
				HashMap<String, String> map = (HashMap<String, String>) obj;
				String rt = map.get("ret");
				switch (Integer.parseInt(rt)) {
				case 0:
					Intent intent0 = new Intent(TransferActivity.this,
							SuccessActivity.class);
					intent0.putExtra("FROM", "FACE");
					intent0.putExtra("CARD", Constants.FACEPAY_CARD);
					// intent0.putExtra(
					// "SUM",
					// Integer.parseInt(Constants.FACE_SUM)
					// - Integer.parseInt(et_sum.getText()
					// .toString().trim()));
					intent0.putExtra(
							"SUM",
							Integer.parseInt(Constants.FACE_SUM2)
									- Integer.parseInt(et_sum.getText()
											.toString().trim()));

					startActivity(intent0);
					break;
				case 17:
					Intent intent1 = new Intent(TransferActivity.this,
							DefeatedActivity.class);
					intent1.putExtra("result", "转出卡号不合法");
					startActivityForResult(intent1, 101);
					break;
				case 18:
					Intent intent2 = new Intent(TransferActivity.this,
							DefeatedActivity.class);
					intent2.putExtra("result", "转入卡号不合法");
					startActivityForResult(intent2, 102);
					break;
				case 12:
					Intent intent3 = new Intent(TransferActivity.this,
							DefeatedActivity.class);
					intent3.putExtra("result", "余额不足");
					startActivityForResult(intent3, 103);
					break;

				default:
					showToast("未知错误");
					break;
				}
			}
		};

	}

}
