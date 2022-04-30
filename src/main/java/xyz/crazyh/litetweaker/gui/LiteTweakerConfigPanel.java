package xyz.crazyh.litetweaker.gui;

import fi.dy.masa.malilib.gui.config.liteloader.RedirectingConfigPanel;

public class LiteTweakerConfigPanel extends RedirectingConfigPanel {
    public LiteTweakerConfigPanel() {
        super(ConfigScreen::create);
    }
}
