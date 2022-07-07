package xyz.crazyh.litetweaker.mixins.Tweaks.alwaysRenderStructureVoidModel;

import net.minecraft.block.BlockStructureVoid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.init.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import xyz.crazyh.litetweaker.block.BlockFakeSV;
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
        if (TweaksToggle.ALWAYS_RENDER_STRUCTURE_VOID_MODEL.getBooleanValue() && state.getBlock() instanceof BlockStructureVoid) {
            return FakeBlocks.FAKE_STRUCTURE_VOID.getDefaultState();
        }
        return state;
    }
}
