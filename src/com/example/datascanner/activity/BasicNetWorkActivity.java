package com.example.datascanner.activity;

import java.util.HashMap;

import org.json.JSONObject;

import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.datascanner.DataScannerApplication;
import com.example.datascanner.R;
import com.example.datascanner.tools.BitmapCache;
import com.google.gson.Gson;
import com.tandong.sa.activity.SmartActivity;

public abstract class BasicNetWorkActivity extends SmartActivity {
	RequestQueue mQueue;
	Gson mGson;
	ImageLoader mImageLoader;
	ImageListener mImageListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mQueue = DataScannerApplication.getInstance().getRequestQueue();
		mGson=new Gson();
		mImageLoader=new ImageLoader(mQueue, new BitmapCache());
	}

	protected boolean putJsonRequest(String url, final int type) {
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						// TODO Auto-generated method stub
						onRequestEnd(response, type);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						showToast(getResources().getString(R.string.net_error));
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
						showToast(getResources().getString(R.string.net_error));
					}
				});
		mQueue.add(jsonObjectRequest);
		return false;
	}

	protected  boolean putGsonRequest(String url, final int type
			) {
		
		StringRequest mStringRequest = new StringRequest(url,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						// TODO Auto-generated method stub
															
						onRequestEnd(response, type);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						showToast(getResources().getString(R.string.net_error));
					}
				});

		mQueue.add(mStringRequest);
		return false;
	}
	
	protected boolean bindImage(ImageView imageView,String url) {
		mImageListener=ImageLoader.getImageListener(imageView, R.drawable.ic_launcher, R.drawable.ic_launcher);
		mImageLoader.get(url, mImageListener);
		return false;
	}

	protected abstract boolean onRequestEnd(Object response, int type);

	

}
