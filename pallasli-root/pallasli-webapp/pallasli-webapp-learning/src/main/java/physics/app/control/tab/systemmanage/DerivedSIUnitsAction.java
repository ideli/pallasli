package physics.app.control.tab.systemmanage;

import java.util.ArrayList;
import java.util.List;

import physics.app.model.DerivedSIUnits;

import com.lyt.pallas.jdbc.dao.GenericDAO;
import com.lyt.pallas.web.physics.control.AbstractTabAction;
import com.pallasli.utils.MashalUtil;

public class DerivedSIUnitsAction extends AbstractTabAction {
	public String getAllInfo() {
		List<DerivedSIUnits> list = new ArrayList<DerivedSIUnits>();
		list = new GenericDAO().findAll(DerivedSIUnits.class);
		return MashalUtil.marshal(list);
	}

	public String getInfo(int start, int limit) {
		List<DerivedSIUnits> list = new ArrayList<DerivedSIUnits>();
		list = new GenericDAO().findAll(DerivedSIUnits.class, start, limit);
		return MashalUtil.marshal(list);
	}

	public int count() {
		return new GenericDAO().count(DerivedSIUnits.class);
	}
}
