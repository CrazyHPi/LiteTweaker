package xyz.crazyh.litetweaker.gui.info;

import malilib.overlay.widget.sub.HotkeyedBooleanConfigStatusWidget;
import malilib.util.data.ConfigOnTab;
import xyz.crazyh.litetweaker.config.TweaksToggle;

public class TweakConfigStatusWidget extends HotkeyedBooleanConfigStatusWidget {
    public TweakConfigStatusWidget(TweaksToggle config, ConfigOnTab configOnTab) {
        super(config.getBooleanConfig(), config::getKeyBind, configOnTab, "litetweaker:csi_value_tweak_toggle");
    }
}
