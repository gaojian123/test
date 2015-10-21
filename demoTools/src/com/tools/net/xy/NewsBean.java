package com.tools.net.xy;

import java.io.Serializable;

/****
 * "article":"",
 * 
 * "source":"",
 * 
 * "detailurl":"",
 * 
 * "icon":""
 *****/
public class NewsBean extends ListBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2602659812621967151L;

	private String article;
	private String source;
	private String detailurl;
	private String icon;
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDetailurl() {
		return detailurl;
	}
	public void setDetailurl(String detailurl) {
		this.detailurl = detailurl;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}
