package xyz.crazyh.litetweaker.util;

import fi.dy.masa.malilib.overlay.message.MessageUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import xyz.crazyh.litetweaker.config.TweaksToggle;

public class AutoFish {
    public static void retractFishingRod() {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP playerSP = mc.player;

        ItemStack stack = playerSP.getHeldItemMainhand();
        ItemStack stack1 = playerSP.getHeldItemOffhand();

        if (stack.isEmpty() && stack1.isEmpty() || (!(stack.getItem() instanceof ItemFishingRod) && !(stack1.getItem() instanceof ItemFishingRod))) {
            return;
        }

        EnumActionResult result = mc.playerController.processRightClick(playerSP, playerSP.world, stack.getItem() instanceof ItemFishingRod ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND);
        if (result == EnumActionResult.SUCCESS) {
            tickCounter = 0;
            MessageUtils.printCustomActionbarMessage("litetweaker.message.catch_fish");
        }
    }

    private static int tickCounter;
    public static void autoReUseFishingRod(Minecraft minecraft, boolean inGame, boolean clock) {
        EntityPlayerSP playerSP = minecraft.player;

        if (!TweaksToggle.AUTO_FISH.getBooleanValue() && !(playerSP.getHeldItemMainhand().getItem() instanceof ItemFishingRod)) {
            return;
        }

        if (inGame && clock) {
            if (tickCounter < 20) {
                tickCounter ++;
            }

            if (tickCounter == 20) {
                EnumActionResult result = minecraft.playerController.processRightClick(playerSP, playerSP.world, playerSP.getHeldItemMainhand().getItem() instanceof ItemFishingRod ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND);
                if (result == EnumActionResult.FAIL) {
                    tickCounter = 0;
                } else if (result == EnumActionResult.SUCCESS) {
                    tickCounter = 114514;
                }
            }
        }
    }
}
