package com.pallasli.report.designer.page;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class TableReportBuildingGuide extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TableReportBuildingGuide dialog = new TableReportBuildingGuide();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TableReportBuildingGuide() {
		setTitle("表格报表向导");
		setBounds(new Rectangle(100, 100, 550, 350));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			Box verticalBox = Box.createVerticalBox();
			verticalBox.setPreferredSize(new Dimension(500, 300));
			contentPanel.add(verticalBox);
			{
				JPanel panel = new JPanel();
				panel.setPreferredSize(new Dimension(100, 10));
				verticalBox.add(panel);
				{
					JLabel lblNewLabel = new JLabel("");
					lblNewLabel.setIcon(new ImageIcon(
							TableReportBuildingGuide.class
									.getResource("/designer/icons/help.gif")));
					panel.add(lblNewLabel);
				}
				{
					JLabel lblNewLabel_1 = new JLabel(
							"报表数据集选择，对于所有标准报表只能有一个数据集。（必选）");
					panel.add(lblNewLabel_1);
				}
			}
			{
				JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
				tabbedPane.setPreferredSize(new Dimension(300, 250));
				verticalBox.add(tabbedPane);
				{
					JPanel panel = new JPanel();
					tabbedPane.addTab("数据集", null, panel, null);
				}
				{
					JPanel panel = new JPanel();
					tabbedPane.addTab("显示项", null, panel, null);
				}
				{
					JPanel panel = new JPanel();
					tabbedPane.addTab("分组", null, panel, null);
				}
				{
					JPanel panel = new JPanel();
					tabbedPane.addTab("统计项", null, panel, null);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnNewButton_1 = new JButton("上一步");
				buttonPane.add(btnNewButton_1);
			}
			{
				JButton btnNewButton = new JButton("下一步");
				buttonPane.add(btnNewButton);
			}
			{
				JButton okButton = new JButton("完成");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("取消");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
