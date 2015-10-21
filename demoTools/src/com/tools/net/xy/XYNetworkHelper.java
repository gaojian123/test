package com.tools.net.xy;

import org.json.JSONObject;

import com.android.volley.VolleyError;
import com.tools.net.base.NetworkHelper;
import com.tools.net.base.ResponseError;
import com.tools.net.base.SystemParams;
import com.tools.utils.JsonUtils;

import android.content.Context;

public class XYNetworkHelper extends NetworkHelper<XYResponseBean>{
    
    public XYNetworkHelper(Context context){
        super(context);
    }

	@Override
	protected void disposeVolleyError(VolleyError error,int requestCode,boolean isMore,Object tag) {
		ResponseError responseError=new ResponseError();
		responseError.setMore(isMore);
		responseError.setTag(tag);
		responseError.setErrorCode(SystemParams.VOLLEY_ERROR_CODE);
		if (null==error) {
			responseError.setErrorMsg("null");
		}else {
			responseError.setErrorMsg("网络错误");
		}
		notifyErrorHappened(responseError,requestCode);
	}

    @Override
    protected void disposeResponse(JSONObject response,int requestCode,boolean isMore,Object tag){
        XYResponseBean bean = null;
        ResponseError error=new ResponseError();
        if(response != null){
            try{
            	bean=JsonUtils.resultData(response.toString(),XYResponseBean.class );
            	bean.setMore(isMore);
            	bean.setTag(tag);
            	switch (Integer.parseInt(bean.getCode())) {
				case 100000:
				case 200000:
				case 302000:
				case 305000:
				case 308000:
					notifyDataChanged(bean,requestCode);
					break;
				default:
					error.setBean(bean);
                    notifyErrorHappened(error,requestCode);
					break;
				}
            }catch(Exception e){
            	error.setErrorCode(SystemParams.RESPONSE_FORMAT_ERROR);
            	error.setErrorMsg("Response format error");
            	error.setMore(isMore);
            	error.setTag(tag);
                notifyErrorHappened(error ,requestCode);
            }
        }else{
        	error.setErrorCode(SystemParams.RESPONSE_IS_NULL);
        	error.setErrorMsg("Response is null!");
        	error.setMore(isMore);
        	error.setTag(tag);
            notifyErrorHappened(error, requestCode);
        }
    }
}