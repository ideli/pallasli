package physics.app.control.tab.systemmanage.physicsbasicdata;

import java.util.ArrayList;
import java.util.List;

import physics.app.model.ElectroMagnetSpectrum;

import com.pallasli.utils.MashalUtil;
import com.lyt.pallas.jdbc.dao.GenericDAO;
import com.lyt.pallas.web.physics.control.AbstractTabAction;

public class ElectromagneticSpectrumAction extends AbstractTabAction {
	@SuppressWarnings("unchecked")
	public String getAllInfo() {
		List<ElectroMagnetSpectrum> list = new ArrayList<ElectroMagnetSpectrum>();
		list = new GenericDAO().findAll(ElectroMagnetSpectrum.class);
		return MashalUtil.marshal(list);
	}

	@SuppressWarnings("unchecked")
	public String getInfo(int start, int limit) {
		List<ElectroMagnetSpectrum> list = new ArrayList<ElectroMagnetSpectrum>();
		list = new GenericDAO().findAll(ElectroMagnetSpectrum.class, start,
				limit);
		return MashalUtil.marshal(list);
	}

	@SuppressWarnings("unchecked")
	public int count() {
		return new GenericDAO().count(ElectroMagnetSpectrum.class);
	}
}
