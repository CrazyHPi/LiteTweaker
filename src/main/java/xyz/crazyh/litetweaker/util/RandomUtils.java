package xyz.crazyh.litetweaker.util;

import com.google.gson.JsonElement;
import fi.dy.masa.malilib.util.data.json.JsonUtils;
import net.minecraft.block.BlockAir;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.Display;
import paulscode.sound.SoundSystemConfig;
import xyz.crazyh.litetweaker.config.TweaksToggle;
import xyz.crazyh.litetweaker.input.MouseInputHandlerImpl;
import xyz.crazyh.litetweaker.mixins.Accessors.EntityAccessor;
import xyz.crazyh.litetweaker.mixins.Accessors.MinecraftAccessor;
import xyz.crazyh.litetweaker.mixins.Accessors.SoundHandlerAccessor;

import java.nio.file.Path;

public class RandomUtils {
    //for some simple methods that does not deserve its own class
    public static final Minecraft mc = Minecraft.getMinecraft();

    public static void changeTitle(String title) {
        Display.setTitle(title);
    }

    public static void swapMainHand() {
        mc.gameSettings.setOptionValue(GameSettings.Options.MAIN_HAND, 1);
    }

    public static void swapBlock() {
        EntityPlayerSP playerSP = mc.player;

        if (playerSP.isCreative()) {
            BlockPos pos = mc.objectMouseOver.getBlockPos();

            if (!(mc.world.getBlockState(pos).getBlock() instanceof BlockAir)) {
                //mc.playerController.clickBlock(pos, mc.objectMouseOver.sideHit);
                ((MinecraftAccessor) mc).invokeClickMouse();
                mc.playerController.processRightClickBlock(playerSP, mc.world, pos, mc.objectMouseOver.sideHit, mc.objectMouseOver.hitVec, EnumHand.MAIN_HAND);
            }
        }
    }

    //kinda buggy, deprecated for now
    public static void tryToSwap(boolean inGame, boolean clock) {
        if (inGame && clock && TweaksToggle.QUICK_BLOCK_SWAP.getBooleanValue()) {
            if (MouseInputHandlerImpl.isRightPressed && MouseInputHandlerImpl.isLeftPressed) {

                EntityPlayerSP playerSP = mc.player;

                if (playerSP.isCreative()) {
                    BlockPos pos = mc.objectMouseOver.getBlockPos();

                    if (!(mc.world.getBlockState(pos).getBlock() instanceof BlockAir)) {
                        mc.playerController.processRightClickBlock(playerSP, mc.world, pos, mc.objectMouseOver.sideHit, mc.objectMouseOver.hitVec, EnumHand.MAIN_HAND);

                        MouseInputHandlerImpl.isRightPressed = false;
                        MouseInputHandlerImpl.isLeftPressed = false;
                    }
                }
            }
        }
    }

    public static void toggleElytraFlying() {
        EntityPlayerSP playerSP = mc.player;
        ((EntityAccessor) playerSP).invokeSetFlag(7, !playerSP.isElytraFlying());
    }

    public static void cancelPlayerFall(Minecraft minecraft, boolean inGame, boolean clock) {
        EntityPlayerSP playerSP = Minecraft.getMinecraft().player;

        if (inGame && clock && TweaksToggle.NO_FALL.getBooleanValue() && playerSP.fallDistance > 2F && !playerSP.isElytraFlying()) {
            playerSP.connection.sendPacket(new CPacketPlayer(true));
        }
    }

    public static void riseSoundChannel(boolean isEnabled, boolean isInit) {
        if (isEnabled) {
            SoundSystemConfig.setNumberNormalChannels(1024);
            SoundSystemConfig.setNumberStreamingChannels(32);
        } else {
            SoundSystemConfig.setNumberNormalChannels(28);
            SoundSystemConfig.setNumberStreamingChannels(4);
        }
        if (isInit) {
            return;
        }
        ((SoundHandlerAccessor) Minecraft.getMinecraft().getSoundHandler()).getsSndManager().reloadSoundSystem();
    }

    public static boolean readTweakValueFromConfig(String tweakName) {
        Path cfgDir = Minecraft.getMinecraft().gameDir.toPath();
        JsonElement json = JsonUtils.parseJsonFile(cfgDir.resolve("liteconfig/common/litetweaker.json"));
        boolean value = false;

        if (json != null) {
            try {
                value = json.getAsJsonObject()
                        .getAsJsonObject("Tweaks")
                        .get(tweakName)
                        .getAsBoolean();
            } catch (Exception ignored) {
            }
        }

        return value;
    }
}
