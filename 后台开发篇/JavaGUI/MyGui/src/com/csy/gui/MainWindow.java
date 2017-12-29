package com.csy.gui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.text.View;

import com.csy.gui.utils.ScreenUtils;

public class MainWindow {
	private JFrame jFrame;
	private Point origin; 
	private Dimension preferredSize;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainWindow mMainWindow = new MainWindow();
		mMainWindow.initMainWindow();
	}

	void initMainWindow(){
		//显示的位置以及显示的大小
		origin = new Point(ScreenUtils.getScreenWidth()/4, 
				ScreenUtils.getScreenHeight()/4);
		preferredSize = new Dimension(ScreenUtils.getScreenWidth()/2,
				ScreenUtils.getScreenHeight()/2);
		//标题
		jFrame = new JFrame("主窗口");
		jFrame.setBounds(0, 0,preferredSize.width,preferredSize.height);
		//设置位置,不设置就默认在左上角
		jFrame.setLocation(origin.x, origin.y);
		//setSize是设定的固定大小，而setPreferredSize仅仅是设置最好的大小，
		//这个不一定与实际显示出来的控件大小一致（根据界面整体的变化而变化）
		jFrame.setPreferredSize(preferredSize);
		//此方式不能关掉窗口
		jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//设置布局管理器http://blog.csdn.net/jianggujin/article/details/50445093
		
		//必须设置才显示
		jFrame.setVisible(true);
	}
}
