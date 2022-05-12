package xyz.crazyh.litetweaker.mixins.placeIgnoreEntity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(ItemBlock.class)
public abstract class MixinItemBlock{

    @Shadow @Final protected Block block;

    @Inject(
            method = "canPlaceBlockOnSide",
            at = @At("RETURN"),
            cancellable = true
    )
    private void skipCollision(World worldIn, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (TweaksToggle.PLACE_IGNORE_ENTITY.getBooleanValue() && player.isCreative()) {
            cir.setReturnValue(worldIn.mayPlace(this.block, pos, true, side, player));
        }
    }

    @ModifyArg(
            method = "onItemUse",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;mayPlace(Lnet/minecraft/block/Block;Lnet/minecraft/util/math/BlockPos;ZLnet/minecraft/util/EnumFacing;Lnet/minecraft/entity/Entity;)Z"
            )
    )
    private boolean skipCollisionCheck(boolean bl) {
        EntityPlayerSP sp = Minecraft.getMinecraft().player;
        return TweaksToggle.PLACE_IGNORE_ENTITY.getBooleanValue() && sp.isCreative();
    }

}
