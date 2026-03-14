package me.mrsam7k.splashchanger.config;

import com.google.common.collect.Lists;
import eu.midnightdust.lib.config.MidnightConfig;
import me.mrsam7k.splashchanger.SplashChanger;
import net.minecraft.client.gui.screens.Screen;

import java.util.List;

public class Config extends MidnightConfig {

    @Entry
    public static SplashMode splashMode = SplashMode.RANDOM_SPLASH;

    public enum SplashMode {
        SINGLE_SPLASH, RANDOM_SPLASH, ORIGINAL, ORIGINAL_1_0, ORIGINAL_1_8, NONE
    }

    @Condition(requiredOption = "splashMode", requiredValue = "SINGLE_SPLASH")
    @Entry
    public static String customSplash = "Singular Splash!";

    @Condition(requiredOption = "splashMode", requiredValue = "RANDOM_SPLASH")
    @Entry
    public static List<String> customSplashes = Lists.newArrayList("<white><head:%name></white> %name, that's you... I think",
            "Change this in options!", "<rainbow>Colored <bold>SPLASH", "<gradient:#5e4fa2:#f79459:red>Very cool gradient indeed</gradient>");

    @Comment
    public static Comment spacer1;
    @Comment(centered = true,
            url = "https://docs.papermc.io/adventure/minimessage/format/")
    public static Comment customizationComment;

    @Entry
    public static Colors color = Colors.YELLOW;

    public enum Colors {
        BLACK(), DARK_BLUE(), DARK_GREEN(), DARK_AQUA(), DARK_RED(),
        DARK_PURPLE(), GOLD(), GRAY(), DARK_GRAY(), BLUE(),
        GREEN(), AQUA(), RED(), LIGHT_PURPLE(), YELLOW(), WHITE(),
        CUSTOM(), CUSTOM_GRADIENT();
    }

    @Condition(requiredOption = "color", requiredValue = "CUSTOM")
    @Entry
    public static String customColor = "#ffaa88";

    @Condition(requiredOption = "color", requiredValue = "CUSTOM_GRADIENT")
    @Entry
    public static String gradientColor1 = "#ffaa88";

    @Condition(requiredOption = "color", requiredValue = "CUSTOM_GRADIENT")
    @Entry
    public static String gradientColor2 = "#55ff00";

    @Entry
    public static boolean OBFUSCATED = false;
    @Entry
    public static boolean BOLD = false;
    @Entry
    public static boolean STRIKETHROUGH = false;
    @Entry
    public static boolean UNDERLINE = false;
    @Entry
    public static boolean ITALIC = false;

    @Comment
    public static Comment spacer2;

    @Entry
    public static boolean disableButton = false;

    public static Screen getMidnightScreen(Screen parent) {
        return MidnightConfig.getScreen(parent, SplashChanger.MOD_ID);
    }

}
