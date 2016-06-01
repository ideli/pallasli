package com.shineyue.htmldesign.contoller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shineyue.htmldesign.html.AbstractComponent;
import com.shineyue.htmldesign.html.PanelComponent;
import com.shineyue.htmldesign.html.component.Textarea;
import com.shineyue.htmldesign.html.panelcomponent.Panel;
import com.shineyue.htmldesign.model.Page;
import com.shineyue.htmldesign.model.PageComponent;
import com.shineyue.htmldesign.model.PageComponentConfig;
import com.shineyue.htmldesign.service.ComponentTypeConfigService;
import com.shineyue.htmldesign.service.ComponentTypeService;
import com.shineyue.htmldesign.service.MenuService;
import com.shineyue.htmldesign.service.PageComponentConfigService;
import com.shineyue.htmldesign.service.PageComponentService;
import com.shineyue.htmldesign.service.impl.ComponentTypeConfigServiceImpl;
import com.shineyue.htmldesign.service.impl.ComponentTypeServiceImpl;
import com.shineyue.htmldesign.service.impl.MenuServiceImpl;
import com.shineyue.htmldesign.service.impl.PageComponentConfigServiceImpl;
import com.shineyue.htmldesign.service.impl.PageComponentServiceImpl;

@Controller
@RequestMapping(value = "/html")
public class HtmlController {
	ComponentTypeConfigService compTypeCfgService = new ComponentTypeConfigServiceImpl();
	ComponentTypeService compTypeService = new ComponentTypeServiceImpl();
	PageComponentService pageComponentService = new PageComponentServiceImpl();
	PageComponentConfigService pageComponentConfigService = new PageComponentConfigServiceImpl();

	MenuService menuService = new MenuServiceImpl();

	@RequestMapping(value = "/initJsonFile", method = RequestMethod.GET)
	public String initJsonFile(HttpServletRequest req,
			HttpServletResponse resp, HttpSession session) {

		List<Page> list = menuService.listAllPages();
		JsonObject json = new JsonObject();
		try {
			for (Page p : list) {
				json = initJsonPage(p);

				byte[] ret = json.toString().getBytes("utf-8");
				File file = new File("download/" + p.getCaption() + ".json");
				if (!file.exists())
					file.createNewFile();
				FileOutputStream out = new FileOutputStream(file);
				out.write(ret);
				out.close();
			}
			File filetmp = new File("download.zip");
			if (filetmp.exists())
				filetmp.delete();

			zip("dowload.zip", new File("download"));
			resp.setContentType("application/octet-stream");
			resp.setCharacterEncoding("UTF-8");
			resp.setHeader("content-disposition", "attachment;filename="
					+ URLEncoder.encode("页面.zip", "UTF-8"));
			File f = new File("dowload.zip");
			InputStream inputStream = new FileInputStream(f);
			resp.setContentLength(inputStream.available());

			OutputStream os = resp.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			inputStream.close();
			os.close();
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return null;
	}

	private JsonObject initJsonPage(Page p) {

		JsonObject json = new JsonObject();
		json.addProperty("caption", p.getCaption());
		json.add("children", initList(p, null));
		return json;
	}

	private JsonArray initList(Page p, PageComponent parent) {

		JsonArray children = new JsonArray();
		List<PageComponent> pcList = pageComponentService.listPageComponent(p,
				parent);
		for (PageComponent pc : pcList) {
			JsonObject child = new JsonObject();
			// 转化为json
			child.addProperty("caption", pc.getCaption());
			child.addProperty("name", pc.getName());
			child.addProperty("id", pc.getId());
			List<PageComponentConfig> pccList = pageComponentConfigService
					.selectByPageComponet(pc);
			for (PageComponentConfig pcc : pccList) {
				child.addProperty(pcc.getConfigKey(), pcc.getConfigValue());
			}

			if (pc.getComponentTypeId() == 7) {

				// 初始化下一级
				child.add("children", initList(p, pc));
			} else {

			}
			children.add(child);
		}
		return children;
	}

	@RequestMapping(value = "/initHtmlFile", method = RequestMethod.GET)
	public String initHtmlFile(HttpServletRequest req,
			HttpServletResponse resp, HttpSession session) {
		List<Page> list = menuService.listAllPages();
		List<AbstractComponent> compList = new ArrayList<AbstractComponent>();
		try {
			for (Page p : list) {
				compList = initPage(p);
				for (AbstractComponent comp : compList) {
					// System.out.println(comp.getHtml());
				}
				Panel panel = new Panel();
				panel.setChildren(compList);
				String html = panel.buildHtml();

				try {
					byte[] ret = html.toString().getBytes("utf-8");
					File file = new File("download/" + p.getCaption() + ".html");
					if (!file.exists())
						file.createNewFile();
					FileOutputStream out = new FileOutputStream(file);
					out.write(ret);
					out.close();
				} catch (Exception e) {

				}
			}
			File filetmp = new File("download.zip");
			if (filetmp.exists())
				filetmp.delete();

			zip("dowload.zip", new File("src/test"));
			resp.setContentType("application/octet-stream");
			resp.setCharacterEncoding("UTF-8");
			resp.setHeader("content-disposition", "attachment;filename="
					+ URLEncoder.encode("页面.zip", "UTF-8"));
			File f = new File("dowload.zip");
			InputStream inputStream = new FileInputStream(f);
			resp.setContentLength(inputStream.available());

			OutputStream os = resp.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			inputStream.close();
			os.close();
			os.flush();
		} catch (Exception e) {
		}
		return null;
	}

	private List<AbstractComponent> initPage(Page p) {
		List<PageComponent> pcList = pageComponentService.listPageComponent(p,
				null);
		List<AbstractComponent> ret = new ArrayList<AbstractComponent>();
		for (PageComponent pc : pcList) {
			if (pc.getComponentTypeId().equals(1)) {
				Textarea component = new Textarea();
				JsonObject config = new JsonObject();
				config.addProperty("caption", pc.getCaption());
				config.addProperty("name", pc.getName());
				component.setConfig(config);
				component.setChildren(initChild(p, pc));
				ret.add(component);
			} else if (pc.getComponentTypeId().equals(7)) {
				PanelComponent component = new Panel();
				JsonObject config = new JsonObject();
				config.addProperty("caption", pc.getCaption());
				config.addProperty("name", pc.getName());
				component.setConfig(config);
				component.setChildren(initChild(p, pc));
				ret.add(component);
			}
		}
		return ret;
	}

	private List<AbstractComponent> initChild(Page p, PageComponent parent) {

		List<AbstractComponent> children = new ArrayList<AbstractComponent>();
		List<PageComponent> pcList = pageComponentService.listPageComponent(p,
				parent);
		for (PageComponent pc : pcList) {
			if (pc.getComponentTypeId().equals(1)) {

				AbstractComponent child = new Textarea();
				// 转化为json
				JsonObject config = new JsonObject();
				config.addProperty("caption", pc.getCaption());
				config.addProperty("name", pc.getName());
				List<PageComponentConfig> pccList = pageComponentConfigService
						.selectByPageComponet(pc);
				for (PageComponentConfig pcc : pccList) {
					config.addProperty(pcc.getConfigKey(), pcc.getConfigValue());
				}

				if (pc.getComponentTypeId() == 7) {

					// 初始化下一级
					child.setChildren(initChild(p, pc));
				} else {

				}
				children.add(child);
			}
		}
		return children;
	}

	private void zip(String zipFileName, File inputFile) throws Exception {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				zipFileName));
		zip(out, inputFile, "");
		System.out.println("zip done");
		out.close();
	}

	private void zip(ZipOutputStream out, File f, String base) throws Exception {
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			out.putNextEntry(new ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], base + fl[i].getName());
			}
		} else {
			out.putNextEntry(new ZipEntry(base));
			FileInputStream in = new FileInputStream(f);
			int b;
			System.out.println(base);
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			in.close();
		}
	}
}
