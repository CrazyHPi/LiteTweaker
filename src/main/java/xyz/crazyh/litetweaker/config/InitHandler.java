package xyz.crazyh.litetweaker.config;

import fi.dy.masa.malilib.config.JsonModConfig;
import fi.dy.masa.malilib.event.InitializationHandler;
import fi.dy.masa.malilib.event.dispatch.InitializationDispatcherImpl;
import fi.dy.masa.malilib.gui.config.ConfigSearchInfo;
import fi.dy.masa.malilib.registry.Registry;
import xyz.crazyh.litetweaker.Reference;
import xyz.crazyh.litetweaker.config.option.EntityListConfig;
import xyz.crazyh.litetweaker.config.option.EntityListConfigWidget;
import xyz.crazyh.litetweaker.gui.ConfigScreen;
import xyz.crazyh.litetweaker.gui.TweaksToggleConfigWidget;
import xyz.crazyh.litetweaker.gui.info.TweakConfigStatusWidget;
import xyz.crazyh.litetweaker.input.KeyboardInputHandlerImpl;
import xyz.crazyh.litetweaker.input.LiteTweakerHotkeyProvider;
import xyz.crazyh.litetweaker.input.MouseInputHandlerImpl;

public class InitHandler implements InitializationHandler {
    /**
     * This method will be called for any registered {@link InitializationHandler}
     * when the game has been initialized and the mods can register their keybinds and configs
     * to malilib without causing class loading issues.
     * <br><br>
     * So call all your (malilib-facing) mod init stuff inside this handler!
     * <br><br>
     * The classes implementing this method should be registered to {@link InitializationDispatcherImpl}
     */
    @Override
    public void registerModHandlers() {
        //cfgs
        Registry.CONFIG_MANAGER.registerConfigHandler(new JsonModConfig(Reference.MOD_INFO, Configs.CURRENT_VERSION, Configs.CATEGORIES));

        Registry.CONFIG_SCREEN.registerConfigScreenFactory(Reference.MOD_INFO, ConfigScreen::create);
        Registry.CONFIG_TAB.registerConfigTabProvider(Reference.MOD_INFO, ConfigScreen::getConfigTabs);

        //widget
        Registry.CONFIG_WIDGET.registerConfigWidgetFactory(TweaksToggle.class, TweaksToggleConfigWidget::new);
        Registry.CONFIG_WIDGET.registerConfigSearchInfo(TweaksToggle.class, new ConfigSearchInfo<TweaksToggle>(true, true).setBooleanStorageGetter(TweaksToggle::getBooleanConfig).setKeyBindGetter(TweaksToggle::getKeyBind));
        Registry.CONFIG_WIDGET.registerConfigWidgetFactory(EntityListConfig.class, EntityListConfigWidget::new);

        Registry.CONFIG_STATUS_WIDGET.registerConfigStatusWidgetFactory(TweaksToggle.class, TweakConfigStatusWidget::new, "litetweaker:csi_value_tweak_toggle");

        //input
        Registry.HOTKEY_MANAGER.registerHotkeyProvider(LiteTweakerHotkeyProvider.INSTANCE);
        //Haven't use these two yet, so just leave them here?
        //Registry.INPUT_DISPATCHER.registerKeyboardInputHandler(KeyboardInputHandlerImpl.INSTANCE);
        Registry.INPUT_DISPATCHER.registerMouseClickHandler(new MouseInputHandlerImpl());

        //hotkey callback
        Actions.init();
        Callbacks.init();
    }
}
