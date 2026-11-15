package xyz.crazyh.litetweaker.mixins.Tweaks.fastChest;

import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockChest.class)
public abstract class BlockChestMixin extends BlockContainer {
    //
//    @Shadow
//    @Final
//    protected static AxisAlignedBB NORTH_CHEST_AABB;
//
//    @Shadow
//    @Final
//    protected static AxisAlignedBB SOUTH_CHEST_AABB;
//
//    @Shadow
//    @Final
//    protected static AxisAlignedBB WEST_CHEST_AABB;
//
//    @Shadow
//    @Final
//    protected static AxisAlignedBB EAST_CHEST_AABB;
//
    protected BlockChestMixin(Material materialIn) {
        super(materialIn);
    }

//    @Inject(method = "<init>", at = @At("RETURN"))
//    private void addDefaultState(BlockChest.Type chestTypeIn, CallbackInfo ci) {
//        this.setDefaultState(this.getDefaultState().withProperty(ChestType.TYPE, ChestType.SINGLE));
//    }
//
//    @Inject(method = "getRenderType", at = @At("HEAD"), cancellable = true)
//    private void fastChest(IBlockState state, CallbackInfoReturnable<EnumBlockRenderType> cir) {
////        if (TweaksToggle.FAST_CHEST.getBooleanValue()) {
//        if (true) {
//            cir.setReturnValue(EnumBlockRenderType.MODEL);
//        }
//    }
//
//    @ModifyArg(
//            method = "createBlockState",
//            index = 1,
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/block/state/BlockStateContainer;<init>(Lnet/minecraft/block/Block;[Lnet/minecraft/block/properties/IProperty;)V"
//            )
//    )
//    private IProperty<?>[] onCreateBlockState(IProperty<?>[] properties) {
//        properties = Arrays.copyOf(properties, properties.length + 1);
//        properties[properties.length - 1] = ChestType.TYPE;
//        return properties;
//    }
//
//    @SuppressWarnings("all")
//    @Override
//    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
//        AxisAlignedBB bb = state.getBoundingBox(world, pos);
//        EnumFacing face = ((Supplier<EnumFacing>) () -> {
//            if (bb.equals(NORTH_CHEST_AABB)) return EnumFacing.NORTH;
//            if (bb.equals(SOUTH_CHEST_AABB)) return EnumFacing.SOUTH;
//            if (bb.equals(WEST_CHEST_AABB)) return EnumFacing.WEST;
//            if (bb.equals(EAST_CHEST_AABB)) return EnumFacing.EAST;
//            return EnumFacing.UP;
//        }).get();
//
//        EnumFacing chestDir = state.getValue(BlockChest.FACING);
//        ChestType type = ChestType.getType(chestDir, face);
//        return state.withProperty(ChestType.TYPE, type);
//    }
}
