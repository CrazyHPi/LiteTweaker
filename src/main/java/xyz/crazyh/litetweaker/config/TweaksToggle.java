package xyz.crazyh.litetweaker.config;

import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.config.option.BooleanConfig;
import fi.dy.masa.malilib.config.option.ConfigInfo;
import fi.dy.masa.malilib.config.option.HotkeyConfig;
import fi.dy.masa.malilib.input.KeyBind;
import fi.dy.masa.malilib.input.KeyBindSettings;
import fi.dy.masa.malilib.input.callback.HotkeyCallback;
import fi.dy.masa.malilib.input.callback.ToggleBooleanWithMessageKeyCallback;
import fi.dy.masa.malilib.overlay.message.MessageHelpers;
import fi.dy.masa.malilib.util.data.ModInfo;
import xyz.crazyh.litetweaker.Reference;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

public enum TweaksToggle implements ConfigInfo {
    //sort these from A-Z (A B C D E F G H I J K L M N O P Q R S T U V W X Y Z)
    ALWAYS_RENDER_BARRIER_MODEL("alwaysRenderBarrierModel", false),
    ALWAYS_RENDER_BARRIER_PARTICLES("alwaysRenderBarrierParticles", false),
    ALWAYS_RENDER_STRUCTURE_VOID_MODEL("alwaysRenderStructureVoidModel", false),
    ANVIL_RENAME_COPY("anvilRenameCopy", false),
    AUTO_FISH("autoFish",false),
    AUTO_RESPAWN("autoRespawn", false),
    BETTER_F3N_CYCLE("betterF3nCycle", false),
    CLIENT_TIME_OVERRIDE("clientTimeOverride", false),
    //TODO CREATIVE_FLY_NO_DRAG("creativeFlyNoDrag", false),
    CREATIVE_NO_CLIP("creativeNoClip", false),
    ENTITY_CULLING("entityCulling",false),
    FAKE_NIGHT_VISION("fakeNightVision", false),
    FAST_CHEST("fastChest", false),
    MINING_GHOST_BLOCK_FIX("miningGhostBlockFix", false),
    NEW_NOTE_BLOCK("newNoteBlock", false),
    NO_FALL("noFall", false),
    PERIMETER_WALL_HELPER("perimeterWallHelper", false),
    PICK_MORE_BLOCKS("pickMoreBlocks", false),
    PLACE_IGNORE_ENTITY("placeIgnoreEntity", false),
    //PLACEMENT_OFFSET("placementOffset", false),
    PREVENT_DIG_BLOCKS_BELOW("preventDigBlocksBelow", false),
    QUICK_BLOCK_SWAP("quickBlockSwap", false),
    RAW_INPUT("rawInput", false),
    REDUCED_BOSS_BAR("reducedBossBar", false),
    RISE_CHAT_HISTORY_LIMIT("riseChatHistoryLimit",false),
    RISE_SOUND_CHANNEL("riseSoundChannel", false),
    STEP_UP("stepUp", false),
    TAB_PING_DISPLAY("tabPingDisplay", false),
    ;

    public static final ImmutableList<TweaksToggle> VALUES = ImmutableList.copyOf(values());
    public static final ImmutableList<BooleanConfig> TOGGLE_CONFIGS = ImmutableList.copyOf(VALUES.stream().map(TweaksToggle::getBooleanConfig).collect(Collectors.toList()));
    public static final ImmutableList<HotkeyConfig> TOGGLE_HOTKEYS = ImmutableList.copyOf(VALUES.stream().map(TweaksToggle::getHotKeyConfig).collect(Collectors.toList()));

    private final BooleanConfig toggleStatus;
    private final HotkeyConfig toggleHotkey;

    TweaksToggle(String name, boolean defaultValue) {
        this(name, defaultValue, KeyBindSettings.INGAME_DEFAULT);
    }

    TweaksToggle(String name, boolean defaultValue, KeyBindSettings settings) {
        this.toggleStatus = new BooleanConfig(name, defaultValue);
        this.toggleHotkey = new HotkeyConfig(name, "", settings);

        String nameLower = name.toLowerCase(Locale.ROOT);
        String nameKey = "litetweaker.feature_toggle.name." + nameLower;
        this.toggleHotkey.setNameTranslationKey(nameKey);
        this.toggleHotkey.setPrettyNameTranslationKey(nameKey);

        this.toggleStatus.setNameTranslationKey(nameKey);
        this.toggleStatus.setPrettyNameTranslationKey(nameKey);
        this.toggleStatus.setCommentTranslationKey("litetweaker.feature_toggle.comment." + nameLower);

        this.setSpecialToggleMessageFactory(null);
    }

    /**
     * This will replace the default hotkey callback with the ToggleBooleanWithMessageKeyCallback
     * variant that takes in the message factory
     */
    public void setSpecialToggleMessageFactory(@Nullable MessageHelpers.BooleanConfigMessageFactory messageFactory)
    {
        HotkeyCallback callback = new ToggleBooleanWithMessageKeyCallback(this.toggleStatus, messageFactory);
        this.toggleHotkey.getKeyBind().setCallback(callback);
    }

    public void setHotCallback(HotkeyCallback callback) {
        this.toggleHotkey.getKeyBind().setCallback(callback);
    }

    public boolean getBooleanValue() {
        return this.toggleStatus.getBooleanValue();
    }

    public BooleanConfig getBooleanConfig() {
        return this.toggleStatus;
    }

    public HotkeyConfig getHotKeyConfig() {
        return this.toggleHotkey;
    }

    public KeyBind getKeyBind() {
        return this.toggleHotkey.getKeyBind();
    }

    /**
     * @return true if the value has been changed from the default value
     */
    @Override
    public boolean isModified() {
        return this.toggleStatus.isModified() || this.toggleHotkey.isModified();
    }

    /**
     * Resets the config value back to the default value
     */
    @Override
    public void resetToDefault() {
        this.toggleStatus.resetToDefault();
        this.toggleHotkey.resetToDefault();
    }

    /**
     * Returns the (internal) name of this object, used for example in the config files
     *
     * @return the internal name of this config
     */
    @Override
    public String getName() {
        return this.toggleStatus.getName();
    }

    /**
     * @return the display name used for this object, for example for config options on the config screens
     */
    @Override
    public String getDisplayName() {
        return this.toggleStatus.getDisplayName();
    }

    /**
     * @return the ModInfo of the mod owning this object
     */
    @Override
    public ModInfo getModInfo() {
        return Reference.MOD_INFO;
    }

    /**
     * Returns the comment or description for this object. It can be for example displayed
     * when hovering over the object's name or other related screen element on some screens.
     * Newlines can be added with "\n". Can be null if there is no comment for this object.
     *
     * @return the (localized) comment, if one exists
     */
    @Override
    public Optional<String> getComment() {
        return this.toggleStatus.getComment();
    }


}
