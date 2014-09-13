package com.example.datascanner.activity;

import java.util.HashMap;

import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.GsonRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.datascanner.DataScannerApplication;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public abstract class BasicNetWorkActivity extends Activity {
	RequestQueue mQueue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mQueue = DataScannerApplication.getInstance().getRequestQueue();
	}

	protected boolean putJsonRequest(String url, final int type) {
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,
				null, new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						// TODO Auto-generated method stub
						onRequestEnd(response, type);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						showToast("ÍøÂçÁ¬½ÓÊ§°Ü£¬Çë¼ì²éÍøÂç£¡");
					}
				});
		mQueue.add(jsonObjectRequest);
		return false;
	}

	protected boolean putJsonRequest(String url, final int type,
			HashMap<String, String> params) {
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,
				new JSONObject(params), new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						// TODO Auto-generated method stub
						onRequestEnd(response, type);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						showToast("ÍøÂçÁ¬½ÓÊ§°Ü£¬Çë¼ì²éÍøÂç£¡");
					}
				});
		mQueue.add(jsonObjectRequest);
		return false;
	}

	protected <T> boolean putGsonRequest(String url, final int type,
			Class<T> mClass) {
		GsonRequest<T> mGsonRequest = new GsonRequest<T>(url, mClass,
				new Response.Listener<T>() {

					@Override
					public void onResponse(T response) {
						// TODO Auto-generated method stub
						onRequestEnd(response, type);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						showToast("ÍøÂçÁ¬½ÓÊ§°Ü£¬Çë¼ì²éÍøÂç£¡");
					}
				});
		mQueue.add(mGsonRequest);
		return false;
	}

	protected abstract boolean onRequestEnd(Object response, int type);

	protected void showToast(String message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}

}
