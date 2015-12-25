package physics.app.control.tab.systemmanage.manage;

import java.util.ArrayList;
import java.util.List;

import physics.app.model.Operater;

import com.lyt.pallas.jdbc.dao.GenericDAO;
import com.lyt.pallas.web.physics.control.AbstractTabAction;
import com.pallasli.utils.MashalUtil;

public class OperaterAction extends AbstractTabAction {
	public String getAllInfo() {
		List<Operater> list = new ArrayList<Operater>();
		list = new GenericDAO().findAll(Operater.class);
		return MashalUtil.marshal(list);
	}

	public String getInfo(int start, int limit) {
		List<Operater> list = new ArrayList<Operater>();
		list = new GenericDAO().findAll(Operater.class, start, limit);
		return MashalUtil.marshal(list);
	}

	public int count() {
		return new GenericDAO().count(Operater.class);
	}
}
