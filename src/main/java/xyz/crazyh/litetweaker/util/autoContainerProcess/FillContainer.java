package xyz.crazyh.litetweaker.util.autoContainerProcess;

import fi.dy.masa.malilib.overlay.message.MessageUtils;
import fi.dy.masa.malilib.util.inventory.InventoryUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketOpenWindow;
import xyz.crazyh.litetweaker.config.Configs;
import xyz.crazyh.litetweaker.config.TweaksToggle;
import xyz.crazyh.litetweaker.util.RefreshInventory;

public class FillContainer {
    public static boolean fill(SPacketOpenWindow packetIn) {
        if (Configs.Generic.AUTO_FILL_CONTAINER.getBooleanValue()) {
            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayerSP playerSP = mc.player;

            int winID = packetIn.getWindowId();

            long minLimit = Configs.Generic.AUTO_FILL_CONTAINER_LIMIT.getIntegerValue();
            Slot bestSlot = null;

            Container container = ((GuiContainer)mc.currentScreen).inventorySlots;

            for (int i = packetIn.getSlotCount(); i < packetIn.getSlotCount() + 36; i++) {
                Slot slot = container.getSlot(i);
                if (!slot.getHasStack()) {
                    continue;
                }

                long counter = playerSP.inventoryContainer.inventorySlots.stream().filter(slot1 -> InventoryUtils.areStacksEqual(slot.getStack(), slot1.getStack())).count();

                if (counter > minLimit) {
                    minLimit = counter;
                    bestSlot = slot;
                } else if (counter == minLimit && bestSlot != null && !InventoryUtils.areStacksEqual(slot.getStack(), bestSlot.getStack())) {
                    bestSlot = null;
                }
            }

            if (bestSlot !=null && !playerSP.inventory.mainInventory.isEmpty()) {
                int success = 0;
                for (int i = packetIn.getSlotCount(); i < packetIn.getSlotCount() + 36; i++) {
                    Slot slot = container.getSlot(i);
                    if (slot != bestSlot && InventoryUtils.areStacksEqual(slot.getStack(), bestSlot.getStack())) {
                        mc.playerController.windowClick(winID, slot.slotNumber, 0, ClickType.QUICK_MOVE, playerSP);
                        success++;
                    }
                }
                mc.playerController.windowClick(winID, bestSlot.slotNumber, 0, ClickType.QUICK_MOVE, playerSP);
                MessageUtils.printCustomActionbarMessage("litetweaker.message.fill_container", packetIn.getGuiId(), success);
            }


            /*for (int i = 0; i < 27; i++) {
                shiftMove((GuiContainer) mc.currentScreen, packetIn.getSlotCount()+i, packetIn.getWindowId());
            }*/

            //RefreshInventory.silentRefreshInv();
            playerSP.closeScreen();
            return true;
        }

        return false;
    }

}
