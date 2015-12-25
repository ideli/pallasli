package physics.app.control.tab.systemmanage.manage;

import java.util.ArrayList;
import java.util.List;

import physics.app.model.PhysicalVariable;

import com.lyt.pallas.jdbc.dao.GenericDAO;
import com.lyt.pallas.web.physics.control.AbstractTabAction;
import com.pallasli.utils.MashalUtil;

public class PhysicalVariableAction extends AbstractTabAction {
	public String getAllInfo() {
		List<PhysicalVariable> list = new ArrayList<PhysicalVariable>();
		list = new GenericDAO().findAll(PhysicalVariable.class);
		return MashalUtil.marshal(list);
	}

	public String getInfo(int start, int limit) {
		List<PhysicalVariable> list = new ArrayList<PhysicalVariable>();
		list = new GenericDAO().findAll(PhysicalVariable.class, start, limit);
		return MashalUtil.marshal(list);
	}

	public int count() {
		return new GenericDAO().count(PhysicalVariable.class);
	}
}
