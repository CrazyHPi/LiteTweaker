package xyz.crazyh.litetweaker.mixins.Tweaks.maxChatSize;

import net.minecraft.client.gui.GuiNewChat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import xyz.crazyh.litetweaker.config.Configs;

@Mixin(GuiNewChat.class)
public abstract class GuiNewChatMixin {
    @ModifyConstant(
            method = "calculateChatboxHeight",
            constant = @Constant(floatValue = 160.0F),
            require = 0
    )
    private static float maxChatHeight(float constant) {
        if (Configs.Generic.MAX_CHAT_HEIGHT.isModified()){
            constant = Configs.Generic.MAX_CHAT_HEIGHT.getIntegerValue();
        }
        return constant;
    }

    @ModifyConstant(
            method = "calculateChatboxWidth",
            constant = @Constant(floatValue = 280.0F),
            require = 0
    )
    private static float maxChatWidth(float constant) {
        if (Configs.Generic.MAX_CHAT_WIDTH.isModified()){
            constant = Configs.Generic.MAX_CHAT_WIDTH.getIntegerValue();
        }
        return constant;
    }
}
