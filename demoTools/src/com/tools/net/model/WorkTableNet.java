package com.tools.net.model;

import com.tools.net.base.NetworkHelper;
import com.tools.net.base.UIDataListener;
import com.tools.net.xy.XYNetworkHelper;
import com.tools.net.xy.XYResponseBean;

import android.content.Context;

/***
 * 工作台网络接口
 * @author gj
 *
 */
public class WorkTableNet {

	private NetworkHelper<XYResponseBean> mNetworkHelper;
	public WorkTableNet(Context context, UIDataListener<XYResponseBean> listener) {
		mNetworkHelper = new XYNetworkHelper(context);
		mNetworkHelper.setUiDataListener(listener);
	}
//	/**
//	 * 5-2获取教练工作台
//	 * @param trainerId教练ID(必填)
//	 * @param pageSize请求天数（不填，默认6天） PS：从当天开始后的6天，如果数据空则代表当天没有排班
//	 */
//	public void scheduleRecent(String trainerId,int pageSize){
//		
//		List<NameValuePair> params=new ArrayList<NameValuePair>();
////		params.add(new BasicNameValuePair("periodId", getIntent().getStringExtra("periodId")));
//		params.add(new BasicNameValuePair("trainerId", trainerId));
//		params.add(new BasicNameValuePair("pageSize", String.valueOf(pageSize)));
//		mNetworkHelper.sendGETRequest(Constants.trainScheduleUrl, params,RequestCode.SCHEDULE_RECENT);
//	}
//	
//	/**
//	 * 5-3获取教练工作台详情
//	 * @param periodId 时间段Id(必填)
//	 */
//	public void scheduleDetail(String periodId){
//		List<NameValuePair> params=new ArrayList<NameValuePair>();
//		params.add(new BasicNameValuePair("periodId", periodId));
//		mNetworkHelper.sendGETRequest(Constants.trainScheduleDetailUrl, params,RequestCode.SCHEDULE_DETAIL);
//	}
//
//	/**
//	 * 开始学成状态变更
//	 * @param periodId
//	 */
//	public void scheduleTrain(String periodId) {
//		List<NameValuePair> params = new ArrayList<NameValuePair>();
//		params.add(new BasicNameValuePair("periodId",periodId));
//		mNetworkHelper.sendGETRequest(Constants.trainScheduleTrainUrl, params, RequestCode.SCHEDULE_TRAIN);
//	}
//	/**
//	 * 5-1获取教练一个月的排班
//	 * @param trainerId教练ID(必填)
//	 * @param yearMonth请求的年月（yyyy-MM）格式（选填，默认当月）
//	 */
//	public void schedule(String trainerId,String yearMonth) {
//		List<NameValuePair> params = new ArrayList<NameValuePair>();
//		params.add(new BasicNameValuePair("trainerId", trainerId));
//		params.add(new BasicNameValuePair("yearMonth", yearMonth));
//		mNetworkHelper.sendGETRequest(Constants.coachMonthUrl, params, RequestCode.SCHEDULE);
//	}
//	
	
	

}
