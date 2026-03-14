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

    @Entry
    public static String customSplash = "Singular Splash!";
    @Entry
    public static List<String> customSplashes = Lists.newArrayList("%name, that's you... I think",
            "Change this in options!", "&cC&6o&el&ao&br&9e&5d&r &a&lSPLASH");

    @Comment
    public static Comment spacer1;
    @Comment(centered = true)
    public static Comment customizationComment;

    @Entry
    public static Colors color = Colors.YELLOW;

    public enum Colors {
        BLACK("0"), DARK_BLUE("1"), DARK_GREEN("2"), DARK_AQUA("3"), DARK_RED("4"),
        DARK_PURPLE("5"), GOLD("6"), GRAY("7"), DARK_GRAY("8"), BLUE("9"),
        GREEN("a"), AQUA("b"), RED("c"), LIGHT_PURPLE("d"), YELLOW("e"), WHITE("f");

        private final String code;

        Colors(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

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
