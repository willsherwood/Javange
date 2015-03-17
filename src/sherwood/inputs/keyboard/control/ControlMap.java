package sherwood.inputs.keyboard.control;

import sherwood.iohandlers.ConfigHandler;

import java.util.Map;
import java.util.TreeMap;

public class ControlMap {

    protected static Map<Integer, Control> keyMap;

    static {
        keyMap = new TreeMap<>();
        for (Control c : Control.values())
            keyMap.put(Integer.valueOf(ConfigHandler.load(c.toString())), c);
    }

    public static Control getControl (int keyCode) {
        return keyMap.get(keyCode);
    }
}
