package com.pallasli.report.designer.tooloper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.pallasli.report.designer.Main;

public class NewFileDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private String selectReportType = "0";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewFileDialog dialog = new NewFileDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewFileDialog() {
		setModal(true);
		setTitle("新建报表");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		FlowLayout fl_contentPanel = new FlowLayout();
		fl_contentPanel.setAlignment(FlowLayout.LEFT);
		contentPanel.setLayout(fl_contentPanel);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			Box verticalBox = Box.createVerticalBox();
			contentPanel.add(verticalBox);
			{
				JPanel panel = new JPanel();
				panel.setPreferredSize(new Dimension(250, 25));
				FlowLayout flowLayout = (FlowLayout) panel.getLayout();
				flowLayout.setAlignment(FlowLayout.LEFT);
				verticalBox.add(panel);
				{
					JLabel lblNewLabel = new JLabel("请选择报表类型");
					panel.add(lblNewLabel);
				}
			}
			{
				JPanel panel = new JPanel();
				panel.setBackground(Color.WHITE);
				panel.setPreferredSize(new Dimension(350, 150));
				FlowLayout flowLayout = (FlowLayout) panel.getLayout();
				flowLayout.setAlignment(FlowLayout.LEFT);
				verticalBox.add(panel);
				{
					JRadioButton rdbtnNewRadioButton = new JRadioButton("空白报表");
					rdbtnNewRadioButton.setActionCommand("0");
					rdbtnNewRadioButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							selectReportType = arg0.getActionCommand();
						}
					});
					rdbtnNewRadioButton.setPreferredSize(new Dimension(65, 65));
					rdbtnNewRadioButton.setMaximumSize(new Dimension(75, 25));
					rdbtnNewRadioButton.setIconTextGap(0);
					rdbtnNewRadioButton
							.setVerticalTextPosition(SwingConstants.BOTTOM);
					rdbtnNewRadioButton
							.setHorizontalTextPosition(SwingConstants.CENTER);
					rdbtnNewRadioButton
							.setIcon(new ImageIcon(
									NewFileDialog.class
											.getResource("/designer/icons/report/blank.gif")));
					panel.add(rdbtnNewRadioButton);
				}
				{
					JRadioButton rdbtnNewRadioButton_1 = new JRadioButton(
							"表格报表");
					rdbtnNewRadioButton_1.setActionCommand("1");
					rdbtnNewRadioButton_1
							.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									selectReportType = arg0.getActionCommand();
								}
							});
					rdbtnNewRadioButton_1
							.setPreferredSize(new Dimension(65, 65));
					rdbtnNewRadioButton_1
							.setVerticalTextPosition(SwingConstants.BOTTOM);
					rdbtnNewRadioButton_1
							.setHorizontalTextPosition(SwingConstants.CENTER);
					rdbtnNewRadioButton_1
							.setIcon(new ImageIcon(
									NewFileDialog.class
											.getResource("/designer/icons/report/styletable.gif")));
					panel.add(rdbtnNewRadioButton_1);
				}
				{
					JRadioButton rdbtnNewRadioButton_2 = new JRadioButton(
							"交叉报表");
					rdbtnNewRadioButton_2.setActionCommand("2");
					rdbtnNewRadioButton_2
							.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									selectReportType = arg0.getActionCommand();
								}
							});
					rdbtnNewRadioButton_2
							.setPreferredSize(new Dimension(65, 65));
					rdbtnNewRadioButton_2
							.setHorizontalTextPosition(SwingConstants.CENTER);
					rdbtnNewRadioButton_2
							.setVerticalTextPosition(SwingConstants.BOTTOM);
					rdbtnNewRadioButton_2
							.setIcon(new ImageIcon(
									NewFileDialog.class
											.getResource("/designer/icons/report/crosstabstyle.gif")));
					panel.add(rdbtnNewRadioButton_2);
				}
			}
			{
				JPanel panel = new JPanel();
				FlowLayout flowLayout = (FlowLayout) panel.getLayout();
				flowLayout.setAlignment(FlowLayout.LEFT);
				verticalBox.add(panel);
				{
					JLabel lblNewLabel_1 = new JLabel("");
					lblNewLabel_1.setIcon(new ImageIcon(NewFileDialog.class
							.getResource("/designer/icons/help.gif")));
					panel.add(lblNewLabel_1);
				}
				{
					JLabel lblNewLabel_2 = new JLabel(" ");
					panel.add(lblNewLabel_2);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("创建");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						if (selectReportType.equals("0")) {
							setVisible(false);
							JTabbedPane tab = Main.getInstance()
									.getTabbedPane();
							JPanel p = new ReportDesignPanel();
							tab.add(p);

						} else if (selectReportType.equals("1")) {

							setVisible(false);

							TableReportBuildingGuide dialog = new TableReportBuildingGuide();
							dialog.setLocationRelativeTo(Main.getInstance());
							dialog.setModal(true);
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
						} else if (selectReportType.equals("2")) {

							setVisible(false);

							TableReportBuildingGuide dialog = new TableReportBuildingGuide();
							dialog.setLocationRelativeTo(Main.getInstance());
							dialog.setModal(true);
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("取消");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
