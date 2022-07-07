package xyz.crazyh.litetweaker.mixins.Tweaks.alwaysRenderStructureVoidModel;

import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.crazyh.litetweaker.block.BlockFakeSV;

@Mixin(Block.class)
public abstract class MixinBlock {
    @Shadow
    private static void registerBlock(int id, String textualID, Block block_) {
    }

    @Inject(
            method = "registerBlocks",
            at = @At("HEAD")
    )
    private static void regSV(CallbackInfo ci) {
        registerBlock(555, "fakesv", (new BlockFakeSV()).setTranslationKey("fakesv"));
    }
}
