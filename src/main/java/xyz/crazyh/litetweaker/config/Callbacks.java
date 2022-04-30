package xyz.crazyh.litetweaker.config;

import fi.dy.masa.malilib.util.GameUtils;
import net.minecraft.client.Minecraft;

public class Callbacks {
    public static void init() {
        Minecraft mc = GameUtils.getClient();

        Hotkeys.OPEN_CONFIG_GUI.createCallbackForAction(Actions.OPEN_CONFIG_GUI);
    }
}
