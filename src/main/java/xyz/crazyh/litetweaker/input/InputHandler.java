package xyz.crazyh.litetweaker.input;

import fi.dy.masa.malilib.input.KeyboardInputHandler;
import fi.dy.masa.malilib.input.MouseInputHandler;
import fi.dy.masa.malilib.util.data.LeftRight;

public class InputHandler implements KeyboardInputHandler, MouseInputHandler {
    public static final InputHandler INSTANCE = new InputHandler();

    private LeftRight lastSidewaysInput = LeftRight.NONE;
    private ForwardBack lastForwardInput = ForwardBack.NONE;

    public LeftRight getLastSidewaysInput() {
        return lastSidewaysInput;
    }

    public ForwardBack getLastForwardInput() {
        return lastForwardInput;
    }

    private InputHandler() {
        super();
    }

    /**
     * Called on keyboard events with the key and whether the key was pressed or released.
     * @return true if further processing of this key event should be cancelled
     */
    @Override
    public boolean onKeyInput(int keyCode, int scanCode, int modifiers, boolean eventKeyState) {
        return false;
    }

    /**
     * Called on mouse events with the key or wheel value and whether the key was pressed or released.
     * @return true if further processing of this mouse button event should be cancelled
     */
    @Override
    public boolean onMouseInput(int eventButton, int wheelDelta, boolean eventButtonState) {
        return false;
    }

    public enum LeftRight
    {
        NONE,
        LEFT,
        RIGHT
    }

    public enum ForwardBack
    {
        NONE,
        FORWARD,
        BACK
    }
}
