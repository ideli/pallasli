package com.pallasli.report.designer.utils;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class DragUtils {

	public static void initDrag(JComponent dragComp) {
		DragSource dragSource = DragSource.getDefaultDragSource();
		dragSource.createDefaultDragGestureRecognizer(dragComp,
				DnDConstants.ACTION_COPY_OR_MOVE, new MyDragGestureListener2());

		// dragComp.addMouseListener(l);
		// dragComp.addMouseMotionListener(l);
	}
}

class MyDragGestureListener2 implements DragGestureListener {
	public void dragGestureRecognized(DragGestureEvent dge) {
		// 将数据存储到Transferable中，然后通知组件开始调用startDrag()初始化
		JTree tree = (JTree) dge.getComponent();
		TreePath path = tree.getSelectionPath();
		if (path != null) {
			DefaultMutableTreeNode selection = (DefaultMutableTreeNode) path
					.getLastPathComponent();
			MyTransferable dragAndDropTransferable = new MyTransferable(
					selection);
			dge.startDrag(DragSource.DefaultCopyDrop, dragAndDropTransferable,
					new MySourceListener());
		}
	}

}
