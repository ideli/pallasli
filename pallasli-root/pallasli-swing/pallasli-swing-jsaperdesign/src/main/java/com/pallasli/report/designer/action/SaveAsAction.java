package com.pallasli.report.designer.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.Document;

public class SaveAsAction extends AbstractAction {
	public SaveAsAction(AbstractButton action) {
		super(action);
	}

	@Override
	public void execute() {
		JFileChooser chooser = new JFileChooser();
		System.out.println(888);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.addChoosableFileFilter(new FileFilter() {// 添加文件过滤，保存文件格式为.txt格式的文件
			@Override
			public boolean accept(File f) {// 这个的意思是说，如果是目录或者为.txt文件格式的文件就显示出来
				if (!f.isDirectory()) {// 如果是目录就可以访问
					return true;
				}
				return false;
			}

			@Override
			public String getDescription() {
				return "*.txt";
			}
		});
		int returnVal = chooser.showSaveDialog(mainFrame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			System.out.println("begin");
			// 保存如果要保存的文件在用户选择的目录中已有文件存在，那么就提示一个JOpitonPane来显示一个对话框
			if (file.exists()) {// 已存在文件
				int copy = JOptionPane.showConfirmDialog(null, file
						+ "文件已经存在,是否覆盖!", "文件存在", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);// 显示一个对话框来实现是否覆盖源文件
				if (copy == JOptionPane.YES_OPTION) {
					chooser.approveSelection();
				}
			} else {
				try {
					System.out.println("writfile");
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 下面是保存文件
	// doc是文档对象 doc=JTextPane.getDocument();//获取文档 得到
	// 使用对象的反序列化来完成
	private void saveFile(File f, Document doc) {// 保存文件
		FileOutputStream fous = null;
		ObjectOutputStream oous = null;// 对象序列化
		try {
			fous = new FileOutputStream(f);// 写入到这个目录中
			oous = new ObjectOutputStream(fous);
			oous.writeObject(doc);// 把文档写入文件中
		} catch (IOException ex) {
			Logger.getLogger(SaveAction.class.getName()).log(Level.SEVERE,
					null, ex);
		} finally {
			try {
				fous.close();
				oous.close();
			} catch (IOException ex) {
				Logger.getLogger(SaveAction.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
	}

	// 下面是载入文件数据，要和保存相对应
	private void loadFile(File f) {// 载入文件
		try {
			FileInputStream fins = null;
			ObjectInputStream oins = null;
			fins = new FileInputStream(f);// 从此文件读取数据
			oins = new ObjectInputStream(fins);
			Document doc = (Document) oins.readObject();// 获取文本的数据对象
			// picTextPane.setDocument(doc);// 设置文本面板的文档
			oins.close();
			fins.close();
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(SaveAction.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (IOException ex) {
			Logger.getLogger(SaveAction.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}
}
