package xyz.crazyh.litetweaker.util;

import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.overlay.message.MessageUtils;
import fi.dy.masa.malilib.util.game.wrap.ItemWrap;
import fi.dy.masa.malilib.util.inventory.InventoryUtils;
import net.minecraft.block.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import xyz.crazyh.litetweaker.config.Configs;

public class AntiGhostBlock {
    public static void clearGBLeftWithMsg() {
        clearLeftClick();
        MessageUtils.printCustomActionbarMessage("litetweaker.message.clear_ghost_block");
    }

    public static void clearGBRightWithMsg() {
        clearRightClick();
        MessageUtils.printCustomActionbarMessage("litetweaker.message.clear_ghost_block");
    }

    private static int autoClearGhostBlockCounter;

    public static void autoClearGhostBlock(boolean inGame, boolean clock) {
        if (clock && inGame) {
            if (Configs.Generic.AUTO_CLEAR_GHOST_BLOCK.getBooleanValue()) {
                if (autoClearGhostBlockCounter++ >= Configs.Generic.AUTO_CLEAR_GHOST_BLOCK_INTERVAL.getIntegerValue()) {
                    AntiGhostBlock.clearLeftClick();
                    autoClearGhostBlockCounter = 0;
                }
            }
        }
    }

    public static void clearLeftClick() {
        Minecraft minecraft = Minecraft.getMinecraft();
        EntityPlayerSP playerSP = minecraft.player;
        NetHandlerPlayClient client = minecraft.getConnection();

        if (client == null) {
            return;
        }

        BlockPos pos = playerSP.getPosition();
        int range = 4;

        for (int i = -range; i <= range; i++) {
            for (int j = -range; j <= range; j++) {
                for (int k = -range; k <= range; k++) {
                    CPacketPlayerDigging packet = new CPacketPlayerDigging(
                            CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK,
                            new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ() + k),
                            EnumFacing.UP
                    );
                    client.sendPacket(packet);
                }
            }

        }
    }

    public static void clearRightClick() {
        Minecraft minecraft = Minecraft.getMinecraft();
        EntityPlayerSP playerSP = minecraft.player;
        NetHandlerPlayClient client = minecraft.getConnection();

        if (client == null) {
            return;
        }

        int swappedSlotMain = -1;
        int swappedSlotOff = -1;
        int hotbarSlot = playerSP.inventory.currentItem;
        Container container = playerSP.openContainer;

        // Move away the items in the player's hands
        if (ItemWrap.notEmpty(playerSP.getHeldItemMainhand())) {
            swappedSlotMain = InventoryUtils.findEmptySlotInPlayerInventory(container, false, false);

            if (swappedSlotMain != -1) {
                InventoryUtils.swapSlots(container, swappedSlotMain, hotbarSlot);
            }
        }

        if (ItemWrap.notEmpty(playerSP.getHeldItemOffhand())) {
            swappedSlotOff = InventoryUtils.findEmptySlotInPlayerInventory(container, false, false);

            if (swappedSlotOff != -1) {
                InventoryUtils.swapSlots(container, 45, hotbarSlot);
                InventoryUtils.swapSlots(container, swappedSlotOff, hotbarSlot);
            }
        }

        BlockPos playerPos = playerSP.getPosition();

        int range = 4;
        for (int i = -range; i <= range; i++) {
            for (int j = -range; j <= range; j++) {
                for (int k = -range; k <= range; k++) {
                    BlockPos pos = new BlockPos(playerPos.getX() + i, playerPos.getY() + j, playerPos.getZ() + k);
                    Block block = playerSP.getEntityWorld().getBlockState(pos).getBlock();

                    //ik its shitty but it works for now
                    if (block instanceof BlockContainer || block instanceof BlockDoor || block instanceof BlockTrapDoor || block instanceof BlockFenceGate || CLICKABLE.contains(block)) {
                        continue;
                    }

                    CPacketPlayerTryUseItemOnBlock packet = new CPacketPlayerTryUseItemOnBlock(
                            pos,
                            EnumFacing.UP,
                            EnumHand.OFF_HAND,
                            0,
                            0,
                            0);
                    client.sendPacket(packet);
                }
            }
        }

        // Restore the items the player was holding initially
        if (swappedSlotOff != -1) {
            InventoryUtils.swapSlots(container, swappedSlotOff, hotbarSlot);
            InventoryUtils.swapSlots(container, 45, hotbarSlot);
        }

        if (swappedSlotMain != -1) {
            InventoryUtils.swapSlots(container, swappedSlotMain, hotbarSlot);
        }
    }

    private static final ImmutableList<Block> CLICKABLE = ImmutableList.of(
            Blocks.ANVIL,
            Blocks.BED,
            Blocks.CRAFTING_TABLE,
            Blocks.DRAGON_EGG,
            Blocks.LEVER,
            Blocks.POWERED_COMPARATOR,
            Blocks.POWERED_REPEATER,
            Blocks.STONE_BUTTON,
            Blocks.UNPOWERED_COMPARATOR,
            Blocks.UNPOWERED_REPEATER,
            Blocks.WOODEN_BUTTON
    );
}
