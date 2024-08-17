package xyz.crazyh.litetweaker.gui;

import fi.dy.masa.malilib.gui.config.ConfigWidgetContext;
import fi.dy.masa.malilib.gui.widget.list.entry.DataListEntryWidgetData;
import fi.dy.masa.malilib.gui.widget.list.entry.config.BaseHotkeyedBooleanConfigWidget;
import xyz.crazyh.litetweaker.config.DisableToggle;
import xyz.crazyh.litetweaker.config.TweaksToggle;

public class DisableToggleConfigWidget extends BaseHotkeyedBooleanConfigWidget<DisableToggle> {

    public DisableToggleConfigWidget(DisableToggle baseConfig,
                                     DataListEntryWidgetData constructData,
                                     ConfigWidgetContext ctx) {
        super(baseConfig, baseConfig.getBooleanConfig(), baseConfig.getKeyBind(), constructData, ctx);
    }
}
