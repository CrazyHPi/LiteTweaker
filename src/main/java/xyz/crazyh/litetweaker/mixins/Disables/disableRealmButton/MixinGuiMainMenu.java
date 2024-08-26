package xyz.crazyh.litetweaker.mixins.Disables.disableRealmButton;

import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.crazyh.litetweaker.config.DisableToggle;

@Mixin(GuiMainMenu.class)
public abstract class MixinGuiMainMenu {
    @Inject(
            method = "addSingleplayerMultiplayerButtons",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiMainMenu;addButton(Lnet/minecraft/client/gui/GuiButton;)Lnet/minecraft/client/gui/GuiButton;"
            ),
            cancellable = true
    )
    private void noRealmButt(int p_73969_1_, int p_73969_2_, CallbackInfo ci) {
        if (DisableToggle.DISABLE_REALM_BUTTON.getBooleanValue()) {
            ci.cancel();
        }
    }
}
