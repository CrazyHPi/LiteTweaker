package xyz.crazyh.litetweaker.mixins.Tweaks.anvilRenameCopy;

import net.minecraft.client.gui.GuiRepair;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.crazyh.litetweaker.config.TweaksToggle;
import xyz.crazyh.litetweaker.util.ClipBoardHelper;

@Mixin(GuiRepair.class)
public abstract class MixinGuiRepair extends GuiContainer {
    @Shadow private GuiTextField nameField;

    @Shadow @Final private ContainerRepair anvil;

    public MixinGuiRepair(Container inventorySlotsIn) {
        super(inventorySlotsIn);
    }

    @Redirect(
            method = "sendSlotContents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiTextField;setText(Ljava/lang/String;)V"
            )
    )
    private void setNameToClipboard(GuiTextField instance, String textIn) {
        Slot slot = this.anvil.getSlot(0);

        if (TweaksToggle.ANVIL_RENAME_COPY.getBooleanValue() && slot != null && slot.getHasStack()){
            instance.setText(ClipBoardHelper.getClipBoardString() != null ? ClipBoardHelper.getClipBoardString() : textIn);
        } else {
            instance.setText(textIn);
        }
    }
}
