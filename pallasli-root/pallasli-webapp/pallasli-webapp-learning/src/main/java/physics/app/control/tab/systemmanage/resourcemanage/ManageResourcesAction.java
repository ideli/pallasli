package physics.app.control.tab.systemmanage.resourcemanage;

import java.util.ArrayList;
import java.util.List;

import physics.app.model.ManageResources;

import com.pallasli.utils.MashalUtil;
import com.lyt.pallas.jdbc.dao.GenericDAO;
import com.lyt.pallas.web.physics.control.AbstractTabAction;

public class ManageResourcesAction extends AbstractTabAction {
	@SuppressWarnings("unchecked")
	public String getAllInfo() {
		List<ManageResources> list = new ArrayList<ManageResources>();
		list = new GenericDAO().findAll(ManageResources.class);
		return MashalUtil.marshal(list);
	}

	@SuppressWarnings("unchecked")
	public String getInfo(int start, int limit) {
		List<ManageResources> list = new ArrayList<ManageResources>();
		list = new GenericDAO().findAll(ManageResources.class, start, limit);
		return MashalUtil.marshal(list);
	}

	@SuppressWarnings("unchecked")
	public int count() {
		return new GenericDAO().count(ManageResources.class);
	}
}