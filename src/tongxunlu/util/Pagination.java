package tongxunlu.util;

public class Pagination {
	public static String getPagenation(int pageNum, int pageCount, int recordCount, String pageUrl) {
		//检查获得请求pageUrl是否包含"？",为包含则需要添加"?".
		String url = pageUrl.contains("?") ? pageUrl : pageUrl + "?";
		//如果url不是以"?"并且不是"&"结尾,则添加"&"
		if(!url.endsWith("?") && !url.endsWith("&")) {
			url = url + "&";
		}
		
		//创建一个字符串缓冲器用来连接字符串
		StringBuffer buffer = new StringBuffer();
		buffer.append("第  " + pageNum + "/" + pageCount + "页  共" + recordCount + "记录  ");
		//判断是不是第一页,是的话则直接显示一个字符串,否则显示一个字符串链接.
		buffer.append(pageNum == 1 ? "第一页" : "<a href='" + url + "pageNum=1'>第一页</a> ");
		buffer.append(pageNum == 1 ? "上一页" : "<a href='" + url + "pageNum=" + 
					(pageNum - 1) + "'>上一页</a> ");
		buffer.append(pageNum == pageCount ? "下一页" : "<a href='" + url + "pageNum=" +
					(pageNum + 1) + "'>下一页</a> ");
		buffer.append(pageNum == pageCount ? "最后一页" : "<a href='" + url + "pageNum=" +
				pageCount + "'>最后一页</a> ");
		return buffer.toString();
	}
}
