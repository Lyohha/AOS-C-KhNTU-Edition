package ua.lyohha.options;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Options {

    private static HashMap<String, String> options;
    private static List<String> optionsName;

    public static void load() {
        options = new HashMap<>();
        optionsName = new ArrayList<>();
        read();
    }

    private static void read() {
        try (BufferedReader reader = new BufferedReader(new FileReader("options.txt"))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.contains("="))
                    continue;
                String[] ls = line.split("=");
                if (ls.length > 2) {
                    StringBuilder builder = new StringBuilder();
                    for (int i = 1; i < ls.length; i++)
                        builder.append(ls[i]);
                    ls[1] = builder.toString();
                }

                if (!options.containsKey(ls[0])) {
                    optionsName.add(ls[0]);
                    options.put(ls[0], ls[1]);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void write() {
        try (FileWriter writer = new FileWriter("options.txt", false)) {
            for (String s : optionsName) {
                writer.write(s + "=" + options.get(s) + "\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String get(@NotNull String name) {
        if (options.containsKey(name))
            return options.get(name);
        return null;
    }

    public static void put(@NotNull String name, @NotNull String option) {
        if (options.containsKey(name))
            options.replace(name, option);
        else {
            options.put(name, option);
            optionsName.add(name);
        }
        write();
    }
}
