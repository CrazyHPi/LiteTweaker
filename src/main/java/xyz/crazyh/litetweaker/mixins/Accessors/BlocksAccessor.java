package xyz.crazyh.litetweaker.mixins.Accessors;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Blocks.class)
public interface BlocksAccessor {
    @Invoker
    static Block invokeGetRegisteredBlock(String blockName) {
        throw new AssertionError();
    }
}
