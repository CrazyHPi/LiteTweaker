package xyz.crazyh.litetweaker.mixins.Disables.disableCommandblockOutputToConsole;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.CommandBlockBaseLogic;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.crazyh.litetweaker.config.DisableToggle;

@Mixin(ServerCommandManager.class)
public abstract class MixinServerCommandManager {
    private static boolean shouldCancel;

    @Inject(
            method = "notifyListener",
            at = @At("HEAD")
    )
        private void checkSender(ICommandSender sender, ICommand command, int flags, String translationKey, Object[] translationArgs, CallbackInfo ci) {
        if (DisableToggle.DISABLE_COMMANDBLOCK_OUTPUT_TO_CONSOLE.getBooleanValue() && sender instanceof CommandBlockBaseLogic ) {
            shouldCancel = true;
        }
    }

    @Redirect(
            method = "notifyListener",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/MinecraftServer;sendMessage(Lnet/minecraft/util/text/ITextComponent;)V"
            )
    )
    private void noCommandBlockSpam(MinecraftServer instance, ITextComponent component) {
        if (!shouldCancel) {
            instance.sendMessage(component);
        } else {
            shouldCancel = false;
        }
    }
}
