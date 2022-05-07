package xyz.crazyh.litetweaker.util.autoContainerProcess;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;
import net.minecraft.network.play.server.SPacketOpenWindow;
import xyz.crazyh.litetweaker.config.Configs;
import xyz.crazyh.litetweaker.util.RefreshInventory;

public class FillContainer {
    public static boolean fill(SPacketOpenWindow packetIn) {
        if (Configs.Generic.AUTO_FILL_CONTAINER.getBooleanValue()) {
            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayerSP playerSP = mc.player;

            int winID = packetIn.getWindowId();
            for (int i = 0; i < 27; i++) {

                shiftMove((GuiContainer) mc.currentScreen, packetIn.getSlotCount()+i, packetIn.getWindowId());
            }

            RefreshInventory.silentRefreshInv();
            playerSP.closeScreen();
            //mc.playerController.windowClick(winID, packetIn.getSlotCount(), 0, ClickType.QUICK_MOVE, playerSP);

        }

        return false;
    }

    private static void moveItemFromSlotToSlot(int winID, int slotFrom, int slotTo) {
        Minecraft mc = Minecraft.getMinecraft();

        mc.playerController.windowClick(winID, slotFrom, 0, ClickType.PICKUP, mc.player);
        mc.playerController.windowClick(winID, slotTo, 0, ClickType.PICKUP, mc.player);
    }

    private static void shiftMove(GuiContainer gui, int slotNum, int winID) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP playerSP = mc.player;
        Slot slot = gui.inventorySlots.getSlot(slotNum);

        mc.playerController.windowClick(winID, slot.slotNumber, 0, ClickType.QUICK_MOVE, playerSP);

    }
}
