package xyz.crazyh.litetweaker.gui.info;

import malilib.overlay.widget.sub.HotkeyedBooleanConfigStatusWidget;
import malilib.util.data.ConfigOnTab;
import xyz.crazyh.litetweaker.config.DisableToggle;

public class DisableConfigStatusWidget extends HotkeyedBooleanConfigStatusWidget {
    public DisableConfigStatusWidget(DisableToggle config, ConfigOnTab configOnTab) {
        super(config.getBooleanConfig(), config::getKeyBind, configOnTab, "litetweaker:csi_value_disable_toggle");
    }
}
