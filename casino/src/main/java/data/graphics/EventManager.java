package data.graphics;

import java.util.ArrayList;

public class EventManager {
    
    private static ArrayList<Event> queue = new ArrayList<Event>();

    public static void addEvent(Event e) {
        queue.add(e);
    }

    public static void handleEvents() {
        while (!queue.isEmpty()) {
            queue.get(0).onEvent();
            queue.remove(0);
        }
    }

}
