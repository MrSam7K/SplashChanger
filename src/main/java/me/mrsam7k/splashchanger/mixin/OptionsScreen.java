package me.mrsam7k.splashchanger.mixin;

import me.mrsam7k.splashchanger.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.gui.screens.options.OptionsScreen.class)
public class OptionsScreen extends Screen {

    public OptionsScreen(Component literalText) {
        super(literalText);
    }

    @Inject(method = "init()V", at = @At("RETURN"))
    protected void init(CallbackInfo callbackInfo) {
        if(Config.disableButton) return;

        Minecraft mc = Minecraft.getInstance();
        this.addRenderableWidget(Button.builder(Component.literal("Splash Text"), (button) -> mc.setScreen(Config.getMidnightScreen(mc.screen))

        ).build());
    }
}
