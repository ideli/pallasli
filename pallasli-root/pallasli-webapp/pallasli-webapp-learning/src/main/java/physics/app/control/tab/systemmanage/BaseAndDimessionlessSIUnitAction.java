package physics.app.control.tab.systemmanage;

import java.util.ArrayList;
import java.util.List;

import physics.app.model.BaseAndDimessionlessSIUnit;

import com.lyt.pallas.jdbc.dao.GenericDAO;
import com.lyt.pallas.web.physics.control.AbstractTabAction;
import com.pallasli.utils.MashalUtil;

public class BaseAndDimessionlessSIUnitAction extends AbstractTabAction {
	public String getAllInfo() {
		List<BaseAndDimessionlessSIUnit> list = new ArrayList<BaseAndDimessionlessSIUnit>();
		list = new GenericDAO().findAll(BaseAndDimessionlessSIUnit.class);
		return MashalUtil.marshal(list);
	}

	public String getInfo(int start, int limit) {
		List<BaseAndDimessionlessSIUnit> list = new ArrayList<BaseAndDimessionlessSIUnit>();
		list = new GenericDAO().findAll(BaseAndDimessionlessSIUnit.class,
				start, limit);
		return MashalUtil.marshal(list);
	}

	public int count() {
		return new GenericDAO().count(BaseAndDimessionlessSIUnit.class);
	}
}
