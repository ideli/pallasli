package physics.app.control.tab.systemmanage;

import java.util.ArrayList;
import java.util.List;

import physics.app.model.DecimalMutiplesAndSubmutiples;

import com.lyt.pallas.jdbc.dao.GenericDAO;
import com.lyt.pallas.web.physics.control.AbstractTabAction;
import com.pallasli.utils.MashalUtil;

public class DecimalMutiplesAndSubmutiplesAction extends AbstractTabAction {
	public String getAllInfo() {
		List<DecimalMutiplesAndSubmutiples> list = new ArrayList<DecimalMutiplesAndSubmutiples>();
		list = new GenericDAO().findAll(DecimalMutiplesAndSubmutiples.class);
		return MashalUtil.marshal(list);
	}

	public String getInfo(int start, int limit) {
		List<DecimalMutiplesAndSubmutiples> list = new ArrayList<DecimalMutiplesAndSubmutiples>();
		list = new GenericDAO().findAll(DecimalMutiplesAndSubmutiples.class,
				start, limit);
		return MashalUtil.marshal(list);
	}

	public int count() {
		return new GenericDAO().count(DecimalMutiplesAndSubmutiples.class);
	}
}
