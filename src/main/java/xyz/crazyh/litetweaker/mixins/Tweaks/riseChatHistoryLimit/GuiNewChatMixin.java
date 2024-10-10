package xyz.crazyh.litetweaker.mixins.Tweaks.riseChatHistoryLimit;

import net.minecraft.client.gui.GuiNewChat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import xyz.crazyh.litetweaker.config.TweaksToggle;

@Mixin(GuiNewChat.class)
public abstract class GuiNewChatMixin {
    @ModifyConstant(
            method = "setChatLine",
            constant = @Constant(intValue = 100)
    )
    private int riseChatLimit(int constant){
        if (TweaksToggle.RISE_CHAT_HISTORY_LIMIT.getBooleanValue()) {
            return 23333;
        }
        return constant;
    }
}
