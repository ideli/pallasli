package com.pallasli.report.designer.action;

import java.io.File;

import javax.swing.AbstractButton;
import javax.swing.JFileChooser;

import com.pallasli.utils.FileUtils;

/**
 * setFileSelectionMode(int mode) 设置 JFileChooser，以允许用户只选择文件、只选择目录，或者可选择文件和目录。
 * mode参数：FILES_AND_DIRECTORIES 指示显示文件和目录。 FILES_ONLY 指示仅显示文件。 DIRECTORIES_ONLY
 * 指示仅显示目录。 showDialog(Component parent,String approveButtonText) 弹出具有自定义
 * approve 按钮的自定义文件选择器对话框。 showOpenDialog(Component parent) 弹出一个 "Open File"
 * 文件选择器对话框。 showSaveDialog(Component parent) 弹出一个 "Save File" 文件选择器对话框。
 * setMultiSelectionEnabled(boolean b) 设置文件选择器，以允许选择多个文件。 getSelectedFiles()
 * 如果将文件选择器设置为允许选择多个文件，则返回选中文件的列表(File[])。 getSelectedFile() 返回选中的文件。
 * 
 * @author lyt
 * 
 */
public class OpenAction extends AbstractAction {
	public OpenAction(AbstractButton action) {
		super(action);
	}

	@Override
	public void execute() {
		JFileChooser chooser = new JFileChooser();
		int i = chooser.showOpenDialog(mainFrame);
		System.out.println(i);
		if (i == 0) {
			File f = chooser.getSelectedFile();
			System.out.println(FileUtils.readFileToString(f));
		}
	}

}
