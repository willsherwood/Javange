package sherwood.iohandlers;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.TreeMap;

public class ConfigHandler {

    public static final String configPath = "res/config/config.txt";
    private static Map<String, String> configMap;
    private static String nl = System.getProperty("line.separator");

    public static void init () {
        configMap = new TreeMap<>();
        reload();
    }

    public static void save (String key, String value) {
        configMap.put(key.trim().toUpperCase(), value.trim().toUpperCase());
    }

    public static void saveAll () {
        StringBuilder out = new StringBuilder();
        configMap.entrySet().forEach(a -> out.append(nl + a.getKey() + ":" + a.getValue()));
        try {
            Files.write(Paths.get(getFile()), out.toString().getBytes("utf-8"), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String load (String key) {
        String value = configMap.get(key);
        if (value != null)
            return value;
        reload();
        value = configMap.get(key);
        if (value != null)
            return value;
        throw new RuntimeException("Config key: " + key + " not found!");
    }

    private static void reload () {
        try {
            for (String line : Files.readAllLines(Paths.get(getFile()))) {
                if (line.isEmpty() || line.charAt(0) == '#')
                    continue;
                int i = line.indexOf(":");
                configMap.put(line.substring(0, i).trim().toUpperCase(), line.substring(i + 1).trim().toUpperCase());
            }
            System.out.println(configMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static URI getFile () {
        File p = new File(configPath);
        if (p.exists())
            return p.toURI();
        try {
            p.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return p.toURI();
    }
}
