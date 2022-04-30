package xyz.crazyh.litetweaker.gui.info;

import fi.dy.masa.malilib.config.option.HotkeyedBooleanConfig;
import fi.dy.masa.malilib.overlay.widget.sub.HotkeyedBooleanConfigStatusWidget;
import fi.dy.masa.malilib.util.data.ConfigOnTab;
import xyz.crazyh.litetweaker.config.TweaksToggle;

public class TweakConfigStatusWidget extends HotkeyedBooleanConfigStatusWidget {
    public TweakConfigStatusWidget(TweaksToggle config, ConfigOnTab configOnTab) {
        super(config.getBooleanConfig(), config::getKeyBind, configOnTab, "litetweaker:csi_value_tweak_toggle");
    }
}
