package me.mrsam7k.splashchanger.mixin;

import me.mrsam7k.splashchanger.SplashChanger;
import me.mrsam7k.splashchanger.config.Config;
import me.mrsam7k.splashchanger.config.Config.SplashMode;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Mixin(net.minecraft.client.gui.components.SplashRenderer.class)
public class SplashRenderer {

    private final Random random = new Random();

    private Map<SplashMode, String> splashStrings;

    @Mutable @Shadow @Final
    private Component splash;

    @Inject(method = "render", at = @At("HEAD"))
    public void render(GuiGraphics guiGraphics, int i, Font font, float f, CallbackInfo ci) {
        if (splashStrings == null) {
            initSplashStrings();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("§").append(Config.color.getCode());

        String[] formats = {
                Config.OBFUSCATED ? "§k" : "",
                Config.BOLD ? "§l" : "",
                Config.STRIKETHROUGH ? "§m" : "",
                Config.UNDERLINE ? "§n" : "",
                Config.ITALIC ? "§o" : ""
        };

        for (String format : formats) {
            if (!format.isEmpty())
                sb.append(format);
        }

        if (SplashChanger.USER != null)
            sb.append(splashStrings.get(Config.splashMode).replace("&", "§").replace("%name", SplashChanger.USER.getName()));

        this.splash = Component.literal(sb.toString()).setStyle(Style.EMPTY.withColor(-256));
    }

    private void initSplashStrings() {
        splashStrings = new HashMap<>();

        splashStrings.put(SplashMode.SINGLE_SPLASH, Config.customSplash);
        splashStrings.put(SplashMode.RANDOM_SPLASH, Config.customSplashes.get(random.nextInt(Config.customSplashes.size())));
        splashStrings.put(SplashMode.ORIGINAL, this.splash.getString());
        splashStrings.put(SplashMode.ORIGINAL_1_0, randomSplashFromFile("presets/1.0.txt"));
        splashStrings.put(SplashMode.ORIGINAL_1_8, randomSplashFromFile("presets/1.8.txt"));
        splashStrings.put(SplashMode.NONE, "");
    }

    private String randomSplashFromFile(String path) {
        try {
            String[] splashFileStrings = new String(
                    SplashRenderer.class.getClassLoader().getResourceAsStream(path).readAllBytes(),
                    StandardCharsets.UTF_8
            ).split("\n");
            List<String> allSplashes = Arrays.stream(splashFileStrings)
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .toList();
            return allSplashes.get(random.nextInt(allSplashes.size()));
        } catch (IOException e) {
            throw new RuntimeException("Unexpected Error. Should be able to read resource file in jar.", e);
        }
    }
}
