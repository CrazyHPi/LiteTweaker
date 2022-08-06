package xyz.crazyh.litetweaker.config;

import fi.dy.masa.malilib.action.Action;
import fi.dy.masa.malilib.action.ActionUtils;
import fi.dy.masa.malilib.action.NamedAction;
import fi.dy.masa.malilib.action.ParameterizedAction;
import fi.dy.masa.malilib.listener.EventListener;
import xyz.crazyh.litetweaker.Reference;
import xyz.crazyh.litetweaker.gui.ConfigScreen;
import xyz.crazyh.litetweaker.util.AntiGhostBlock;
import xyz.crazyh.litetweaker.util.RandomUtils;
import xyz.crazyh.litetweaker.util.RefreshInventory;

public class Actions {
    public static final NamedAction OPEN_CONFIG_GUI = register("openConfigScreen", ConfigScreen::open);
    public static final NamedAction CLEAR_GHOST_BLOCK = register("clearGhostBlock", AntiGhostBlock::clearGBLeftWithMsg);
    public static final NamedAction CLEAR_GHOST_BLOCK_RC = register("clearGhostBlockRightClick", AntiGhostBlock::clearGBRightWithMsg);
    public static final NamedAction REFRESH_INVENTORY  = register("refreshInventory", RefreshInventory::refreshInv);
    public static final NamedAction TOGGLE_MAIN_HAND = register("toggleMainHand", RandomUtils::swapMainHand);
    public static final NamedAction SWAP_BLOCK = register("swapBlock", RandomUtils::swapBlock);
    public static final NamedAction STOP_ELYTRA_FLYING = register("stopElytraFlying", RandomUtils::stopElytraFlying);

    public static void init() {
        for (TweaksToggle tweak : TweaksToggle.VALUES) {
            ActionUtils.registerBooleanConfigActions(Reference.MOD_INFO, tweak.getBooleanConfig(), tweak.getKeyBind());
        }

        ActionUtils.registerBooleanConfigActions(Configs.Generic.OPTIONS);
    }

    //register
    private static NamedAction register(String name, EventListener action) {
        return ActionUtils.register(Reference.MOD_INFO, name, action);
    }

    private static NamedAction register(String name, Action action) {
        return ActionUtils.register(Reference.MOD_INFO, name, action);
    }

    private static NamedAction register(String name, ParameterizedAction action) {
        return ActionUtils.register(Reference.MOD_INFO, name, action);
    }


}
