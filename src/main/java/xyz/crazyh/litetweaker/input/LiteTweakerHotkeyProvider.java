package xyz.crazyh.litetweaker.input;

import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.input.Hotkey;
import fi.dy.masa.malilib.input.HotkeyCategory;
import fi.dy.masa.malilib.input.HotkeyProvider;
import xyz.crazyh.litetweaker.Reference;
import xyz.crazyh.litetweaker.config.Configs;
import xyz.crazyh.litetweaker.config.Hotkeys;
import xyz.crazyh.litetweaker.config.TweaksToggle;

import java.util.List;

public class LiteTweakerHotkeyProvider implements HotkeyProvider {
    public static final LiteTweakerHotkeyProvider INSTANCE = new LiteTweakerHotkeyProvider();
    /**
     * Returns a list of all hotkeys that should be registered.
     * This is called when the master hotkey list in malilib is being rebuilt,
     * Any hotkeys not on the returned list will not function!
     */
    @Override
    public List<? extends Hotkey> getAllHotkeys() {
        ImmutableList.Builder<Hotkey> builder = ImmutableList.builder();

        builder.addAll(Hotkeys.HOTKEY_LIST);
        builder.addAll(TweaksToggle.TOGGLE_HOTKEYS);

        return builder.build();
    }

    /**
     * Returns a list of all the hotkeys, grouped in categories.
     * This is mostly just used for the keybind overlap info hover text.
     */
    @Override
    public List<HotkeyCategory> getHotkeysByCategories() {
        return ImmutableList.of(
                new HotkeyCategory(Reference.MOD_INFO, "litetweaker.hotkeys.category.generic_hotkeys", Hotkeys.HOTKEY_LIST),
                new HotkeyCategory(Reference.MOD_INFO, "litetweaker.hotkeys.category.tweak_toggle_hotkeys", TweaksToggle.TOGGLE_HOTKEYS)
        );
    }
}
