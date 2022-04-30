package xyz.crazyh.litetweaker.gui;

import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.config.option.ConfigInfo;
import fi.dy.masa.malilib.config.util.ConfigUtils;
import fi.dy.masa.malilib.gui.BaseScreen;
import fi.dy.masa.malilib.gui.config.BaseConfigScreen;
import fi.dy.masa.malilib.gui.config.BaseConfigTab;
import fi.dy.masa.malilib.gui.config.ConfigTab;
import fi.dy.masa.malilib.gui.tab.ScreenTab;
import fi.dy.masa.malilib.util.data.ModInfo;
import net.minecraft.client.gui.GuiScreen;
import xyz.crazyh.litetweaker.Reference;
import xyz.crazyh.litetweaker.config.Configs;
import xyz.crazyh.litetweaker.config.Hotkeys;
import xyz.crazyh.litetweaker.config.TweaksToggle;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class ConfigScreen {
    public static final ModInfo MOD_INFO = Reference.MOD_INFO;

    private static final BaseConfigTab GENERIC = new BaseConfigTab(MOD_INFO, "generic", 160, getGenericConfigs(), ConfigScreen::create);
    private static final BaseConfigTab TWEAKS = new BaseConfigTab(MOD_INFO, "tweaks", 200, TweaksToggle.VALUES, ConfigScreen::create);
    private static final BaseConfigTab HOTKEYS =  new BaseConfigTab(MOD_INFO, "hotkeys", 160, getHotKeys(), ConfigScreen::create);

    private static final ImmutableList<ConfigTab> CONFIG_TABS = ImmutableList.of(
            GENERIC,
            TWEAKS,
            HOTKEYS
    );

    private static final ImmutableList<ScreenTab> ALL_TABS = ImmutableList.of(
            GENERIC,
            TWEAKS,
            HOTKEYS
    );

    public static void open() {
        BaseScreen.openScreen(create());
    }

    public static BaseConfigScreen create() {
        // The parent screen should not be set here, to prevent infinite recursion via
        // the call to the parent's setWorldAndResolution -> initScreen -> switch tab -> etc.
        return new BaseConfigScreen(MOD_INFO, null, ALL_TABS, GENERIC, "litetweaker.title.screen.configs", Reference.MOD_VERSION);
    }

    public static BaseConfigScreen create(@Nullable GuiScreen currentScreen) {
        // The parent screen should not be set here, to prevent infinite recursion via
        // the call to the parent's setWorldAndResolution -> initScreen -> switch tab -> etc.
        return new BaseConfigScreen(MOD_INFO, null, ALL_TABS, GENERIC, "litetweaker.title.screen.configs", Reference.MOD_VERSION);
    }

    public static ImmutableList<ConfigTab> getConfigTabs() {
        return CONFIG_TABS;
    }

    private static ImmutableList<ConfigInfo> getGenericConfigs() {
        ArrayList<ConfigInfo> list = new ArrayList<>(Configs.Generic.OPTIONS);

        ConfigUtils.sortConfigsByDisplayName(list);

        return ImmutableList.copyOf(list);
    }

    private static ImmutableList<ConfigInfo> getHotKeys() {
        ArrayList<ConfigInfo> list = new ArrayList<>(Hotkeys.HOTKEY_LIST);

        ConfigUtils.sortConfigsByDisplayName(list);

        return ImmutableList.copyOf(list);
    }
}
