package sherwood.iohandlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ConfigHandler {

	public static final String configPath = "src/res/config/config.txt";
	private static Map<String, String> configMap;
	private static FileWriter fw;
	private static String nl = System.getProperty("line.separator");

	public static void init() {
		configMap = new TreeMap<>();
		reload();
	}

	public static void save(String key, String value) {
		try {
			if (fw == null)
				fw = new FileWriter(new File(configPath), true);
			fw.write(nl + key + ":" + value);
			fw.flush();
			configMap.put(key.trim().toUpperCase(), value.trim().toUpperCase());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String load(String key) {
		String value = configMap.get(key);
		if (value != null)
			return value;
		reload();
		value = configMap.get(key);
		if (value != null)
			return value;
		throw new RuntimeException("Config key: " + key + " not found!");
	}

	private static void reload() {
		try {
			Scanner s = new Scanner(new File(configPath));
			while (s.hasNextLine()) {
				String t = s.nextLine();
				if (t.charAt(0) == '#')
					continue;
				int i = t.indexOf(":");
				configMap.put(t.substring(0, i).trim().toUpperCase(), t
						.substring(i + 1).trim().toUpperCase());
			}
			System.out.println(configMap);
		} catch (FileNotFoundException e) {
			System.out.println("file does not exist");
			e.printStackTrace();
		}
	}
}
