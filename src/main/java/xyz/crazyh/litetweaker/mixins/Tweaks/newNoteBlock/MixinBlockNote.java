package xyz.crazyh.litetweaker.mixins.Tweaks.newNoteBlock;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityNote;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(BlockNote.class)
public abstract class MixinBlockNote {
    @Inject(
            method = "onBlockActivated",
            at = @At(
                    "HEAD"
            )
    )
    private void onRightClicked(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ, CallbackInfoReturnable<Boolean> cir) {
        if (!worldIn.isRemote && TweaksToggle.NEW_NOTE_BLOCK.getBooleanValue()) {
            worldIn.notifyNeighborsRespectDebug(pos, state.getBlock(), true);
        }
    }

    /*@Inject(
            method = "neighborChanged",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;getTileEntity(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/tileentity/TileEntity;",
                    shift = At.Shift.BY,
                    by = 2
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void onPowered(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, CallbackInfo ci, boolean flag, TileEntity tileentity) {
        if (!worldIn.isRemote && TweaksToggle.NEW_NOTE_BLOCK.getBooleanValue()) {
            if (tileentity instanceof TileEntityNote) {
                TileEntityNote tileentitynote = (TileEntityNote) tileentity;

                if (tileentitynote.previousRedstoneState != flag) {
                    worldIn.notifyNeighborsRespectDebug(pos, state.getBlock(), true);
                }
            }
        }
    }*/
    @Inject(
            method = "neighborChanged",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/tileentity/TileEntityNote;previousRedstoneState:Z",
                    shift = At.Shift.BY,
                    by = 3,
                    ordinal = 0
            )
    )
    private void test(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, CallbackInfo ci) {
        if (!worldIn.isRemote && TweaksToggle.NEW_NOTE_BLOCK.getBooleanValue()) {
            worldIn.notifyNeighborsRespectDebug(pos, state.getBlock(), true);
        }
    }

}
