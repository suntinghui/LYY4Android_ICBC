package com.people.icbc.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.people.icbc.R;
import com.people.icbc.client.Constants;
import com.people.icbc.sqlite.DataDao;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class ICBCMainActivity extends BaseActivity implements OnClickListener {
	private DataDao dao = null;
	private Button btn_back_icbcmain = null;
	private GridView main_gridView = null;
	private int[] images = { R.drawable.icon_1, R.drawable.icon_2,
			R.drawable.icon_3, R.drawable.icon_4, R.drawable.icon_5,
			R.drawable.icon_6, R.drawable.icon_7 };
	private String[] name = { "网上购物", "当面付", "在线消费", "离线消费", "ATM取款", "添加新卡",
			"卸载SOTP插件" };
	private List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
	private myAdapter adapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_icbcmain);

		initView();
		if (!dao.find(Constants.LOGGED)) {
			dao.add(Constants.LOGGED, 1);
		}

	}

	public void initView() {
		dao = new DataDao(getApplication());
		btn_back_icbcmain = (Button) findViewById(R.id.btn_back_icbcmain);
		btn_back_icbcmain.setOnClickListener(this);
		main_gridView = (GridView) findViewById(R.id.main_gridView);
		for (int i = 0; i < name.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", images[i]);
			map.put("text", name[i]);
			dataList.add(map);
		}
		adapter = new myAdapter();
		main_gridView.setAdapter(adapter);

		main_gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				ImageView icon = (ImageView) arg1.findViewById(R.id.iv_gri);
				icon.startAnimation(AnimationUtils.loadAnimation(
						ICBCMainActivity.this, R.anim.scale));
				if (position == 0) {
					Intent intent0 = new Intent(ICBCMainActivity.this,
							OnlineShopActivity.class);
					startActivity(intent0);
				} else if (position == 1) {
					Intent intent1 = new Intent(ICBCMainActivity.this,
							FacePayActivity.class);
					startActivity(intent1);
				} else if (position == 2) {
					Intent intent2 = new Intent(ICBCMainActivity.this,
							OnlineAccountsInfoActivity.class);
					startActivity(intent2);
				} else if (position == 3) {
					Intent intent3 = new Intent(ICBCMainActivity.this,
							AccountsInfoActivity.class);
					startActivity(intent3);
				} else if (position == 4) {

					// Intent intent4 = new Intent(ICBCMainActivity.this,
					// OnlineShopActivity.class);
					// startActivity(intent4);
					showToast("ATM取款正在建设中");
				}

				else if (position == 5) {

					Intent intent5 = new Intent(ICBCMainActivity.this,
							BindActivity.class);
					startActivity(intent5);
				} else if (position == 6) {
					Uri uri = Uri.parse("package:com.people.sotp.service");
					Intent intent2 = new Intent(Intent.ACTION_DELETE, uri);
					ICBCMainActivity.this.startActivity(intent2);
				}
			}
		});
	}

	public class myAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return dataList.size();
		}

		@Override
		public Object getItem(int position) {
			return dataList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = LayoutInflater.from(ICBCMainActivity.this)
						.inflate(R.layout.item_gridview, null);
				holder = new ViewHolder();
				holder.imageView = (ImageView) convertView
						.findViewById(R.id.iv_gri);
				holder.textView = (TextView) convertView
						.findViewById(R.id.tv_gri);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.imageView.setImageResource((Integer) dataList.get(position)
					.get("image"));
			holder.textView.setText((CharSequence) dataList.get(position).get(
					"text"));
			return convertView;
		}
	}

	static class ViewHolder {
		ImageView imageView;
		TextView textView;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back_icbcmain:
			ICBCMainActivity.this.finish();
			break;

		default:
			break;
		}
	}
}
