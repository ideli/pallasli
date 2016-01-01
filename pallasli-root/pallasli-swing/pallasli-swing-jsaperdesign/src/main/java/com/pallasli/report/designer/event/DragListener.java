package com.pallasli.report.designer.event;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JComponent;

public class DragListener {
	JComponent component;
	int dragType;// 1 move 2 resize

	static final int DRAG = 1;
	static final int RESIZE_N = 2;
	static final int RESIZE_S = 3;
	static final int RESIZE_W = 4;
	static final int RESIZE_E = 5;
	static final int RESIZE_SW = 6;
	static final int RESIZE_SE = 7;
	static final int RESIZE_NW = 8;
	static final int RESIZE_NE = 9;

	public void dragOn() {
		// component.setDragEnabled(true);
		component.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// mpBeginOnComponent = e.getPoint();
				//
				System.out.println("drag");
			}

			@Override
			public void mouseMoved(MouseEvent eve) {
				Point curp = eve.getPoint();
				// 8 point is to resize
				boolean s0 = Math.abs(component.getSize().height - curp.y) < 3;
				boolean n0 = Math.abs(curp.y) < 3;
				boolean e0 = Math.abs(component.getSize().width - curp.x) < 3;
				boolean w0 = Math.abs(curp.x) < 3;

				boolean ns = Math.abs(component.getSize().width / 2 - curp.x) < 3;
				boolean we = Math.abs(component.getSize().height / 2 - curp.y) < 3;

				boolean nw = n0 && w0;
				boolean ne = n0 && e0;
				boolean n = n0 && ns;
				boolean sw = s0 && w0;
				boolean s = s0 && ns;
				boolean se = s0 && e0;
				boolean w = w0 && we;
				boolean e = e0 && we;

				if (n) {
					component.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));

					dragType = RESIZE_N;
				} else if (s) {
					component.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));

					dragType = RESIZE_S;
				} else if (e) {
					component.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));

					dragType = RESIZE_E;
				} else if (w) {
					component.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));

					dragType = RESIZE_W;
				} else if (nw) {
					component.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));

					dragType = RESIZE_NW;
				} else if (ne) {
					component.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));

					dragType = RESIZE_NE;
				} else if (sw) {
					component.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));

					dragType = RESIZE_SW;
				} else if (se) {
					component.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));

					dragType = RESIZE_SE;
				} else {
					component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					dragType = DRAG;
				}
				System.err.println(component.getCursor());
			}
		});
		component.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("begin");
				cpoint = component.getLocation();
				cpointOnScreen = component.getLocationOnScreen();
				mpBeginOnScreen = e.getLocationOnScreen();

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				Dimension size = component.getSize();
				mpEndOnScreen = e.getLocationOnScreen();
				Point endPoint = e.getPoint();
				if (dragType == DRAG) {

					Point newP = new Point();
					int x = mpEndOnScreen.x - mpBeginOnScreen.x + cpoint.x;
					int y = mpEndOnScreen.y - mpBeginOnScreen.y + cpoint.y;
					newP.setLocation(x, y);

					component.setLocation(newP);
				} else if (dragType == RESIZE_S) {
					component.setSize(size.width, endPoint.y);
				} else if (dragType == RESIZE_N) {
					component.setLocation(cpoint.x, +cpoint.y
							+ (mpEndOnScreen.y - mpBeginOnScreen.y));
					component.setSize(size.width, size.height
							- (mpEndOnScreen.y - mpBeginOnScreen.y));
				} else if (dragType == RESIZE_E) {
					component.setSize(size.width
							+ (mpEndOnScreen.x - mpBeginOnScreen.x),
							size.height);
				} else if (dragType == RESIZE_W) {
					component.setLocation(cpoint.x
							+ (mpEndOnScreen.x - mpBeginOnScreen.x), +cpoint.y);
					component.setSize(size.width
							- (mpEndOnScreen.x - mpBeginOnScreen.x),
							size.height);
				} else if (dragType == RESIZE_SE) {
					component
							.setSize(size.width
									+ (mpEndOnScreen.x - mpBeginOnScreen.x),
									endPoint.y);
				} else if (dragType == RESIZE_SW) {
					component.setLocation(cpoint.x
							+ (mpEndOnScreen.x - mpBeginOnScreen.x), cpoint.y);
					component.setSize(endPoint.x, endPoint.y);
				} else if (dragType == RESIZE_NE) {
					component.setLocation(cpoint.x, +cpoint.y
							+ (mpEndOnScreen.y - mpBeginOnScreen.y));
					component
							.setSize(
									size.width
											+ (mpEndOnScreen.x - mpBeginOnScreen.x),
									size.height
											- (mpEndOnScreen.y - mpBeginOnScreen.y));
				} else if (dragType == RESIZE_NW) {
					component.setLocation(cpoint.x
							+ (mpEndOnScreen.x - mpBeginOnScreen.x), cpoint.y
							+ (mpEndOnScreen.y - mpBeginOnScreen.y));
					component
							.setSize(
									size.width
											- (mpEndOnScreen.x - mpBeginOnScreen.x),
									size.height
											- (mpEndOnScreen.y - mpBeginOnScreen.y));
				}
			}
		});
		component.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				// 鼠标
			}

			@Override
			public void focusLost(FocusEvent e) {
			}
		});
	}

	public DragListener(final JComponent component) {
		this.component = component;
		dragOn();
	}

	Point cpoint;
	Point cpointOnScreen;
	Point mpBeginOnScreen;
	Point mpEndOnScreen;

}
