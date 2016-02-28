package org.eclipse.birt.report.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.context.BirtContext;
import org.eclipse.birt.report.context.IContext;
import org.eclipse.birt.report.presentation.aggregation.IFragment;
import org.eclipse.birt.report.presentation.aggregation.layout.FramesetFragment;
import org.eclipse.birt.report.presentation.aggregation.layout.RunFragment;
import org.eclipse.birt.report.service.BirtReportServiceFactory;
import org.eclipse.birt.report.service.BirtViewerReportService;
import org.eclipse.birt.report.utility.BirtUtility;

public class ViewerServlet extends BirtSoapMessageDispatcherServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void __init(ServletConfig config) {
		BirtReportServiceFactory.init(new BirtViewerReportService(config.getServletContext()));

		this.viewer = new FramesetFragment();
		this.viewer.buildComposite();
		this.viewer.setJSPRootPath("/src/main/webapp/birt");

		this.run = new RunFragment();
		this.run.buildComposite();
		this.run.setJSPRootPath("/src/main/webapp/birt");
	}

	@Override
	protected IContext __getContext(HttpServletRequest request, HttpServletResponse response) throws BirtException {
		BirtReportServiceFactory.getReportService().setContext(getServletContext(), null);

		return new BirtContext(request, response);
	}

	@Override
	protected void __doGet(IContext context) throws ServletException, IOException, BirtException {
		IFragment activeFragment = null;
		String servletPath = context.getRequest().getServletPath();
		if ("/frameset".equalsIgnoreCase(servletPath)) {
			activeFragment = this.viewer;
		} else if ("/run".equalsIgnoreCase(servletPath)) {
			activeFragment = this.run;
		}

		if (activeFragment != null)
			activeFragment.service(context.getRequest(), context.getResponse());
	}

	@Override
	protected void __doPost(IContext context) throws ServletException, IOException, BirtException {
	}

	@Override
	protected boolean __authenticate(HttpServletRequest request, HttpServletResponse response) {
		return true;
	}

	@Override
	protected void __handleNonSoapException(HttpServletRequest request, HttpServletResponse response,
			Exception exception) throws ServletException, IOException {
		exception.printStackTrace();
		BirtUtility.appendErrorMessage(response.getOutputStream(), exception);
	}
}