package com.csy.gui.utils;

import java.awt.Dimension;
import java.awt.Toolkit;

public class ScreenUtils {


    public static int getScreenWidth() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //得到屏幕的尺寸
        return screenSize.width;
	}
    
    public static int getScreenHeight() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //得到屏幕的尺寸
        return screenSize.height;
   	}
}
