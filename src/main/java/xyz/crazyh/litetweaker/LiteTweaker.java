package xyz.crazyh.litetweaker;

import malilib.registry.Registry;
import net.minecraft.client.Minecraft;
import net.ornithemc.osl.entrypoints.api.client.ClientModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.crazyh.litetweaker.config.InitHandler;
import xyz.crazyh.litetweaker.util.*;
import xyz.crazyh.litetweaker.util.autoInventoryProcess.DropPlayerInventory;

public class LiteTweaker implements ClientModInitializer {
    public static final Logger LOGGER = LogManager.getLogger(Reference.MOD_NAME);

    /**
     * Called by the OSL entrypoint system very early in the client initialisation cycle,
     * before the game is fully initialised.
     */
    @Override
    public void initClient() {
        Registry.INITIALIZATION_DISPATCHER.registerInitializationHandler(new InitHandler());
        RawInput.init();
    }

    /**
     * Called at the end of every client world tick.
     *
     * @param minecraft    Minecraft instance
     * @param inGame       True if in-game, false if in the menu
     * @param clock        True if this is a new tick, otherwise false if it's a regular frame
     */
    public static void onTick(Minecraft minecraft, boolean inGame, boolean clock) {
        AntiGhostBlock.autoClearGhostBlock(inGame, clock);
        RefreshInventory.autoRefreshInv(inGame, clock);
        AutoFish.autoReUseFishingRod(minecraft, inGame, clock);
        RandomUtils.cancelPlayerFall(minecraft, inGame, clock);
        DropPlayerInventory.autoDropInventory(inGame, clock);
        //RandomUtils.tryToSwap(inGame, clock);
    }

    public static void onJoinGame() {
        RawInput.getMouse();
    }

}
