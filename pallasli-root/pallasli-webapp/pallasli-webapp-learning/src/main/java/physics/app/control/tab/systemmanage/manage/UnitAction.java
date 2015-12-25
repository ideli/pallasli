package physics.app.control.tab.systemmanage.manage;

import java.util.ArrayList;
import java.util.List;

import physics.app.model.Unit;

import com.lyt.pallas.jdbc.dao.GenericDAO;
import com.lyt.pallas.web.physics.control.AbstractTabAction;
import com.pallasli.utils.MashalUtil;

public class UnitAction extends AbstractTabAction {
	public String getAllInfo() {
		List<Unit> list = new ArrayList<Unit>();
		list = new GenericDAO().findAll(Unit.class);
		return MashalUtil.marshal(list);
	}

	public String getInfo(int start, int limit) {
		List<Unit> list = new ArrayList<Unit>();
		list = new GenericDAO().findAll(Unit.class, start, limit);
		return MashalUtil.marshal(list);
	}

	public int count() {
		return new GenericDAO().count(Unit.class);
	}
}
