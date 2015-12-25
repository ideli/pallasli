package physics.app.control.tab.systemmanage.resourcemanage;

import java.util.ArrayList;
import java.util.List;

import physics.app.model.ManageGrant;

import com.pallasli.utils.MashalUtil;
import com.lyt.pallas.jdbc.dao.GenericDAO;
import com.lyt.pallas.web.physics.control.AbstractTabAction;

public class ManageGrantAction extends AbstractTabAction {
	@SuppressWarnings("unchecked")
	public String getAllInfo() {
		List<ManageGrant> list = new ArrayList<ManageGrant>();
		list = new GenericDAO().findAll(ManageGrant.class);
		return MashalUtil.marshal(list);
	}

	@SuppressWarnings("unchecked")
	public String getInfo(int start, int limit) {
		List<ManageGrant> list = new ArrayList<ManageGrant>();
		list = new GenericDAO().findAll(ManageGrant.class, start, limit);
		return MashalUtil.marshal(list);
	}

	@SuppressWarnings("unchecked")
	public int count() {
		return new GenericDAO().count(ManageGrant.class);
	}
}
