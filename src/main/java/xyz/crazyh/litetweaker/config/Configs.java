package xyz.crazyh.litetweaker.config;


import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.config.category.BaseConfigOptionCategory;
import fi.dy.masa.malilib.config.category.ConfigOptionCategory;
import fi.dy.masa.malilib.config.option.*;
import fi.dy.masa.malilib.config.option.list.BlackWhiteListConfig;
import fi.dy.masa.malilib.config.option.list.BlockListConfig;
import fi.dy.masa.malilib.config.option.list.ItemListConfig;
import fi.dy.masa.malilib.config.option.list.StringListConfig;
import fi.dy.masa.malilib.config.value.BlackWhiteList;
import fi.dy.masa.malilib.input.Hotkey;
import fi.dy.masa.malilib.util.game.BlockUtils;
import fi.dy.masa.malilib.util.game.ItemUtils;
import fi.dy.masa.malilib.util.restriction.UsageRestriction;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import xyz.crazyh.litetweaker.Reference;
import xyz.crazyh.litetweaker.util.autoInventoryProcess.AutoDropContainerType;

import java.sql.Ref;
import java.util.List;

public class Configs {

    //Generic and misc options goes here
    public static class Generic {
        public static final HotkeyedBooleanConfig ADDITIONAL_BLOCK_BREAKING_COOLDOWN = new HotkeyedBooleanConfig("additionalBlockBreakingCooldown", false,"");
        public static final IntegerConfig ADDITIONAL_DELAY = new IntegerConfig("additionalDelay", 2, 0, 20);
        public static final HotkeyedBooleanConfig AUTO_CLEAR_GHOST_BLOCK = new HotkeyedBooleanConfig("autoClearGhostBlock", false, "");
        public static final IntegerConfig AUTO_CLEAR_GHOST_BLOCK_INTERVAL = new IntegerConfig("autoClearGhostBlockInterval", 100, 1, 1000);
        public static final HotkeyedBooleanConfig AUTO_DROP_CONTAINER = new HotkeyedBooleanConfig("autoDropContainer", false, "");
        public static final OptionListConfig<AutoDropContainerType> AUTO_DROP_CONTAINER_TYPE = new OptionListConfig<>("autoDropContainerType", AutoDropContainerType.SHULKER, AutoDropContainerType.VALUES);
        public static final HotkeyedBooleanConfig AUTO_FILL_CONTAINER = new HotkeyedBooleanConfig("autoFillContainer", false, "");
        public static final IntegerConfig AUTO_FILL_CONTAINER_LIMIT = new IntegerConfig("autoFillContainerLimit", 3, 1, 36);
        public static final HotkeyedBooleanConfig AUTO_REFRESH_INVENTORY = new HotkeyedBooleanConfig("autoRefreshInventory", false, "");
        public static final IntegerConfig AUTO_REFRESH_INVENTORY_INTERVAL = new IntegerConfig("autoRefreshInventoryInterval", 100, 1, 1000);
        public static final HotkeyedBooleanConfig AUTO_DROP_INVENTORY = new HotkeyedBooleanConfig("autoDropInventory", false, "");
        public static final IntegerConfig AUTO_DROP_INVENTORY_INTERVAL = new IntegerConfig("autoDropInventory", 100, 1, 12000);

        public static final IntegerConfig BACKGROUND_FPS_LIMIT = new IntegerConfig("backgroundFPSLimit", -1, -1, 999);
        public static final IntegerConfig CLIENT_TIME = new IntegerConfig("clientTime", 6000, 0, 24000);
        public static final StringConfig CUSTOM_TITLE  = new StringConfig("customTitle", "Minecraft 1.12.2");
        public static final IntegerConfig MAX_CHAT_HEIGHT = new IntegerConfig("maxChatHeight", 180, 180, 1000);
        public static final IntegerConfig MAX_CHAT_WIDTH = new IntegerConfig("maxChatWidth", 280, 280, 1000);

        public static final BlackWhiteListConfig<Block> BLOCK_HIT_LIST = new BlackWhiteListConfig<>("blockHitList", BlackWhiteList.blocks(UsageRestriction.ListType.BLACKLIST, ImmutableList.of(Blocks.DRAGON_EGG), ImmutableList.of())) ;
        public static final StringListConfig ENTITY_HIT_LIST = new StringListConfig("entityHitList", ImmutableList.of("EntityEnderCrystal"));
        //not usable cuz forge messing with registry
        //public static final BlackWhiteListConfig<Class <? extends Entity>> ENTITY_HIT_LIST = new BlackWhiteListConfig<>("entityHitList", EntityListConfig.entities(UsageRestriction.ListType.BLACKLIST, ImmutableList.of(EntityEnderCrystal.class), ImmutableList.of()));
        public static final BlockListConfig PERIMETER_WALL_LIST = new BlockListConfig("perimeterWallList", ImmutableList.of(Blocks.SANDSTONE, Blocks.NETHERRACK), BlockUtils::getBlockRegistryName, BlockUtils::getBlockByRegistryName);
        public static final BlackWhiteListConfig<Item> ITEM_DROP_LIST = new BlackWhiteListConfig<>("itemDropList", BlackWhiteList.itemNames((UsageRestriction.ListType.WHITELIST),ImmutableList.of(), ImmutableList.of("minecraft:stone")));

        public static final ImmutableList<ConfigOption<?>> OPTIONS = ImmutableList.of(
                ADDITIONAL_BLOCK_BREAKING_COOLDOWN,
                ADDITIONAL_DELAY,
                AUTO_CLEAR_GHOST_BLOCK,
                AUTO_CLEAR_GHOST_BLOCK_INTERVAL,
                AUTO_DROP_CONTAINER,
                AUTO_DROP_CONTAINER_TYPE,
                AUTO_DROP_INVENTORY,
                AUTO_DROP_INVENTORY_INTERVAL,
                AUTO_FILL_CONTAINER,
                AUTO_FILL_CONTAINER_LIMIT,
                AUTO_REFRESH_INVENTORY,
                AUTO_REFRESH_INVENTORY_INTERVAL,
                BACKGROUND_FPS_LIMIT,
                BLOCK_HIT_LIST,
                CLIENT_TIME,
                CUSTOM_TITLE,
                ENTITY_HIT_LIST,
                ITEM_DROP_LIST,
                MAX_CHAT_HEIGHT,
                MAX_CHAT_WIDTH,
                PERIMETER_WALL_LIST
        );

        public static final List<Hotkey> OPTIONS_HOTKEY = ImmutableList.of(
                ADDITIONAL_BLOCK_BREAKING_COOLDOWN,
                AUTO_CLEAR_GHOST_BLOCK,
                AUTO_DROP_CONTAINER,
                AUTO_FILL_CONTAINER,
                AUTO_REFRESH_INVENTORY
        );
    }

    public static final int CURRENT_VERSION = 1;

    public static final List<ConfigOptionCategory> CATEGORIES = ImmutableList.of(
            BaseConfigOptionCategory.normal(Reference.MOD_INFO, "Generic", Generic.OPTIONS),
            BaseConfigOptionCategory.normal(Reference.MOD_INFO, "Tweaks", TweaksToggle.TOGGLE_CONFIGS),
            BaseConfigOptionCategory.normal(Reference.MOD_INFO, "TweakHotkeys", TweaksToggle.TOGGLE_HOTKEYS),
            BaseConfigOptionCategory.normal(Reference.MOD_INFO, "Disables", DisableToggle.TOGGLE_CONFIGS),
            BaseConfigOptionCategory.normal(Reference.MOD_INFO, "DisableHotKeys", DisableToggle.TOGGLE_HOTKEYS),
            BaseConfigOptionCategory.normal(Reference.MOD_INFO, "Hotkeys", Hotkeys.HOTKEY_LIST)
    );
}
