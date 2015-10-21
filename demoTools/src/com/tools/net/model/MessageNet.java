package com.tools.net.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.tools.net.base.NetworkHelper;
import com.tools.net.base.RequestCode;
import com.tools.net.base.UIDataListener;
import com.tools.net.xy.XYNetworkHelper;
import com.tools.net.xy.XYResponseBean;
import com.tools.utils.Constants;

import android.content.Context;

/***
 * 工作台网络接口
 * 
 * @author gj
 *
 */
public class MessageNet {

	private NetworkHelper<XYResponseBean> mNetworkHelper;

	public MessageNet(Context context, UIDataListener<XYResponseBean> listener) {
		mNetworkHelper = new XYNetworkHelper(context);
		mNetworkHelper.setUiDataListener(listener);
	}

	/**
	 * 获取消息列表
	 * @param info 信息
	 */
	public void talkText(String info) {

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", Constants.API_KEY));
		params.add(new BasicNameValuePair("info", info));
		mNetworkHelper.sendGETRequest(Constants.API_URL, params, RequestCode.Text);
	}
	
	/**
	 * 获取消息列表
	 * @param info 信息
	 */
	public void searchTrian(String from,String to) {

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", Constants.API_KEY));
		params.add(new BasicNameValuePair("info", from+"到"+to+"的火车"));
		mNetworkHelper.sendGETRequest(Constants.API_URL, params, RequestCode.Train);
	}
}
