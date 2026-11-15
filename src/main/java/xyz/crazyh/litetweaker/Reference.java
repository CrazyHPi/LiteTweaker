package xyz.crazyh.litetweaker;

import malilib.util.data.ModInfo;
import net.fabricmc.loader.api.FabricLoader;

public class Reference {
    public static final String MOD_ID = "litetweaker";
    public static final String MOD_NAME = "LiteTweaker";
    public static final String MOD_VERSION = FabricLoader.getInstance()
            .getModContainer(MOD_ID)
            .map(container -> container.getMetadata().getVersion().getFriendlyString())
            .orElse("dev");

    public static final ModInfo MOD_INFO = new ModInfo(MOD_ID, MOD_NAME);
}
