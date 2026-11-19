package xyz.crazyh.litetweaker.mixins.Disables.disableRealmButton;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.crazyh.litetweaker.config.DisableToggle;

@Mixin(GuiMainMenu.class)
public abstract class MixinGuiMainMenu extends GuiScreen {
    @Shadow
    private GuiButton realmsButton;

    @Inject(
            method = "initGui",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiMainMenu;addSingleplayerMultiplayerButtons(II)V",
                    shift = At.Shift.AFTER
            )
    )
    private void noRealmButt(CallbackInfo ci) {
        if (this.realmsButton != null) {
            this.realmsButton.visible = !DisableToggle.DISABLE_REALM_BUTTON.getBooleanValue();
        }
    }
}
