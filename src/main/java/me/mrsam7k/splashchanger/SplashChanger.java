package me.mrsam7k.splashchanger;

import eu.midnightdust.lib.config.MidnightConfig;
import me.mrsam7k.splashchanger.config.Config;
import net.fabricmc.api.ModInitializer;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minecraft.client.User;

public class SplashChanger implements ModInitializer {

    public static String MOD_ID = "splashchanger";

    public static User USER = null;
    public static MiniMessage miniMessage = MiniMessage.miniMessage();

    @Override
    public void onInitialize() {
        MidnightConfig.init(MOD_ID, Config.class);
    }

}
