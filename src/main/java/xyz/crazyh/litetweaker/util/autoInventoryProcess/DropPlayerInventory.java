package xyz.crazyh.litetweaker.util.autoInventoryProcess;

import fi.dy.masa.malilib.config.value.BlackWhiteList;
import fi.dy.masa.malilib.util.restriction.UsageRestriction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class DropPlayerInventory {
    private static final UsageRestriction<Item> itemDropList = new UsageRestriction<>();

    public static void updateItemDropList(BlackWhiteList<Item> list) {
        itemDropList.setListContents(list);
    }
    public static void dropInventory() {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP playerSP = mc.player;

        if (mc.currentScreen instanceof GuiContainer) {
            return;
        }

        NonNullList<ItemStack> invList = playerSP.inventory.mainInventory;

        //hot bar part, but why its id is 36~45?
        for (int i = 0; i < 9; i++) {
            if (itemDropList.isAllowed(invList.get(i).getItem()) && !invList.get(i).isEmpty()) {
                mc.playerController.windowClick(0, i + 36, 1, ClickType.THROW, playerSP);
            }
        }
        //backpack part
        for (int i = 9; i < invList.size(); i++) {
            if (itemDropList.isAllowed(invList.get(i).getItem()) && !invList.get(i).isEmpty()) {
                mc.playerController.windowClick(0, i, 1, ClickType.THROW, playerSP);
            }
        }
    }

}
