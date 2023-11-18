package data.graphics;

import java.util.ArrayList;

public class EventManager {
    
    private static ArrayList<Event> queue = new ArrayList<Event>(); // Creates a queue ArrayList

    public static void addEvent(Event e) {
        queue.add(e);
    }

    public static void handleEvents() {

        // Loops until the queue is empty and activates the event for each one
        while (!queue.isEmpty()) {
            queue.get(0).onEvent();
            queue.remove(0);
        }
    }

}
