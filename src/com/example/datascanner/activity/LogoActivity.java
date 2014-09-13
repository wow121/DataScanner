package com.example.datascanner.activity;

import java.lang.reflect.Type;
import java.util.List;

import org.json.JSONObject;

import android.os.Bundle;

import com.example.datascanner.R;
import com.example.datascanner.bean.UserBean;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class LogoActivity extends BasicNetWorkActivity {

	private final int LOGIN=1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logo);
		
//		String url="http://121.40.86.242/user/login?name=xianrui&password=xianrui123";
//		putJsonRequest(url, LOGIN);
		String url="http://121.40.86.242/user/getinf";
		putGsonRequest(url, LOGIN);
	}

	@Override
	protected boolean onRequestEnd(Object response, int type) {
		// TODO Auto-generated method stub
		
//		JSONObject jsonObject=(JSONObject) response;
		String url=(String) response;
		Type tpye= new TypeToken<List<UserBean>>(){}.getType();
		List<UserBean> list=mGson.fromJson(url,tpye);
		
		showToast(list.toString());
		
		return false;
	}
}
