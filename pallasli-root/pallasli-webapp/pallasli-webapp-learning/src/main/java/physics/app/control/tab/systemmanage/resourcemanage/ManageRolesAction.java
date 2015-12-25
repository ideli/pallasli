package physics.app.control.tab.systemmanage.resourcemanage;

import java.util.ArrayList;
import java.util.List;

import physics.app.model.ManageRoles;

import com.pallasli.utils.MashalUtil;
import com.lyt.pallas.jdbc.dao.GenericDAO;
import com.lyt.pallas.web.physics.control.AbstractTabAction;

public class ManageRolesAction extends AbstractTabAction {
	@SuppressWarnings("unchecked")
	public String getAllInfo() {
		List<ManageRoles> list = new ArrayList<ManageRoles>();
		list = new GenericDAO().findAll(ManageRoles.class);
		return MashalUtil.marshal(list);
	}

	@SuppressWarnings("unchecked")
	public String getInfo(int start, int limit) {
		List<ManageRoles> list = new ArrayList<ManageRoles>();
		list = new GenericDAO().findAll(ManageRoles.class, start, limit);
		return MashalUtil.marshal(list);
	}

	@SuppressWarnings("unchecked")
	public int count() {
		return new GenericDAO().count(ManageRoles.class);
	}
}
