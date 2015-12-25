package physics.app.control.tab.systemmanage;

import java.util.ArrayList;
import java.util.List;

import physics.app.model.ConversionOfUnitToSIUnits;

import com.lyt.pallas.jdbc.dao.GenericDAO;
import com.lyt.pallas.web.physics.control.AbstractTabAction;
import com.pallasli.utils.MashalUtil;

public class ConversionOfUnitToSIUnitsAction extends AbstractTabAction {
	public String getAllInfo() {
		List<ConversionOfUnitToSIUnits> list = new ArrayList<ConversionOfUnitToSIUnits>();
		list = new GenericDAO().findAll(ConversionOfUnitToSIUnits.class);
		return MashalUtil.marshal(list);
	}

	public String getInfo(int start, int limit) {
		List<ConversionOfUnitToSIUnits> list = new ArrayList<ConversionOfUnitToSIUnits>();
		list = new GenericDAO().findAll(ConversionOfUnitToSIUnits.class, start,
				limit);
		return MashalUtil.marshal(list);
	}

	public int count() {
		return new GenericDAO().count(ConversionOfUnitToSIUnits.class);
	}
}
