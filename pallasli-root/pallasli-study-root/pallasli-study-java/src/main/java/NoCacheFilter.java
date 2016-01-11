import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: NoCacheFilter
 * @Description: 禁止浏览器缓存所有动态页面
 * 
 *               <filter> <filter-name>NoCacheFilter</filter-name>
 *               <filter-class>me.gacl.web.filter.NoCacheFilter</filter-class>
 *               </filter>
 * 
 *               <filter-mapping> <filter-name>NoCacheFilter</filter-name>
 *               <!--只拦截Jsp请求--> <servlet-name>*.jsp</servlet-name>
 *               </filter-mapping>
 */
public class NoCacheFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// 把ServletRequest强转成HttpServletRequest
		HttpServletRequest request = (HttpServletRequest) req;
		// 把ServletResponse强转成HttpServletResponse
		HttpServletResponse response = (HttpServletResponse) resp;
		// 禁止浏览器缓存所有动态页面
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}
}