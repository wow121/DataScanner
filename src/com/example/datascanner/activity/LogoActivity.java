package com.example.datascanner.activity;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.List;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.datascanner.R;
import com.example.datascanner.bean.UserBean;
import com.example.datascanner.tools.ChineseCalendar;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.tandong.sa.loopj.AsyncHttpClient;
import com.wt.calendarcard.CalendarCard;
import com.wt.calendarcard.CalendarCardPager;
import com.wt.calendarcard.CardGridItem;
import com.wt.calendarcard.OnCellItemClick;

public class LogoActivity extends BasicNetWorkActivity {

	private final int LOGIN=1;
	
	private CalendarCardPager mCalendarCard;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logo);
		
//		String url="http://121.40.86.242/user/login?name=xianrui&password=xianrui123";
//		putJsonRequest(url, LOGIN);
//		String url="http://121.40.86.242/user/getinf";
//		putGsonRequest(url, LOGIN);
		
//		ImageView imageView=(ImageView)findViewById(R.id.myImage);
//		bindImage(imageView, "http://ttw.tukuzhan.com/uploads/allimg/110829/1341211V0-0.jpg");
		
		mCalendarCard = (CalendarCardPager)findViewById(R.id.calendarCard1);
		
		Calendar cal = Calendar.getInstance();
		int index=(cal.get(Calendar.YEAR)-1970)*12+cal.get(Calendar.MONTH);
		
		mCalendarCard.setCurrentItem(index);
		mCalendarCard.setOnCellItemClick(new OnCellItemClick() {
			@Override
			public void onCellClick(View v, CardGridItem item) {
				Calendar mCalendar=item.getDate();
//				mCalendar.set(2014, 10, 24);
				ChineseCalendar calendar=new ChineseCalendar(mCalendar.getTime());
//				showToast("yue:"+calendar.getChinese(ChineseCalendar.CHINESE_MONTH)+" ri:"+calendar.getChinese(ChineseCalendar.CHINESE_DATE)+"");
				showToast(calendar.getSimpleChineseDateString());
			}
		});
		
		mCalendarCard.getCardPagerAdapter().get
		
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
