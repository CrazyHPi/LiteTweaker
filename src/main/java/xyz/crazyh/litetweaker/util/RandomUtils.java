package xyz.crazyh.litetweaker.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import org.lwjgl.opengl.Display;

public class RandomUtils {
    public static void changeTitle(String title) {
        Display.setTitle(title);
    }

    public static void swapMainHand() {
        Minecraft mc = Minecraft.getMinecraft();

        mc.gameSettings.setOptionValue(GameSettings.Options.MAIN_HAND, 1);
    }
}
