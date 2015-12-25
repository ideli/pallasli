package physics.app.control.tab.systemmanage.manage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import physics.app.model.BasicUnit;
import xml.units.BasicUnits;

import com.lyt.pallas.jdbc.dao.GenericDAO;
import com.lyt.pallas.web.physics.control.AbstractTabAction;
import com.pallasli.utils.MashalUtil;
import com.pallasli.utils.UnMarshalUtil;

public class BasicUnitAction extends AbstractTabAction {

	public String getAllInfo() {
		List<BasicUnit> list = new ArrayList<BasicUnit>();
		list = new GenericDAO().findAll(BasicUnit.class);
		return MashalUtil.marshal(list);
	}

	public String getInfo(int start, int limit) {
		List<BasicUnit> list = new ArrayList<BasicUnit>();
		list = new GenericDAO().findAll(BasicUnit.class, start, limit);
		return MashalUtil.marshal(list);
	}

	public void save(Map<?, ?> map, HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("application/text; charset=utf-8");
		String unitJson = (String) map.get("jsonData");
		BasicUnit bu = UnMarshalUtil.unMarshal(unitJson, BasicUnit.class);
		Boolean flag = new BasicUnits().save(bu);
		if (flag) {
			try {
				PrintWriter out = response.getWriter();

				out.print(unitJson);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public int count() {
		return new GenericDAO().count(BasicUnit.class);
	}
}
