package org.eclipse.birt.report.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.context.BirtContext;
import org.eclipse.birt.report.context.IContext;
import org.eclipse.birt.report.context.ViewerAttributeBean;
import org.eclipse.birt.report.presentation.aggregation.layout.EngineFragment;
import org.eclipse.birt.report.presentation.aggregation.layout.RequesterFragment;
import org.eclipse.birt.report.service.BirtReportServiceFactory;
import org.eclipse.birt.report.service.BirtViewerReportService;
import org.eclipse.birt.report.utility.BirtUtility;

public class BirtEngineServlet extends BaseReportEngineServlet {
	private static final long serialVersionUID = 1L;

	protected void __init(ServletConfig config) {
		BirtReportServiceFactory.init(new BirtViewerReportService(config
				.getServletContext()));

		this.engine = new EngineFragment();

		this.requester = new RequesterFragment();
		this.requester.buildComposite();
		this.requester.setJSPRootPath("/src/main/webapp/birt");
	}

	protected IContext __getContext(HttpServletRequest request,
			HttpServletResponse response) throws BirtException {
		BirtReportServiceFactory.getReportService().setContext(
				getServletContext(), null);

		return new BirtContext(request, response);
	}

	protected boolean __authenticate(HttpServletRequest request,
			HttpServletResponse response) {
		return true;
	}

	static final boolean $assertionsDisabled; /* synthetic field */

	static {
		$assertionsDisabled = !(BirtEngineServlet.class)
				.desiredAssertionStatus();
	}

	protected void __doGet(IContext context) throws ServletException,
			IOException, BirtException {
		ViewerAttributeBean bean = (ViewerAttributeBean) context.getBean();
		if ((!($assertionsDisabled)) && (bean == null))
			throw new AssertionError();

		if (((("/preview".equalsIgnoreCase(context.getRequest()
				.getServletPath()))
				|| ("/document".equalsIgnoreCase(context.getRequest()
						.getServletPath())) || ("/output"
					.equalsIgnoreCase(context.getRequest().getServletPath()))))
				&& (bean.isShowParameterPage())) {
			this.requester.service(context.getRequest(), context.getResponse());
		} else if ("/parameter".equalsIgnoreCase(context.getRequest()
				.getServletPath())) {
			this.requester.service(context.getRequest(), context.getResponse());
		} else {
			this.engine.service(context.getRequest(), context.getResponse());
		}
	}

	protected void __handleNonSoapException(HttpServletRequest request,
			HttpServletResponse response, Exception exception)
			throws ServletException, IOException {
		exception.printStackTrace();
		response.setContentType("text/html; charset=utf-8");
		BirtUtility.appendErrorMessage(response.getOutputStream(), exception);
	}
}