package xyz.crazyh.litetweaker.config;

import fi.dy.masa.malilib.util.GameUtils;
import net.minecraft.client.Minecraft;
import xyz.crazyh.litetweaker.util.BlockBlackListHelper;
import xyz.crazyh.litetweaker.util.CustomTitle;
import xyz.crazyh.litetweaker.util.EntityUtils;

public class Callbacks {
    public static void init() {
        //for some reason you need this for malilib to render on/off switch properly
        //update: only in dev env, so it doesnt matter?
        Minecraft mc = GameUtils.getClient();

        Hotkeys.OPEN_CONFIG_GUI.createCallbackForAction(Actions.OPEN_CONFIG_GUI);
        Hotkeys.CLEAR_GHOST_BLOCK.createCallbackForAction(Actions.CLEAR_GHOST_BLOCK);
        Hotkeys.REFRESH_INVENTORY.createCallbackForAction(Actions.REFRESH_INVENTORY);

        Configs.Generic.CUSTOM_TITLE.setValueLoadCallback(CustomTitle::changeTitle);
        Configs.Generic.CUSTOM_TITLE.setValueChangeCallback((newValue, oldValue) -> CustomTitle.changeTitle(newValue));

        Configs.Generic.BLOCK_HIT_LIST.setValueLoadCallback(BlockBlackListHelper::updateBlockHitRestriction);
        Configs.Generic.BLOCK_HIT_LIST.setValueChangeCallback((newValue, oldValue) -> BlockBlackListHelper.updateBlockHitRestriction(newValue));

        Configs.Generic.ENTITY_HIT_LIST.setValueLoadCallback(EntityUtils::updateEntityHitRestriction);
        Configs.Generic.ENTITY_HIT_LIST.setValueChangeCallback(((newValue, oldValue) -> EntityUtils.updateEntityHitRestriction(newValue)));
    }
}
