package bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSource;

public class PaAnCustomJRdataSource extends JRAbstractBeanDataSource {

	/**
	 * dataSource
	 */
	private Collection<ItemStatReport> data;
	private Object currentBean;
	private Iterator<ItemStatReport> iterator;
	private List<ItemStatReport> allData;
	private int index = -1;

	public PaAnCustomJRdataSource(Collection<ItemStatReport> collection) {
		super(true);
		if (collection.size() > 0) {
			this.allData = (List<ItemStatReport>) collection;
			List<ItemStatReport> list = (List<ItemStatReport>) collection;
			Collections.sort(list, new Comparator<ItemStatReport>() {
				public int compare(ItemStatReport o1, ItemStatReport o2) {
					return o1.getSeqNumber() - o2.getSeqNumber();
				}
			});
			List<ItemStatReport> dataList = new ArrayList<ItemStatReport>();
			ItemStatReport itmesr = list.get(0);
			int seqNumber = itmesr.getSeqNumber();
			for (ItemStatReport isr : allData) {
				if (isr.getSeqNumber() == seqNumber) {
					dataList.add(isr);
				} else {
					break;
				}
			}
			this.data = dataList;
			this.iterator = data.iterator();
		}

	}

	/**
	 *
	 */
	public boolean next() {
		index++;
		boolean hasNext = false;
		if (this.iterator != null) {
			hasNext = this.iterator.hasNext();
			if (hasNext) {
				this.currentBean = this.iterator.next();
			}
		}
		return hasNext;
	}

	/**
	 *
	 */
	public Object getFieldValue(JRField field) throws JRException {
		Object value = null;
		String fieldName = field.getName();
		String[] arr = fieldName.split("_");
		if (arr.length > 1) {
			int seqNumber = Integer.parseInt(arr[1]);
			int dataLength = this.data.size();
			int inteNumber = (seqNumber * dataLength) + this.index;
			ItemStatReport positionObj = this.allData.get(inteNumber);
			ItemStatReport currentObj = (ItemStatReport) currentBean;
			if (currentObj.getStockOrgName().equals(
					positionObj.getStockOrgName())) {
				value = getFieldValue(positionObj, field);
			} else {
				// FIXME:the list is sort error!
			}
		} else {
			value = getFieldValue(currentBean, field);
		}
		return value;
	}

	/**
	 *
	 */
	public void moveFirst() {
		if (this.data != null) {
			this.iterator = this.data.iterator();
		}
	}

	/**
	 * Returns the underlying bean collection used by this data source.
	 * 
	 * @return the underlying bean collection
	 */
	public Collection<ItemStatReport> getData() {
		return data;
	}

	/**
	 * Returns the total number of records/beans that this data source contains.
	 * 
	 * @return the total number of records of this data source
	 */
	public int getRecordCount() {
		return data == null ? 0 : data.size();
	}

	/**
	 * Clones this data source by creating a new instance that reuses the same
	 * underlying bean collection.
	 * 
	 * @return a clone of this data source
	 */
	public PaAnCustomJRdataSource cloneDataSource() {
		return new PaAnCustomJRdataSource(data);
	}

}