package com.pallasli.study.htmlparser;

//import java.util.HashSet;
//import java.util.Set;
//
//import org.w3c.dom.NodeList;
//import org.w3c.dom.traversal.NodeFilter;

public class HtmlParserTool {

	// // 循环访问所有节点，输出包含关键字的值节点
	// public static void extractKeyWordText(String url, String keyword) {
	// try {
	// // 生成一个解析器对象，用网页的 url 作为参数
	// Parser parser = new Parser(url);
	// // 设置网页的编码,这里只是请求了一个 gb2312 编码网页
	// parser.setEncoding("gb2312");
	// // 迭代所有节点, null 表示不使用 NodeFilter
	// NodeList list = parser.parse(null);
	// // 从初始的节点列表跌倒所有的节点
	// processNodeList(list, keyword);
	// } catch (ParserException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// private static void processNodeList(NodeList list, String keyword) {
	// // 迭代开始
	// SimpleNodeIterator iterator = list.elements();
	// while (iterator.hasMoreNodes()) {
	// Node node = iterator.nextNode();
	// // 得到该节点的子节点列表
	// NodeList childList = node.getChildren();
	// // 孩子节点为空，说明是值节点
	// if (null == childList) {
	// // 得到值节点的值
	// String result = node.toPlainTextString();
	// // 若包含关键字，则简单打印出来文本
	// if (result.indexOf(keyword) != -1)
	// System.out.println(result);
	// } // end if
	// // 孩子节点不为空，继续迭代该孩子节点
	// else {
	// processNodeList(childList, keyword);
	// } // end else
	// } // end wile
	// }
	//
	// // 上面的中有两个方法：
	// // private static void processNodeList(NodeList list, String keyword)
	// // 该方法是用类似深度优先的方法来迭代遍历整个网页节点，将那些包含了某个关键字的值节点的值打印出来。
	// // public static void extractKeyWordText(String url, String keyword)
	// // 该方法生成针对 String 类型的 url 变量代表的某个特定网页的解析器，调用 1中的方法实现简单的遍历。
	// //
	// // 清单 3
	// //
	// 的代码展示了如何迭代所有的网页，更多的工作可以在此基础上展开。比如找到某个特定的网页内部节点，其实就可以在遍历所有的节点基础上来判断，看被迭代的节点是否满足特定的需要。
	// //
	// // 使用 NodeFilter
	// // NodeFilter 是一个接口，任何一个自定义的 Filter 都需要实现这个接口中的 boolean accept()
	// // 方法。如果希望迭代网页节点的时候保留当前节点，则在节点条件满足的情况下返回 true；否则返回 false。HtmlParse
	// 里提供了很多实现了
	// // NodeFilter 接口的类，下面就一些笔者所用到的，以及常用的 Filter 做一些介绍：
	// //
	// // 对 Filter 做逻辑操作的 Fitler 有：AndFilter，NotFilter ，OrFilter，XorFilter。
	// // 这些 Filter 来组合不同的 Filter，形成满足两个 Filter 逻辑关系结果的 Filter。
	// //
	// // 判断节点的孩子，兄弟，以及父亲节点情况的 Filter
	// // 有：HasChildFilterHasParentFilter，HasSiblingFilter。
	// // 判断节点本身情况的 Filter 有
	// // HasAttributeFilter：判读节点是否有特定属性；LinkStringFilter：判断节点是否是具有特定模式
	// (pattern)
	// // url 的节点；
	// // TagNameFilter：判断节点是否具有特定的名字；NodeClassFilter：判读节点是否是某个 HtmlParser 定义好的
	// Tag
	// // 类型。在 org.htmlparser.tags 包下有对应 Html标签的各种 Tag，例如 LinkTag，ImgeTag 等。
	//
	// // 简单强大的 StringBean
	// // 如果你想要网页中去掉所有的标签后剩下的文本，那就是用 StringBean 吧。以下简单的代码可以帮你解决这样的问题：
	// //
	// // 清单5
	// // StringBean sb = new StringBean();
	// //
	// // sb.setLinks(false);//设置结果中去点链接
	// //
	// // sb.setURL(url);//设置你所需要滤掉网页标签的页面 url
	// //
	// // System.out.println(sb.getStrings());//打印结果
	// public static void extracLinks(String url) {
	// try {
	// Parser parser = new Parser(url);
	// parser.setEncoding("gb2312");
	// // 过滤 <frame> 标签的 filter，用来提取 frame 标签里的 src 属性所、表示的链接
	// NodeFilter frameFilter = new NodeFilter() {
	// public boolean accept(Node node) {
	// if (node.getText().startsWith("frame src=")) {
	// return true;
	// } else {
	// return false;
	// }
	// }
	// };
	// // OrFilter 来设置过滤 <a> 标签，<img> 标签和 <frame> 标签，三个标签是 or 的关系
	// OrFilte rorFilter = new OrFilter(new NodeClassFilter(LinkTag.class), new
	// NodeClassFilter(ImageTag.class));
	// OrFilter linkFilter = new OrFilter(orFilter, frameFilter);
	// // 得到所有经过过滤的标签
	// NodeList list = parser.extractAllNodesThatMatch(linkFilter);
	// for (int i = 0; i < list.size(); i++) {
	// Node tag = list.elementAt(i);
	// if (tag instanceof LinkTag)// <a> 标签
	// {
	// LinkTag link = (LinkTag) tag;
	// String linkUrl = link.getLink();// url
	// String text = link.getLinkText();// 链接文字
	// System.out.println(linkUrl + "**********" + text);
	// } else if (tag instanceof ImageTag)// <img> 标签
	// {
	// ImageTag image = (ImageTag) list.elementAt(i);
	// System.out.print(image.getImageURL() + "********");// 图片地址
	// System.out.println(image.getText());// 图片文字
	// } else// <frame> 标签
	// {
	// // 提取 frame 里 src 属性的链接如 <frame src="test.html"/>
	// String frame = tag.getText();
	// int start = frame.indexOf("src=");
	// frame = frame.substring(start);
	// int end = frame.indexOf(" ");
	// if (end == -1)
	// end = frame.indexOf(">");
	// frame = frame.substring(5, end - 1);
	// System.out.println(frame);
	// }
	// }
	// } catch (ParserException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// // 获取一个网站上的链接,filter 用来过滤链接
	// public static Set<String> extracLinks(String url, LinkFilter filter) {
	//
	// Set<String> links = new HashSet<String>();
	// try {
	// Parser parser = new Parser(url);
	// parser.setEncoding("gb2312");
	// // 过滤 <frame >标签的 filter，用来提取 frame 标签里的 src 属性所表示的链接
	// NodeFilter frameFilter = new NodeFilter() {
	// public boolean accept(Node node) {
	// if (node.getText().startsWith("frame src=")) {
	// return true;
	// } else {
	// return false;
	// }
	// }
	// };
	// // OrFilter 来设置过滤 <a> 标签，和 <frame> 标签
	// OrFilter linkFilter = new OrFilter(new NodeClassFilter(LinkTag.class),
	// frameFilter);
	// // 得到所有经过过滤的标签
	// NodeList list = parser.extractAllNodesThatMatch(linkFilter);
	// for (int i = 0; i < list.size(); i++) {
	// Node tag = list.elementAt(i);
	// if (tag instanceof LinkTag)// <a> 标签
	// {
	// LinkTag link = (LinkTag) tag;
	// String linkUrl = link.getLink();// url
	// if (filter.accept(linkUrl))
	// links.add(linkUrl);
	// } else// <frame> 标签
	// {
	// // 提取 frame 里 src 属性的链接如 <frame src="test.html"/>
	// String frame = tag.getText();
	// int start = frame.indexOf("src=");
	// frame = frame.substring(start);
	// int end = frame.indexOf(" ");
	// if (end == -1)
	// end = frame.indexOf(">");
	// String frameUrl = frame.substring(5, end - 1);
	// if (filter.accept(frameUrl))
	// links.add(frameUrl);
	// }
	// }
	// } catch (ParserException e) {
	// e.printStackTrace();
	// }
	// return links;
	// }
	//
	// // 测试的 main 方法
	// public static void main(String[] args) {
	// Set<String> links = HtmlParserTool.extracLinks("http://www.twt.edu.cn",
	// new LinkFilter() {
	// // 提取以 http://www.twt.edu.cn 开头的链接
	// @Override
	// public boolean accept(String url) {
	// if (url.startsWith("http://www.twt.edu.cn"))
	// return true;
	// else
	// return false;
	// }
	//
	// });
	// for (String link : links)
	// System.out.println(link);
	// }
}
