package com.tools.net.base;

public interface UIDataListener<T> {
	public void onSuccess(T bean,int requestCode);

	public void onError(ResponseError error,int requestCode);
}
