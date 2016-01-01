package com.pallasli.report.designer.page;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.pallasli.report.designer.event.DragListener;

@SuppressWarnings("serial")
public class ReportDesignPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JButton button;

	/**
	 * Create the panel.
	 */
	public ReportDesignPanel() {
		setBackground(Color.WHITE);
		setLayout(null);

		textField_1 = new JTextField();
		button = new JButton("New button");
		new DragListener(textField_1);
		new DragListener(button);
		textField_1.setBounds(6, 6, 134, 28);
		add(textField_1);
		button.setBounds(6, 138, 117, 75);
		add(button);
		ModelDemo demoModel = new ModelDemo();
		// demoModel.
		Object[][] playerInfo = {
				{ "阿呆", new Integer(66), new Integer(32), new Integer(98),
						new Boolean(false) },
				{ "阿呆", new Integer(82), new Integer(69), new Integer(128),
						new Boolean(true) }, };
		String[] Names = { "姓名", "语文", "数学", "总分", "及格" };
		JTable table = new JTable(demoModel);
		table.setPreferredScrollableViewportSize(new Dimension(550, 30));

		ModelDemo datamodel = (ModelDemo) table.getModel();
		datamodel.addColumn();
		table.setModel(datamodel);

		TableColumnModel defaultModel = table.getColumnModel();
		TableColumn cl = new TableColumn(3);
		cl.setHeaderValue("af");
		defaultModel.addColumn(cl);
		// TableColumn cl = new TableColumn();
		// cl.setWidth(200);
		// table.addColumn(cl);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setSize(600, 120);
		scrollPane.setLocation(10, 300);
		new DragListener(scrollPane);
		add(scrollPane);
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
}

class ModelDemo extends AbstractTableModel {
	/** * @author 小悠 */
	private Vector TableData;// 用来存放表格数据的线性表
	private Vector TableTitle;// 表格的 列标题

	// 注意构造<span id="8_nwp" style="width: auto; height: auto; float: none;"><a
	// id="8_nwl"
	// href="http://cpro.baidu.com/cpro/ui/uijs.php?adclass=0&app_id=0&c=news&cf=1001&ch=0&di=128&fv=20&is_app=0&jk=f954598fea2772bc&k=%BA%AF%CA%FD&k0=%BA%AF%CA%FD&kdi0=0&luki=2&mcpm=0&n=10&p=baidu&q=80007180_cpr&rb=0&rs=1&seller_id=1&sid=bc7227ea8f5954f9&ssp2=1&stid=9&t=tpclicked3_hc&td=1925163&tu=u1925163&u=http%3A%2F%2Fwww%2Eyouranshare%2Ecom%2Fblog%2Fsid%2F67%2Ehtml&urlid=0"
	// target="_blank" mpid="8" style="text-decoration: none;"><span
	// style="color:#0000ff;font-size:16px;width:auto;height:auto;float:none;">函数</span></a></span>是第一个执行的，用于初始化
	// TableData，TableTitle

	public void addColumn() {
		Vector TableData2 = new Vector();
		for (int i = 0; i < TableData.size(); i++) {

			String[] line = (String[]) TableData.get(i);
			String[] nwLine = new String[line.length + 1];
			for (int j = 0; j < line.length; j++) {
				String d = line[j];
				nwLine[j] = d;
			}
			nwLine[line.length] = "kja";
			TableData2.add(nwLine);
		}
		TableData = TableData2;
	}

	public ModelDemo() {
		// 先new 一下
		TableData = new Vector();
		TableTitle = new Vector();

		// 这里我们假设当前的表格是 3x3的
		// 先初始化 列标题,有3列
		TableTitle.add("第一列");
		TableTitle.add("第二列");
		TableTitle.add("第三列");

		// 初始化3行数据，方便起见直接用String数组保存每一行的数据
		// 第0行,都显示表格的坐标
		String[] Line1 = { "(0,0)", "(0,1)", "(0,2)" };
		// 第1行
		String[] Line2 = { "(1,0)", "(1,1)", "(1,2)" };
		// 第2行
		String[] Line3 = { "(2,0)", "(2,1)", "(2,2)" };
		// 将数据挂到线性表形成<span id="9_nwp"
		// style="width: auto; height: auto; float: none;"><a id="9_nwl"
		// href="http://cpro.baidu.com/cpro/ui/uijs.php?adclass=0&app_id=0&c=news&cf=1001&ch=0&di=128&fv=20&is_app=0&jk=f954598fea2772bc&k=%B6%FE%CE%AC&k0=%B6%FE%CE%AC&kdi0=0&luki=5&mcpm=0&n=10&p=baidu&q=80007180_cpr&rb=0&rs=1&seller_id=1&sid=bc7227ea8f5954f9&ssp2=1&stid=9&t=tpclicked3_hc&td=1925163&tu=u1925163&u=http%3A%2F%2Fwww%2Eyouranshare%2Ecom%2Fblog%2Fsid%2F67%2Ehtml&urlid=0"
		// target="_blank" mpid="9" style="text-decoration: none;"><span
		// style="color:#0000ff;font-size:16px;width:auto;height:auto;float:none;">二维</span></a></span>的数据表，形成映射
		TableData.add(Line1);
		TableData.add(Line2);
		TableData.add(Line3);

	}

	@Override
	public int getRowCount() {
		// 这里是告知表格应该有多少行，我们返回TableData上挂的String数组个数
		return TableData.size();
	}

	@Override
	public int getColumnCount() {
		// 告知列数，用标题数组的大小,这样表格就是TableData.size()行，TableTitle.size()列了
		return TableTitle.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// 获取了表格的大小，当然还要获取数据，根据坐标直接返回对应的数据
		// 小心 都是从 0开始的，小心下标越界 的问题
		// 我们之前是将 String[]挂到了线性表上，所以要先获取到String[]
		//
		// 获取每一行对应的String[]数组
		String LineTemp[] = (String[]) this.TableData.get(rowIndex);
		// 提取出对 应的数据
		return LineTemp[columnIndex];

		// 如果我们是将 线性表Vector挂到了Vector上要注意每次我们获取的是 一行线性表
		// 例如
		// return ((Vector)TableData.get(rowIndex)).get(columnIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// 这个<span id="10_nwp"
		// style="width: auto; height: auto; float: none;"><a id="10_nwl"
		// href="http://cpro.baidu.com/cpro/ui/uijs.php?adclass=0&app_id=0&c=news&cf=1001&ch=0&di=128&fv=20&is_app=0&jk=f954598fea2772bc&k=%BA%AF%CA%FD&k0=%BA%AF%CA%FD&kdi0=0&luki=2&mcpm=0&n=10&p=baidu&q=80007180_cpr&rb=0&rs=1&seller_id=1&sid=bc7227ea8f5954f9&ssp2=1&stid=9&t=tpclicked3_hc&td=1925163&tu=u1925163&u=http%3A%2F%2Fwww%2Eyouranshare%2Ecom%2Fblog%2Fsid%2F67%2Ehtml&urlid=0"
		// target="_blank" mpid="10" style="text-decoration: none;"><span
		// style="color:#0000ff;font-size:16px;width:auto;height:auto;float:none;">函数</span></a></span>式设置每个单元格的编辑属性的
		// 这个函数AbstractTableModel已经实现，默认的是 不允许编辑状态
		// 这里我们设置为允许编辑状态
		return true;// super.isCellEditable(rowIndex, columnIndex);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// 当单元格的数据发生改变的时候掉用该函数重设单元格的数据
		// 我们想一下，数据是放在TableData 中的，说白了修改数据就是修改的
		// TableData中的数据，所以我们仅仅在此处将TableData的对应数据修改即可

		((String[]) this.TableData.get(rowIndex))[columnIndex] = (String) aValue;
		super.setValueAt(aValue, rowIndex, columnIndex);
		//
		// 其实这里super的方法是调用了fireTableCellUpdated()只对应更新了
		// 对应单元格的数据
		// fireTableCellUpdated(rowIndex, columnIndex);
	}
}