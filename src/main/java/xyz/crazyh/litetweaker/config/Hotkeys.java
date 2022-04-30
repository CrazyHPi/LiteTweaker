package xyz.crazyh.litetweaker.config;

import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.config.option.HotkeyConfig;

import java.util.List;

public class Hotkeys {
    public static final HotkeyConfig CLEAR_GHOST_BLOCK = new HotkeyConfig("clearGhostBlock", "");
    public static final HotkeyConfig OPEN_CONFIG_GUI = new HotkeyConfig("openConfigGui", "END");
    public static final HotkeyConfig REFRESH_INVENTORY = new HotkeyConfig("refreshInventory", "");

    public static final List<HotkeyConfig> HOTKEY_LIST = ImmutableList.of(
            CLEAR_GHOST_BLOCK,
            OPEN_CONFIG_GUI,
            REFRESH_INVENTORY
    );
}
