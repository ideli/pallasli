package com.pallasli.study.htmlparser;

import java.util.Set;

/**
 * 
 * 各个类的源码以及说明
 * 
 * 对应上面的流程图，简易爬虫由下面几个类组成，各个类职责如下：
 * 
 * Crawler.java：爬虫的主方法入口所在的类，实现爬取的主要流程。
 * 
 * LinkDb.java：用来保存已经访问的 url 和待爬取的 url 的类，提供url出对入队操作。
 * 
 * Queue.java： 实现了一个简单的队列，在 LinkDb.java 中使用了此类。
 * 
 * FileDownloader.java：用来下载 url 所指向的网页。
 * 
 * HtmlParserTool.java： 用来抽取出网页中的链接。
 * 
 * LinkFilter.java：一个接口，实现其 accept() 方法用来对抽取的链接进行过滤。
 * 
 * 下面是各个类的源码，代码中的注释有比较详细的说明。
 * 
 * @author lyt1987
 *
 */
public class Crawler {
	/* 使用种子 url 初始化 URL 队列 */
	private void initCrawlerWithSeeds(String[] seeds) {
		for (int i = 0; i < seeds.length; i++)
			LinkDB.addUnvisitedUrl(seeds[i]);
	}

	/* 爬取方法 */
	public void crawling(String[] seeds) {
		LinkFilter filter = new LinkFilter() {
			// 提取以 http://www.twt.edu.cn 开头的链接
			@Override
			public boolean accept(String url) {
				if (url.startsWith("http://www.twt.edu.cn"))
					return true;
				else
					return false;
			}
		};
		// 初始化 URL 队列
		initCrawlerWithSeeds(seeds);
		// 循环条件：待抓取的链接不空且抓取的网页不多于 1000
		while (!LinkDB.unVisitedUrlsEmpty()
				&& LinkDB.getVisitedUrlNum() <= 1000) {
			// 队头 URL 出对
			String visitUrl = LinkDB.unVisitedUrlDeQueue();
			if (visitUrl == null)
				continue;
			FileDownLoader downLoader = new FileDownLoader();
			// 下载网页
			downLoader.downloadFile(visitUrl);
			// 该 url 放入到已访问的 URL 中
			LinkDB.addVisitedUrl(visitUrl);
			// 提取出下载网页中的 URL

			Set<String> links = HtmlParserTool.extracLinks(visitUrl, filter);
			// 新的未访问的 URL 入队
			for (String link : links) {
				LinkDB.addUnvisitedUrl(link);
			}
		}
	}

	// main 方法入口
	public static void main(String[] args) {
		Crawler crawler = new Crawler();
		crawler.crawling(new String[] { "http://www.twt.edu.cn" });
	}
}
