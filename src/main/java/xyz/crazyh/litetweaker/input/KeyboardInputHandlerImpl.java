package xyz.crazyh.litetweaker.input;

import fi.dy.masa.malilib.input.KeyboardInputHandler;

public class KeyboardInputHandlerImpl implements KeyboardInputHandler {
    public static final KeyboardInputHandlerImpl INSTANCE = new KeyboardInputHandlerImpl();

    private LeftRight lastSidewaysInput = LeftRight.NONE;
    private ForwardBack lastForwardInput = ForwardBack.NONE;

    public LeftRight getLastSidewaysInput() {
        return lastSidewaysInput;
    }

    public ForwardBack getLastForwardInput() {
        return lastForwardInput;
    }

    private KeyboardInputHandlerImpl() {
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
