package sherwood.demo.game.structures.backpack;

import java.util.LinkedHashMap;
import java.util.Map;

public class Backpack {

    static {
        instance = new Backpack();
    }

    public static Backpack instance () {
        return instance;
    }

    private static Backpack instance;

    private Map<Item, Integer> backpack;
    private int coins;

    private Backpack () {
        backpack = new LinkedHashMap<>();
    }

    public void add (Item item, int quantity) {
        int currentQuantity = backpack.getOrDefault(item, 0);
        backpack.put(item, quantity + currentQuantity);
    }

    /**
     * @return whether or not there was enough of the item to be removed
     */
    public boolean remove (Item item, int quantity) {
        int numLeft = backpack.getOrDefault(item, 0) - quantity;
        if (numLeft < 0)
            return false;
        if (numLeft == 0)
            backpack.remove(item);
        else
            backpack.put(item, numLeft);
        return true;
    }

    public void addCoins (int coins) {
        this.coins += coins;
    }

    public boolean removeCoins(int coins) {
        int numLeft = this.coins - coins;
        if (numLeft < 0)
            return false;
        this.coins -= coins;
        return true;
    }

}
