package handlekurv;

import java.util.ArrayList;
import java.util.List;

public class Vare {
	private static List<String> items = new ArrayList<>();

	public Vare() {
		items = new ArrayList<>();
	}
	
	public static void addItem(String item) {
		items.add(item);
	}

	public static synchronized void deleteItem(String item) {
		items.remove(item);
	}

	public static List<String> getItems() {
		return items;
	}

	public static synchronized boolean finnes(String varenavn) {
		return items.contains(varenavn);
	}



	
}