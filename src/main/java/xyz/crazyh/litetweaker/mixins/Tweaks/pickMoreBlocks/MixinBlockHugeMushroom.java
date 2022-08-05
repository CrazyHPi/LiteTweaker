package xyz.crazyh.litetweaker.mixins.Tweaks.pickMoreBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(BlockHugeMushroom.class)
public abstract class MixinBlockHugeMushroom extends Block {
    protected MixinBlockHugeMushroom(Material materialIn) {
        super(materialIn);
    }

    @Inject(
            method = "getItem",
            at = @At("HEAD"),
            cancellable = true
    )
    private void pickItem(World worldIn, BlockPos pos, IBlockState state, CallbackInfoReturnable<ItemStack> cir) {
        if (TweaksToggle.PICK_MORE_BLOCKS.getBooleanValue()) {
            cir.setReturnValue(super.getItem(worldIn, pos, state));
        }
    }
}
