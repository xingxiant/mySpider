# mySpider
Java爬虫基本框架


1.初始化配置参数initializeParams()
---------------------------

   从properties文件中读取WORKER_NUM（工作线程数）和DEYLAY_TIME（等待时间）

2.初始化爬取队列initializeQueue()
--------------------------

  准备初始的爬取链接：

```
  private static void initializeQueue( ){
  // 例如，需要抓取豆瓣TOP 250的电影信息，根据链接规则生成URLs放入带抓取队列
  for(int i = 0; i < 250; i += 25){
        UrlQueue.addElement("http://movie.douban.com/top250?start=" + i);
    }
  }
```

    

3.创建worker线程并启动，开始爬
-------------------

线程数根据WORKER_NUM确定

4.爬取线程的流程
---------

**4.1从待抓取队列中拿URL**

**4.2抓取URL指定的页面，并返回状态码和页面内容构成的FetchedPage对象**

   FetchedPage fetchedPage = fetcher.getContentFromUrl(url);
   使用httpClient类进行页面获取，在获取请求之前要进行请求头的设置。
   
 **4.3检查爬取页面的合法性，爬虫是否被禁止**
 
   handler.check(fetchedPage)
   原理：根据http状态码是否合法以及是否为403（禁止）
   如果被禁止可以进行切换ip等操作
   
 **4.4解析页面，获取目标数据**
 
   Object targetData = parser.parse(fetchedPage);
   Document doc = Jsoup.parse(fetchedPage.getContent());//解析html页面，通过doc对象可以根据节点名称或者是 HTML 元素的 id 来获取对应的元素或者元素列表。
   
**4.5存储目标数据到数据存储（如DB）、存储已爬取的Url到VisitedUrlQueue**

  store.store(targetData);
  
**4.6等待**

  当待抓取URL队列不为空时，执行爬取任务
    注： 当队列内容为空时，也不爬取任务已经结束了
    因为有可能是UrlQueue暂时空，其他worker线程还没有将新的URL放入队列
    所以，这里可以做个等待时间，再进行抓取（二次机会）

