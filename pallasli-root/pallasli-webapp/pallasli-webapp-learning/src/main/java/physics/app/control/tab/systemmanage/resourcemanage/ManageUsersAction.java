package physics.app.control.tab.systemmanage.resourcemanage;

import java.util.ArrayList;
import java.util.List;

import physics.app.model.ManageUsers;

import com.pallasli.utils.MashalUtil;
import com.pallasli.utils.UnMarshalUtil;
import com.lyt.pallas.jdbc.dao.GenericDAO;
import com.lyt.pallas.web.physics.control.AbstractTabAction;

public class ManageUsersAction extends AbstractTabAction {
	@SuppressWarnings("unchecked")
	public String getAllInfo() {
		List<ManageUsers> list = new ArrayList<ManageUsers>();
		list = new GenericDAO().findAll(ManageUsers.class);
		return MashalUtil.marshal(list);
	}

	@SuppressWarnings("unchecked")
	public String getInfo(int start, int limit) {
		List<ManageUsers> list = new ArrayList<ManageUsers>();
		list = new GenericDAO().findAll(ManageUsers.class, start, limit);
		return MashalUtil.marshal(list);
	}

	@SuppressWarnings("unchecked")
	public int count() {
		return new GenericDAO().count(ManageUsers.class);
	}

	@SuppressWarnings("unchecked")
	public boolean save(String jsonData) {
		ManageUsers user = UnMarshalUtil.unMarshal(jsonData, ManageUsers.class);
		return new GenericDAO().save(user);
	}

	@SuppressWarnings("unchecked")
	public boolean delete(String jsonData) {
		ManageUsers user = UnMarshalUtil.unMarshal(jsonData, ManageUsers.class);
		return new GenericDAO().delete(user);
	}
}