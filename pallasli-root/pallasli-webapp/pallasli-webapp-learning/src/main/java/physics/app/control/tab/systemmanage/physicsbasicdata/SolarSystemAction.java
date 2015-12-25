package physics.app.control.tab.systemmanage.physicsbasicdata;

import java.util.ArrayList;
import java.util.List;

import physics.app.model.SolarSystem;

import com.pallasli.utils.MashalUtil;
import com.lyt.pallas.jdbc.dao.GenericDAO;
import com.lyt.pallas.web.physics.control.AbstractTabAction;

public class SolarSystemAction extends AbstractTabAction {

	@SuppressWarnings("unchecked")
	public String getAllInfo() {
		List<SolarSystem> list = new ArrayList<SolarSystem>();
		list = new GenericDAO().findAll(SolarSystem.class);
		return MashalUtil.marshal(list);
	}

	@SuppressWarnings("unchecked")
	public String getInfo(int start, int limit) {
		List<SolarSystem> list = new ArrayList<SolarSystem>();
		list = new GenericDAO().findAll(SolarSystem.class, start, limit);
		return MashalUtil.marshal(list);
	}

	@SuppressWarnings("unchecked")
	public int count() {
		return new GenericDAO().count(SolarSystem.class);
	}
}
