package xyz.crazyh.litetweaker.util.autoContainerProcess;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiShulkerBox;
import net.minecraft.client.player.inventory.ContainerLocalMenu;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.network.play.server.SPacketOpenWindow;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.world.IInteractionObject;
import xyz.crazyh.litetweaker.config.Configs;

public class DropContainer {
    public static boolean drop(SPacketOpenWindow packetIn) {
        if (Configs.Generic.AUTO_DROP_CONTAINER.getValue()) {
            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayerSP playerSP = mc.player;

            if (Configs.Generic.AUTO_DROP_CONTAINER_TYPE.getValue() == AutoDropContainerType.SHULKER) {
                if ("minecraft:shulker_box".equalsIgnoreCase(packetIn.getGuiId())) {
                    dropAllContainerItems(packetIn.getWindowId(), packetIn.getSlotCount());
                    playerSP.closeScreen();
                    return true;
                }
            }

            if (Configs.Generic.AUTO_DROP_CONTAINER_TYPE.getValue() == AutoDropContainerType.ALL) {
                dropAllContainerItems(packetIn.getWindowId(), packetIn.getSlotCount());
                playerSP.closeScreen();
                return true;
            }

        }
        return false;
    }

    private static void dropAllContainerItems(int winID, int slotCount) {
        Minecraft mc = Minecraft.getMinecraft();
        for (int i = 0; i < slotCount; i++) {
            mc.playerController.windowClick(winID, i, 1, ClickType.THROW, mc.player);
        }
    }
}
