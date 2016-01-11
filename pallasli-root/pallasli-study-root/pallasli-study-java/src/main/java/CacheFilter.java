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
 * @ClassName: CacheFilter
 * @Description: 控制缓存的filter
 *
 *               <!-- 配置缓存过滤器 --> <filter>
 *               <filter-name>CacheFilter</filter-name>
 *               <filter-class>me.gacl.web.filter.CacheFilter</filter-class>
 *               <!-- 配置要缓存的web资源以及缓存时间，以小时为单位 --> <init-param>
 *               <param-name>css</param-name> <param-value>4</param-value>
 *               </init-param> <init-param> <param-name>jpg</param-name>
 *               <param-value>1</param-value> </init-param> <init-param>
 *               <param-name>js</param-name> <param-value>4</param-value>
 *               </init-param> <init-param> <param-name>png</param-name>
 *               <param-value>4</param-value> </init-param> </filter> <!--
 *               配置要缓存的web资源的后缀--> <filter-mapping>
 *               <filter-name>CacheFilter</filter-name>
 *               <url-pattern>*.jpg</url-pattern> </filter-mapping>
 * 
 *               <filter-mapping> <filter-name>CacheFilter</filter-name>
 *               <url-pattern>*.css</url-pattern> </filter-mapping>
 * 
 *               <filter-mapping> <filter-name>CacheFilter</filter-name>
 *               <url-pattern>*.js</url-pattern> </filter-mapping>
 *               <filter-mapping> <filter-name>CacheFilter</filter-name>
 *               <url-pattern>*.png</url-pattern> </filter-mapping>
 */
public class CacheFilter implements Filter {

	private FilterConfig filterConfig;

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		// 1.获取用户想访问的资源
		String uri = request.getRequestURI();

		// 2.得到用户想访问的资源的后缀名
		String ext = uri.substring(uri.lastIndexOf(".") + 1);

		// 得到资源需要缓存的时间
		String time = filterConfig.getInitParameter(ext);
		if (time != null) {
			long t = Long.parseLong(time) * 3600 * 1000;
			// 设置缓存
			response.setDateHeader("expires", System.currentTimeMillis() + t);
		}

		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void destroy() {

	}
}