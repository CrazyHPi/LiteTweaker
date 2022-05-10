package xyz.crazyh.litetweaker.util;

import fi.dy.masa.malilib.overlay.message.MessageUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketClickWindow;
import xyz.crazyh.litetweaker.config.Configs;

public class RefreshInventory {
    public static void refreshInv() {
        refresh();
        MessageUtils.printCustomActionbarMessage("litetweaker.message.refresh_inventory");
    }

    public static void silentRefreshInv() {
        refresh();
    }

    private static int autoRefreshInventoryCounter;
    public static void autoRefreshInv(boolean inGame, boolean clock) {
        if (clock && inGame) {
            if (Configs.Generic.AUTO_REFRESH_INVENTORY.getBooleanValue()) {
                if (autoRefreshInventoryCounter++ >= Configs.Generic.AUTO_REFRESH_INVENTORY_INTERVAL.getIntegerValue()) {
                    RefreshInventory.silentRefreshInv();
                    autoRefreshInventoryCounter = 0;
                }
            }
        }
    }

    private static void refresh() {
        Minecraft minecraft = Minecraft.getMinecraft();
        EntityPlayerSP playerSP = minecraft.player;
        NetHandlerPlayClient client = minecraft.getConnection();

        if (client != null && playerSP != null) {
            ItemStack itemStack = new ItemStack(Blocks.BARRIER);
            itemStack.getOrCreateSubCompound("resync").setDouble("resync", Double.NaN);
            client.sendPacket(new CPacketClickWindow(
                    playerSP.inventoryContainer.windowId,
                    -999,
                    2,
                    ClickType.QUICK_MOVE,
                    itemStack,
                    playerSP.inventoryContainer.getNextTransactionID(playerSP.inventory)
            ));
        }
    }
}
