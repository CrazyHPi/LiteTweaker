package xyz.crazyh.litetweaker.mixins.Tweaks.alwaysRenderBarrierModel;

import net.minecraft.block.BlockBarrier;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.init.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import xyz.crazyh.litetweaker.block.BlockFakeBarrier;
import xyz.crazyh.litetweaker.block.FakeBlocks;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(BlockRendererDispatcher.class)
public abstract class MixinBlockRenderDispatcher {
    @ModifyArg(
            method = "getModelForState",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/BlockModelShapes;getModelForState(Lnet/minecraft/block/state/IBlockState;)Lnet/minecraft/client/renderer/block/model/IBakedModel;"
            )
    )
    private IBlockState overrideStructureVoid(IBlockState state) {
        if (TweaksToggle.ALWAYS_RENDER_BARRIER_MODEL.getBooleanValue() && state.getBlock() instanceof BlockBarrier) {
            return FakeBlocks.FAKE_BARRIER.getDefaultState();
        }
        return state;
    }
}
