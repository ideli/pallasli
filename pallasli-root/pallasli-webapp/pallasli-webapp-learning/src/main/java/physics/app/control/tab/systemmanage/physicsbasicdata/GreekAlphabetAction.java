package physics.app.control.tab.systemmanage.physicsbasicdata;

import java.util.ArrayList;
import java.util.List;

import physics.app.model.GreekAlphabet;

import com.pallasli.utils.MashalUtil;
import com.lyt.pallas.jdbc.dao.GenericDAO;
import com.lyt.pallas.web.physics.control.AbstractTabAction;

public class GreekAlphabetAction extends AbstractTabAction {
	@SuppressWarnings("unchecked")
	public String getAllInfo() {
		List<GreekAlphabet> list = new ArrayList<GreekAlphabet>();
		list = new GenericDAO().findAll(GreekAlphabet.class);
		return MashalUtil.marshal(list);
	}

	@SuppressWarnings("unchecked")
	public String getInfo(int start, int limit) {
		List<GreekAlphabet> list = new ArrayList<GreekAlphabet>();
		list = new GenericDAO().findAll(GreekAlphabet.class, start, limit);
		return MashalUtil.marshal(list);
	}

	@SuppressWarnings("unchecked")
	public int count() {
		return new GenericDAO().count(GreekAlphabet.class);
	}
}
