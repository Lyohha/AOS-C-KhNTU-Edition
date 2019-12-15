package ua.lyohha.language;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Language {
    private static HashMap<String, HashMap<String, String>> langMap;
    private static List<String> languagesNames;
    private static String language;
    private static List<LanguageChangeEvent> events;

    public static void load() {
        events = new ArrayList<>();
    }

    public static void addEvent(LanguageChangeEvent event) {
        events.add(event);
    }

    public static void removeEvent(LanguageChangeEvent event) {
        events.remove(event);
    }

    //TODO remove not needed println

    public static void loadLanguageList() {
        List<String> filesList = new ArrayList<>();
        String path = Language.class.getClassLoader().getResource("assets/lang").getPath();
        try {
            path = URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            System.out.println();
        }

        System.out.println(path);

        if (path.startsWith("file")) {
            String[] paths = split(path);
            try {
                JarFile jar = new JarFile(paths[0]);
                Enumeration<JarEntry> entries = jar.entries();
                while (entries.hasMoreElements()) {
                    String name = entries.nextElement().getName();
                    if (name.startsWith(paths[1])) {
                        if (name.length() > paths[1].length()) {
                            String fileName = getFileName(name);
                            System.out.println(fileName);
                            filesList.add(fileName);
                        }
                    }

                }
                loadFiles(filesList);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            File[] files = new File(path).listFiles();
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    filesList.add(files[i].getName());
                    System.out.println(files[i].getName());
                }
            }
            loadFiles(filesList, path);
        }

    }

    private static String[] split(String str) {
        String[] array = new String[2];

        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == '!') {
                array[0] = str.substring(5, i);
                array[1] = str.substring(i + 2) + "/";
                break;
            }
        }


        return array;
    }

    private static String getFileName(String str) {
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == '/')
                return str.substring(i + 1);
        }
        return str;
    }

    private static void loadFiles(List<String> files) {
        languagesNames = new ArrayList<>();
        langMap = new HashMap<>();
        for (String s : files) {
            try {
                InputStream is = Language.class.getResourceAsStream("/assets/lang/" + s);
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(isr);

                readFile(reader);

                reader.close();
                isr.close();
                is.close();

            } catch (NullPointerException | IOException ex) {
                System.out.println("Error read");
                System.out.println(ex.getMessage());
            }

        }
    }

    private static void loadFiles(List<String> files, String path) {
        languagesNames = new ArrayList<>();
        langMap = new HashMap<>();

        languagesNames = new ArrayList<>();
        langMap = new HashMap<>();
        for (String s : files) {
            try {
                FileReader fr = new FileReader(new File(path + '/' + s));
                BufferedReader reader = new BufferedReader(fr);

                readFile(reader);

                reader.close();
                fr.close();

            } catch (NullPointerException | IOException ex) {
                System.out.println("Error read");
                System.out.println(ex.getMessage());
            }

        }
    }

    private static void readFile(BufferedReader reader) throws IOException {
        HashMap<String, String> map = new HashMap<>();
        String line = null;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.length() == 0)
                continue;
            if (!line.contains("="))
                continue;
            String[] sline = line.split("=");
            if (sline.length > 2) {
                StringBuilder builder = new StringBuilder();
                for (int i = 1; i < sline.length; i++)
                    builder.append(sline[i]);
                sline[1] = builder.toString();
            }
            if (!map.containsKey(sline[0])) {
                map.put(sline[0], sline[1]);
            }
        }

        if (map.containsKey("language.name")) {
            String n = map.get("language.name");
            if (!langMap.containsKey(n)) {
                langMap.put(n, map);
                languagesNames.add(n);
                System.out.println("Language: " + n);
            }
        }
    }

    public static String[] getLanguages() {
        String[] items = new String[languagesNames.size()];
        items = languagesNames.toArray(items);
        return items;
    }

    public static void setLanguage(String lang) {
        language = lang;
        for (LanguageChangeEvent event : events) event.onLanguageChange();
    }

    public static String getLocalized(String str) {
        if (langMap.containsKey(language))
            if (langMap.get(language).containsKey(str))
                return langMap.get(language).get(str);
        return str;
    }

    public static String getLanguage() {
        return language;
    }
}
