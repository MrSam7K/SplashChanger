package me.mrsam7k.splashchanger.mixin;

import me.mrsam7k.splashchanger.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.SplashRenderer;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.DefaultTooltipPositioner;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(net.minecraft.client.gui.screens.TitleScreen.class)
public abstract class TitleScreen extends Screen {
    protected TitleScreen(Component title) {
        super(title);
    }

    @Shadow
    @Nullable
    private SplashRenderer splash;

    @Shadow public abstract boolean mouseClicked(MouseButtonEvent mouseButtonEvent, boolean bl);

    @Inject(method = "mouseClicked", at = @At("HEAD"))
    protected void onSplashClick(MouseButtonEvent mouseButtonEvent, boolean bl, CallbackInfoReturnable<Boolean> cir) {
        if(mouseButtonEvent.button() != 0 || this.splash == null) return;

        double mouseX = mouseButtonEvent.x();
        double mouseY = mouseButtonEvent.y();

        if (isHoveringOverTitle(mouseX, mouseY)) {
            // this should update the splash when user goes back after changing something in the config
            this.minecraft.setScreen(Config.getMidnightScreen(new net.minecraft.client.gui.screens.TitleScreen()));
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
        }
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void renderTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        if (isHoveringOverTitle(mouseX, mouseY)) {
            guiGraphics.renderTooltip(
                    this.font,
                    List.of(ClientTooltipComponent.create(Component.translatable("splashchanger.open_config.tooltip").getVisualOrderText())),
                    mouseX,
                    mouseY,
                    DefaultTooltipPositioner.INSTANCE,
                    null
            );
        }
    }

    @Unique
    private boolean isHoveringOverTitle(double mouseX, double mouseY) {
        if (this.splash == null) return false;

        // approximate hitbox around the splash
        int centerX = this.width / 2 + 123;
        int centerY = 69;

        // some magik numbers im not going to elaborate on
        int halfW = 90;
        int halfH = 40;

        return mouseX >= centerX - halfW && mouseX <= centerX + halfW
                && mouseY >= centerY - halfH && mouseY <= centerY + halfH;
    }
}
