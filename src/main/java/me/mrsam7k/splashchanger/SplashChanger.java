package me.mrsam7k.splashchanger;

import eu.midnightdust.lib.config.MidnightConfig;
import me.mrsam7k.splashchanger.config.Config;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.User;

import java.util.ArrayList;
import java.util.List;

public class SplashChanger implements ModInitializer {

    public static String MOD_ID = "splashchanger";

    public static User USER = null;

    @Override
    public void onInitialize() {
        MidnightConfig.init(MOD_ID, Config.class);
    }


}
