package com.pallasli.report.designer.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.dnd.DropTarget;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

public class TestUtils extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TestUtils dialog = new TestUtils();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	JPanel panel;// 要接受拖拽的面板
	private JTextField textField_1;
	ArrayList<Rectangle> list;
	Rectangle current = null;
	Point startPoint;

	private void makeRectangle(Point p1, Point p2) {
		int x = Math.min(p1.x, p2.x);
		int y = Math.min(p1.y, p2.y);
		int w = Math.abs(p1.x - p2.x);
		int h = Math.abs(p1.y - p2.y);
		System.out.println(x);
		System.out.println(y);
		System.out.println(w);
		System.out.println(h);

		current.setBounds(1, y, w, h);
		panel.repaint();
	}

	MouseInputListener mouseHandler = new MouseInputAdapter() {
		Point startPoint;

		public void mousePressed(MouseEvent e) {
			startPoint = e.getPoint();
			current = new Rectangle();
		}

		public void mouseReleased(MouseEvent e) {
			makeRectangle(startPoint, e.getPoint());
			if (current.width > 0 && current.height > 0) {
				list.add(current);
				current = null;
				repaint();
			}
		}

		public void mouseDragged(MouseEvent e) {
			if (current != null) {
				makeRectangle(startPoint, e.getPoint());
				repaint();
			}
		}

		private void makeRectangle(Point p1, Point p2) {
			int x = Math.min(p1.x, p2.x);
			int y = Math.min(p1.y, p2.y);
			int w = Math.abs(p1.x - p2.x);
			int h = Math.abs(p1.y - p2.y);
			current.setBounds(x, y, w, h);
		}
	};

	// public void paint(Graphics g) {
	// super.paint(g);
	// g.setColor(Color.BLACK);
	// System.out.println("***");
	//
	// for (Rectangle rect : list) {
	// g.fillRect(rect.x, rect.y, rect.width, rect.height);
	// }
	//
	// if (current != null) {
	// g.drawRect(current.x, current.y, current.width, current.height);
	// }
	// }

	JPanel panel_1;

	/**
	 * Create the dialog.
	 */
	public TestUtils() {
		// list = new ArrayList<Rectangle>();
		// addMouseMotionListener(mouseHandler);
		// addMouseListener(mouseHandler);
		panel = new JPanel();
		panel.setBackground(Color.YELLOW);
		getContentPane().add(panel, BorderLayout.CENTER);
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(400, 200);
		setTitle("最简单的拖拽示例：拖拽文件到下面（20130124）");
		DropTarget droptarg = DropUtils.initDrop(panel);

		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setPreferredSize(new Dimension(110, 110));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		getContentPane().add(panel_1, BorderLayout.NORTH);

		JTree tree = new JTree();
		DragUtils.initDrag(tree);
		panel_1.add(tree);

		textField_1 = new JTextField();
		textField_1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (current != null) {

					Point tmpPoint = e.getPoint();

					System.out.println(startPoint.x + "  " + startPoint.y);
					current = new Rectangle();
					current.x = tmpPoint.x + textField_1.getX() - startPoint.x;
					current.y = tmpPoint.y + textField_1.getY() - startPoint.y;
					current.width = textField_1.getWidth();
					current.height = textField_1.getHeight();
					System.out.println(current.x + "  " + current.y + "  "
							+ current.width + "  " + current.height);
					panel_1.getGraphics().drawRect(current.x, current.y,
							current.width, current.height);
					// panel_1.getGraphics().drawRect(102, 42, 0, 0);
					repaint();
				}
			}

		});
		textField_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				startPoint = e.getPoint();
				current = new Rectangle();
				current.x = textField_1.getX();
				current.y = textField_1.getY();
				System.out.println(current.x + "  " + current.y);
				current.width = textField_1.getWidth();
				current.height = textField_1.getHeight();
				// panel_1.getGraphics().drawRect(current.x, current.y,
				// current.width, current.height);
				//
				// panel_1.getGraphics().drawRect(0, 0, 100, 20);
				// panel_1.getGraphics().drawRect(0, 20, 100, 20);
				// panel_1.getGraphics().drawRect(0, 40, 100, 20);
				// panel_1.getGraphics().drawRect(0, 60, 100, 20);

				// textField_1.setBounds(textField_1.getX(), textField_1.getY(),
				// textField_1.getWidth(), textField_1.getHeight());
				panel_1.getGraphics().drawRect(textField_1.getX(),
						textField_1.getY(), textField_1.getWidth(),
						textField_1.getHeight());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("-----");
				System.out.println(current.x + "  " + current.y + "  "
						+ current.width + "  " + current.height);
				repaint();
				textField_1.setBounds(current.x, current.y, current.width,
						current.height);
			}
		});
		textField_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textField_1.setCursor(Cursor
						.getPredefinedCursor(Cursor.MOVE_CURSOR));
			}
		});
		textField_1.setDragEnabled(true);
		textField_1.setDropTarget(droptarg);
		// DragUtils.initDrag(textField_1);
		panel_1.add(textField_1);
		textField_1.setBounds(110, 40, 100, 20);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("New button");
		DragUtils.initDrag(btnNewButton);
		panel_1.add(btnNewButton);

		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(110, 110));
		getContentPane().add(panel_2, BorderLayout.SOUTH);
	}

}
