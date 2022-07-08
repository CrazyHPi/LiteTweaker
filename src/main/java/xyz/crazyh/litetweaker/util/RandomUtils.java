package xyz.crazyh.litetweaker.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.command.ICommandSender;
import net.minecraft.tileentity.TileEntityCommandBlock;
import org.lwjgl.opengl.Display;

public class RandomUtils {
    //for some simple methods that does not deserve its own class

    public static void changeTitle(String title) {
        Display.setTitle(title);
    }

    public static void swapMainHand() {
        Minecraft.getMinecraft().gameSettings.setOptionValue(GameSettings.Options.MAIN_HAND, 1);
    }

}
