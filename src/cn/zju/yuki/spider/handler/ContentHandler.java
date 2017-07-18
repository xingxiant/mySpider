package cn.zju.yuki.spider.handler;

import cn.zju.yuki.spider.model.FetchedPage;
import cn.zju.yuki.spider.queue.UrlQueue;
/**
 * handler模块对Fetcher下载的页面进行初步处理，如判断该页面的返回状态码是否正确、
 * 页面内容是否为反爬信息等，从而保证传到Parser进行解析的页面是正确的
 * @author 13983
 *
 */
public class ContentHandler {
	public boolean check(FetchedPage fetchedPage){
		// 如果抓取的页面包含反爬取内容，则将当前URL放入待爬取队列，以便重新爬取
		if(isAntiScratch(fetchedPage)){
			UrlQueue.addFirstElement(fetchedPage.getUrl());
			return false;
		}
		
		return true;
	}
	
	private boolean isStatusValid(int statusCode){
		if(statusCode >= 200 && statusCode < 400){
			return true;
		}
		return false;
	}
	
	private boolean isAntiScratch(FetchedPage fetchedPage){
		// 403 forbidden
		if((!isStatusValid(fetchedPage.getStatusCode())) && fetchedPage.getStatusCode() == 403){
			return true;
		}
		
		// 页面内容包含的反爬取内容
		if(fetchedPage.getContent().contains("<div>禁止访问</div>")){
			return true;
		}
		
		return false;
	}
}
