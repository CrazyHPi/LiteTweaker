package xyz.crazyh.litetweaker.mixins.Accessors;

import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Block.class)
public interface BlockAccessor {
    @Invoker
    static void invokeRegisterBlock(int id, String textualID, Block block_) {
        throw new AssertionError();
    }
}
