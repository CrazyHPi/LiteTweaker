package xyz.crazyh.litetweaker.config;

import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.config.option.HotkeyConfig;
import fi.dy.masa.malilib.input.KeyBindSettings;

import java.util.List;

public class Hotkeys {
    public static final HotkeyConfig CLEAR_GHOST_BLOCK = new HotkeyConfig("clearGhostBlock", "", KeyBindSettings.INGAME_MODIFIER);
    public static final HotkeyConfig OPEN_CONFIG_GUI = new HotkeyConfig("openConfigGui", "END");
    public static final HotkeyConfig REFRESH_INVENTORY = new HotkeyConfig("refreshInventory", "", KeyBindSettings.INGAME_MODIFIER);

    public static final List<HotkeyConfig> HOTKEY_LIST = ImmutableList.of(
            CLEAR_GHOST_BLOCK,
            OPEN_CONFIG_GUI,
            REFRESH_INVENTORY
    );
}
