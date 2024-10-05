package xyz.crazyh.litetweaker.mixins.Tweaks.fastChest;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.block.statemap.BlockStateMapper;
import net.minecraft.init.Blocks;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mixin(BlockModelShapes.class)
public abstract class BlockModelShapesMixin {
    @Shadow
    @Final
    private BlockStateMapper blockStateMapper;

    @Inject(method = "registerBuiltInBlocks", at = @At("HEAD"), cancellable = true)
    private void fastChest(Block[] builtIns, CallbackInfo ci) {
//        if (RandomUtils.readTweakValueFromConfig("fastChest")) {
        if (true) {
            List<Block> blocks = new ArrayList<>(Arrays.asList(builtIns));
            blocks.remove(Blocks.CHEST);
            blocks.remove(Blocks.TRAPPED_CHEST);
            blocks.remove(Blocks.ENDER_CHEST);
            this.blockStateMapper.registerBuiltInBlocks(blocks.toArray(new Block[0]));
            ci.cancel();
        }
    }
}
