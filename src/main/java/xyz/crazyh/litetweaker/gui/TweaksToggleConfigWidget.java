package xyz.crazyh.litetweaker.gui;

import malilib.gui.config.ConfigWidgetContext;
import malilib.gui.widget.list.entry.DataListEntryWidgetData;
import malilib.gui.widget.list.entry.config.BaseHotkeyedBooleanConfigWidget;
import xyz.crazyh.litetweaker.config.TweaksToggle;

public class TweaksToggleConfigWidget extends BaseHotkeyedBooleanConfigWidget<TweaksToggle> {

    public TweaksToggleConfigWidget(TweaksToggle baseConfig,
                                    DataListEntryWidgetData constructData,
                                    ConfigWidgetContext ctx) {
        super(baseConfig, baseConfig.getBooleanConfig(), baseConfig.getKeyBind(), constructData, ctx);
    }
}
