package xyz.crazyh.litetweaker.util;

import org.lwjgl.opengl.Display;

public class CustomTitle {
    public static void changeTitle(String title) {
        Display.setTitle(title);
    }
}
