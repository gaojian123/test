package com.tools;

import com.tools.net.base.ResponseError;
import com.tools.net.base.UIDataListener;
import com.tools.net.model.MessageNet;
import com.tools.net.xy.XYResponseBean;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements UIDataListener<XYResponseBean> {

	private MessageNet mMessageNet;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mMessageNet=new MessageNet(this,this);
		mMessageNet.talkText("北京PM2.5");
	}
	@Override
	public void onSuccess(XYResponseBean bean, int requestCode) {
		System.out.println("text:"+bean.getText());
	}
	@Override
	public void onError(ResponseError error, int requestCode) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
}
