package xyz.crazyh.litetweaker.gui.info;

import fi.dy.masa.malilib.config.option.HotkeyedBooleanConfig;
import fi.dy.masa.malilib.overlay.widget.sub.HotkeyedBooleanConfigStatusWidget;
import fi.dy.masa.malilib.util.data.ConfigOnTab;
import xyz.crazyh.litetweaker.config.DisableToggle;

public class DisableConfigStatusWidget extends HotkeyedBooleanConfigStatusWidget {
    public DisableConfigStatusWidget(DisableToggle config, ConfigOnTab configOnTab) {
        super(config.getBooleanConfig(), config::getKeyBind, configOnTab, "litetweaker:csi_value_disable_toggle");
    }
}
