package physics.app.control.tab.systemmanage.physicsbasicdata;

import java.util.ArrayList;
import java.util.List;

import physics.app.model.FundamentalConstants;

import com.pallasli.utils.MashalUtil;
import com.lyt.pallas.jdbc.dao.GenericDAO;
import com.lyt.pallas.web.physics.control.AbstractTabAction;

public class FundamentalConstantsAction extends AbstractTabAction {

	@SuppressWarnings("unchecked")
	public String getAllInfo() {
		List<FundamentalConstants> list = new ArrayList<FundamentalConstants>();
		list = new GenericDAO().findAll(FundamentalConstants.class);
		return MashalUtil.marshal(list);
	}

	@SuppressWarnings("unchecked")
	public String getInfo(int start, int limit) {
		List<FundamentalConstants> list = new ArrayList<FundamentalConstants>();
		list = new GenericDAO().findAll(FundamentalConstants.class, start,
				limit);
		return MashalUtil.marshal(list);
	}

	@SuppressWarnings("unchecked")
	public int count() {
		return new GenericDAO().count(FundamentalConstants.class);
	}
}
