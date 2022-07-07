package xyz.crazyh.litetweaker.mixins.Tweaks.alwaysRenderStructureVoidModel;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStructureVoid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(BlockStructureVoid.class)
public abstract class MixinBlockStructureVoid extends Block{

    protected MixinBlockStructureVoid(Material materialIn) {
        super(materialIn);
    }

    //private static final AxisAlignedBB STRUCTURE_VOID_AABB = new AxisAlignedBB(0.3125D, 0.3125D, 0.3125D, 0.6875D, 0.6875D, 0.6875D);

    @Inject(
            method = "getRenderType",
            at = @At("HEAD"),
            cancellable = true
    )
    private void renderStructureVoid(IBlockState state, CallbackInfoReturnable<EnumBlockRenderType> cir) {
        if (TweaksToggle.ALWAYS_RENDER_STRUCTURE_VOID_MODEL.getBooleanValue()) {
            cir.setReturnValue(EnumBlockRenderType.MODEL);
        }
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }
}
