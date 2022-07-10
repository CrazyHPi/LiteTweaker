package xyz.crazyh.litetweaker.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.command.ICommandSender;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.Display;
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

    public static void tryToSwap(boolean inGame, boolean clock) {
        if (inGame && clock) {
            if (MouseInputHandlerImpl.isRightPressed && MouseInputHandlerImpl.isLeftPressed) {
                Minecraft mc = Minecraft.getMinecraft();
                EntityPlayerSP playerSP = mc.player;

                if (playerSP.isCreative()) {
                    BlockPos pos = mc.objectMouseOver.getBlockPos();

                    mc.playerController.processRightClickBlock(playerSP, mc.world, pos, mc.objectMouseOver.sideHit, mc.objectMouseOver.hitVec, EnumHand.MAIN_HAND);

                    MouseInputHandlerImpl.isRightPressed = false;
                    MouseInputHandlerImpl.isLeftPressed = false;
                }
            }
        }
    }

}
