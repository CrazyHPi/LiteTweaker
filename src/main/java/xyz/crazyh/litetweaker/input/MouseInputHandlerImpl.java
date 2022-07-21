package xyz.crazyh.litetweaker.input;

import fi.dy.masa.malilib.input.MouseClickHandler;
import fi.dy.masa.malilib.util.game.wrap.GameUtils;
import xyz.crazyh.litetweaker.config.TweaksToggle;

public class MouseInputHandlerImpl implements MouseClickHandler {
    public static boolean isLeftPressed;
    public static boolean isRightPressed;

    @Override
    public boolean onMouseClick(int mouseX, int mouseY, int mouseButton, boolean buttonState) {
        if (TweaksToggle.QUICK_BLOCK_SWAP.getBooleanValue()) {
            if (mouseButton == GameUtils.getClient().gameSettings.keyBindAttack.getKeyCode() + 100) {
                isLeftPressed = buttonState;
            }

            if (mouseButton == GameUtils.getClient().gameSettings.keyBindUseItem.getKeyCode() + 100) {
                isRightPressed = buttonState;
            }
        }

        return false;
    }
}
