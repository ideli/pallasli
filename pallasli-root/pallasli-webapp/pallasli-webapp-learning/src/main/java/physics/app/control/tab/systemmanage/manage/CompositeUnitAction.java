package physics.app.control.tab.systemmanage.manage;

import java.util.ArrayList;
import java.util.List;

import physics.app.model.CompositeUnit;

import com.lyt.pallas.jdbc.dao.GenericDAO;
import com.lyt.pallas.web.physics.control.AbstractTabAction;
import com.pallasli.utils.MashalUtil;

public class CompositeUnitAction extends AbstractTabAction {

	public String getAllInfo() {
		List<CompositeUnit> list = new ArrayList<CompositeUnit>();
		list = new GenericDAO().findAll(CompositeUnit.class);
		return MashalUtil.marshal(list);
	}

	public String getInfo(int start, int limit) {
		List<CompositeUnit> list = new ArrayList<CompositeUnit>();
		list = new GenericDAO().findAll(CompositeUnit.class, start, limit);
		return MashalUtil.marshal(list);
	}

	public int count() {
		return new GenericDAO().count(CompositeUnit.class);
	}
}
