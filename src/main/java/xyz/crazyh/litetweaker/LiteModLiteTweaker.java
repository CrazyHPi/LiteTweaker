package xyz.crazyh.litetweaker;

import com.mojang.realmsclient.dto.RealmsServer;
import com.mumfrey.liteloader.Configurable;
import com.mumfrey.liteloader.JoinGameListener;
import com.mumfrey.liteloader.LiteMod;
import com.mumfrey.liteloader.Tickable;
import com.mumfrey.liteloader.modconfig.ConfigPanel;
import fi.dy.masa.malilib.registry.Registry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.INetHandler;
import net.minecraft.network.play.server.SPacketJoinGame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.crazyh.litetweaker.config.InitHandler;
import xyz.crazyh.litetweaker.gui.LiteTweakerConfigPanel;
import xyz.crazyh.litetweaker.util.*;
import xyz.crazyh.litetweaker.util.autoInventoryProcess.DropPlayerInventory;

import java.io.File;

public class LiteModLiteTweaker implements LiteMod, Configurable, Tickable, JoinGameListener {
    public static final Logger LOGGER = LogManager.getLogger(Reference.MOD_NAME);

    /**
     * Default constructor. All LiteMods must have a default constructor. In general you should do very little
     * in the mod constructor EXCEPT for initialising any non-game-interfacing components or performing
     * sanity checking prior to initialisation
     */
    public LiteModLiteTweaker() {
    }

    /**
     * getName() should be used to return the display name of your mod and MUST NOT return null
     *
     * @see com.mumfrey.liteloader.LiteMod#getName()
     */
    @Override
    public String getName() {
        return Reference.MOD_NAME;
    }

    /**
     * getVersion() should return the same version string present in the mod metadata, although this is
     * not a strict requirement.
     *
     * @see com.mumfrey.liteloader.LiteMod#getVersion()
     */
    @Override
    public String getVersion() {
        return Reference.MOD_VERSION;
    }

    /**
     * init() is called very early in the initialisation cycle, before the game is fully initialised, this
     * means that it is important that your mod does not interact with the game in any way at this point.
     *
     * @see com.mumfrey.liteloader.LiteMod#init(File)
     */
    @Override
    public void init(File configPath) {
        Registry.INITIALIZATION_DISPATCHER.registerInitializationHandler(new InitHandler());
        RawInput.init();
    }

    /**
     * upgradeSettings is used to notify a mod that its version-specific settings are being migrated
     *
     * @see com.mumfrey.liteloader.LiteMod#upgradeSettings(String, File, File)
     */
    @Override
    public void upgradeSettings(String version, File configPath, File oldConfigPath) {
    }

    /**
     * Get the class of the configuration panel to use, the returned class must
     * have a default (no-arg) constructor
     *
     * @return configuration panel class
     */
    @Override
    public Class<? extends ConfigPanel> getConfigPanelClass() {
        return LiteTweakerConfigPanel.class;
    }

    /**
     * Called every frame
     *
     * @param minecraft    Minecraft instance
     * @param partialTicks Partial tick value
     * @param inGame       True if in-game, false if in the menu
     * @param clock        True if this is a new tick, otherwise false if it's a regular frame
     */
    @Override
    public void onTick(Minecraft minecraft, float partialTicks, boolean inGame, boolean clock) {
        AntiGhostBlock.autoClearGhostBlock(inGame, clock);
        RefreshInventory.autoRefreshInv(inGame, clock);
        AutoFish.autoReUseFishingRod(minecraft, inGame, clock);
        RandomUtils.cancelPlayerFall(minecraft, inGame, clock);
        DropPlayerInventory.autoDropInventory(inGame, clock);
        //RandomUtils.tryToSwap(inGame, clock);
    }

    @Override
    public void onJoinGame(INetHandler netHandler, SPacketJoinGame joinGamePacket, ServerData serverData, RealmsServer realmsServer) {
        RawInput.getMouse();
    }
}