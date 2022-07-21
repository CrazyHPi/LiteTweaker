package xyz.crazyh.litetweaker.util;

import net.minecraft.block.BlockAir;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.Display;
import xyz.crazyh.litetweaker.config.TweaksToggle;
import xyz.crazyh.litetweaker.input.MouseInputHandlerImpl;
import xyz.crazyh.litetweaker.mixins.Random.MinecraftAccessor;

public class RandomUtils {
    //for some simple methods that does not deserve its own class

    public static void changeTitle(String title) {
        Display.setTitle(title);
    }

    public static void swapMainHand() {
        Minecraft.getMinecraft().gameSettings.setOptionValue(GameSettings.Options.MAIN_HAND, 1);
    }

    public static void swapBlock() {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP playerSP = mc.player;

        if (playerSP.isCreative()) {
            BlockPos pos = mc.objectMouseOver.getBlockPos();

            //mc.playerController.clickBlock(pos, mc.objectMouseOver.sideHit);
            ((MinecraftAccessor) mc).invokeClickMouse();
            mc.playerController.processRightClickBlock(playerSP, mc.world, pos, mc.objectMouseOver.sideHit, mc.objectMouseOver.hitVec, EnumHand.MAIN_HAND);
        }
    }

    //kinda buggy, deprecated for now
    public static void tryToSwap(boolean inGame, boolean clock) {
        if (inGame && clock && TweaksToggle.QUICK_BLOCK_SWAP.getBooleanValue()) {
            if (MouseInputHandlerImpl.isRightPressed && MouseInputHandlerImpl.isLeftPressed) {
                Minecraft mc = Minecraft.getMinecraft();
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

}
