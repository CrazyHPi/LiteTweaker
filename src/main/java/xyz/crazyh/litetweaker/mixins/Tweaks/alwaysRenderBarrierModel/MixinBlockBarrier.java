package xyz.crazyh.litetweaker.mixins.Tweaks.alwaysRenderBarrierModel;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBarrier;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(BlockBarrier.class)
public abstract class MixinBlockBarrier extends Block {
    protected MixinBlockBarrier(Material materialIn) {
        super(materialIn);
    }

    @Inject(
            method = "getRenderType",
            at = @At("HEAD"),
            cancellable = true
    )
    private void renderBarrierModel(IBlockState state, CallbackInfoReturnable<EnumBlockRenderType> cir) {
        if (TweaksToggle.ALWAYS_RENDER_BARRIER_MODEL.getBooleanValue()) {
            cir.setReturnValue(EnumBlockRenderType.MODEL);
        }
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return (blockAccess.getBlockState(pos.offset(side)).getBlock() instanceof BlockBarrier) || super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }
}
