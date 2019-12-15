package ua.lyohha.themes;

import ua.lyohha.options.Options;

import java.util.ArrayList;
import java.util.List;

public class Themes {

    private static Theme theme;
    private static List<ThemeChangeEvent> events = new ArrayList<>();

    public static String getUnlocalizedName(Theme theme) {
        switch (theme) {
            case DARK:
                return "themes.theme_dark.name";
            case LIGHT:
                return "themes.theme_light.name";
            default:
                return "themes.theme_none.name";
        }
    }

    public static void setTheme(String name) {

        if (name == null) {
            theme = Theme.DARK;
            Options.put("theme", getName());
            return;
        }

        switch (name) {
            case "LIGHT":
                theme = Theme.LIGHT;
                break;
            case "DARK":
            default:
                theme = Theme.DARK;
                break;
        }
    }

    private static String getName() {
        switch (theme) {
            case LIGHT:
                return "LIGHT";
            case DARK:
            default:
                return "DARK";
        }
    }

    public static void addEvent(ThemeChangeEvent event) {
        events.add(event);
    }

    public static void removeEvent(ThemeChangeEvent event) {
        events.remove(event);
    }

    public static Theme getTheme() {
        return theme;
    }

    public static void setTheme(Theme th) {
        theme = th;
        for (int i = 0; i < events.size(); i++)
            events.get(i).onThemeChange();
        Options.put("theme", getName());
    }

    public enum Theme {
        DARK,
        LIGHT
    }

    public static Theme[] getThemes() {
        return Theme.values();
    }
}
