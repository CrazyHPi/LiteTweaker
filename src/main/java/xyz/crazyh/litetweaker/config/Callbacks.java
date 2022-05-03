package xyz.crazyh.litetweaker.config;

import fi.dy.masa.malilib.util.GameUtils;
import net.minecraft.client.Minecraft;
import xyz.crazyh.litetweaker.util.CustomTitle;

public class Callbacks {
    public static void init() {
        Minecraft mc = GameUtils.getClient();

        Hotkeys.OPEN_CONFIG_GUI.createCallbackForAction(Actions.OPEN_CONFIG_GUI);
        Hotkeys.CLEAR_GHOST_BLOCK.createCallbackForAction(Actions.CLEAR_GHOST_BLOCK);
        Hotkeys.REFRESH_INVENTORY.createCallbackForAction(Actions.REFRESH_INVENTORY);

        Configs.Generic.CUSTOM_TITLE.setValueLoadCallback(CustomTitle::changeTitle);
        Configs.Generic.CUSTOM_TITLE.setValueChangeCallback(((newValue, oldValue) -> CustomTitle.changeTitle(newValue)));
    }
}
