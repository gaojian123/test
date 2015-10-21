package com.tools.net.xy;


import java.util.List;

import com.tools.net.base.ResponseBean;

/**
 * 服务器返回数据对象
 * @author gj
 *
 */
public class XYResponseBean extends ResponseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2010160010850889314L;
	private String text = "";
	private String code = "";
    private String url;	
    private List<ListBean> list;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCode() {
		return code.toString();
	}
	public void setCode(String result) {
		this.code = result;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<ListBean> getList() {
		return list;
	}
	public void setList(List<ListBean> list) {
		this.list = list;
	}
	
	
}
