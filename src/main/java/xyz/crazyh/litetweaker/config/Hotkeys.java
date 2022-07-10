package xyz.crazyh.litetweaker.config;

import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.config.option.HotkeyConfig;
import fi.dy.masa.malilib.input.KeyBindSettings;

import java.util.List;

public class Hotkeys {
    public static final HotkeyConfig CLEAR_GHOST_BLOCK = new HotkeyConfig("clearGhostBlock", "", KeyBindSettings.INGAME_MODIFIER);
    public static final HotkeyConfig CLEAR_GHOST_BLOCK_RC = new HotkeyConfig("clearGhostBlockRightClick",  "", KeyBindSettings.INGAME_MODIFIER);
    public static final HotkeyConfig OPEN_CONFIG_GUI = new HotkeyConfig("openConfigGui", "END");
    public static final HotkeyConfig REFRESH_INVENTORY = new HotkeyConfig("refreshInventory", "", KeyBindSettings.INGAME_MODIFIER);
    public static final HotkeyConfig SWAP_BLOCK = new HotkeyConfig("swapBlock", "", KeyBindSettings.INGAME_MODIFIER);
    public static final HotkeyConfig TOGGLE_MAIN_HAND = new HotkeyConfig("toggleMainHand", "", KeyBindSettings.INGAME_MODIFIER);

    public static final List<HotkeyConfig> HOTKEY_LIST = ImmutableList.of(
            CLEAR_GHOST_BLOCK,
            CLEAR_GHOST_BLOCK_RC,
            OPEN_CONFIG_GUI,
            REFRESH_INVENTORY,
            SWAP_BLOCK,
            TOGGLE_MAIN_HAND
    );
}
