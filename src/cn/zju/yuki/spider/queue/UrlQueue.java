package cn.zju.yuki.spider.queue;

import java.util.LinkedList;
/**
 * 要抓取的URL队列
 * @author 13983
 *
 */
public class UrlQueue {
	// 超链接队列
	private static LinkedList<String> urlQueue = new LinkedList<String>();

	public synchronized static void addElement(String url){
		urlQueue.add(url);
	}
	
	public synchronized static void addFirstElement(String url){
		urlQueue.addFirst(url);
	}
	
	public synchronized static String outElement(){
		return urlQueue.removeFirst();
	}
	
	public synchronized static boolean isEmpty(){
		return urlQueue.isEmpty();
	}
	
	public static int size(){
		return urlQueue.size();
	}
	
	public static boolean isContains(String url){
		return urlQueue.contains(url);
	}
}
