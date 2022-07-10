package xyz.crazyh.litetweaker.config;

import xyz.crazyh.litetweaker.util.BlockBlackListHelper;
import xyz.crazyh.litetweaker.util.RandomUtils;

public class Callbacks {
    public static void init() {
        Hotkeys.OPEN_CONFIG_GUI.createCallbackForAction(Actions.OPEN_CONFIG_GUI);
        Hotkeys.CLEAR_GHOST_BLOCK.createCallbackForAction(Actions.CLEAR_GHOST_BLOCK);
        Hotkeys.REFRESH_INVENTORY.createCallbackForAction(Actions.REFRESH_INVENTORY);
        Hotkeys.TOGGLE_MAIN_HAND.createCallbackForAction(Actions.TOGGLE_MAIN_HAND);
        Hotkeys.CLEAR_GHOST_BLOCK_RC.createCallbackForAction(Actions.CLEAR_GHOST_BLOCK_RC);
        Hotkeys.SWAP_BLOCK.createCallbackForAction(Actions.SWAP_BLOCK);

        Configs.Generic.CUSTOM_TITLE.setValueLoadCallback(RandomUtils::changeTitle);
        Configs.Generic.CUSTOM_TITLE.setValueChangeCallback((newValue, oldValue) -> RandomUtils.changeTitle(newValue));

        Configs.Generic.PERIMETER_WALL_LIST.setValueLoadCallback(BlockBlackListHelper::updatePeriWallHelper);
        Configs.Generic.PERIMETER_WALL_LIST.setValueChangeCallback(((newValue, oldValue) -> BlockBlackListHelper.updatePeriWallHelper(newValue)));

        Configs.Generic.BLOCK_HIT_LIST.setValueLoadCallback(BlockBlackListHelper::updateBlockHitRestriction);
        Configs.Generic.BLOCK_HIT_LIST.setValueChangeCallback((newValue, oldValue) -> BlockBlackListHelper.updateBlockHitRestriction(newValue));

        //Configs.Generic.ENTITY_HIT_LIST.setValueLoadCallback(EntityUtils::updateEntityHitRestriction);
        //Configs.Generic.ENTITY_HIT_LIST.setValueChangeCallback(((newValue, oldValue) -> EntityUtils.updateEntityHitRestriction(newValue)));
    }
}
