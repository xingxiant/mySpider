package cn.zju.yuki.spider.queue;

import java.util.LinkedList;
/**
 * 已经访问过的URL队列
 * @author 13983
 *
 */
public class VisitedUrlQueue {
	// 超链接队列
	private static LinkedList<String> visitedUrlQueue = new LinkedList<String>();

	public synchronized static void addElement(String url){
		visitedUrlQueue.add(url);
	}
	
	public synchronized static void addFirstElement(String url){
		visitedUrlQueue.addFirst(url);
	}
	
	public synchronized static String outElement(){
		return visitedUrlQueue.removeFirst();
	}
	
	public synchronized static boolean isEmpty(){
		return visitedUrlQueue.isEmpty();
	}
	
	public static int size(){
		return visitedUrlQueue.size();
	}
	
	public static boolean isContains(String url){
		return visitedUrlQueue.contains(url);
	}
}
