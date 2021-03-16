package avaj.launcher;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class Tower {

    private final List<Flyable> observers = new LinkedList<>();

    public void register(Flyable flyable) {
        AvajLauncher.trackOutput(
                "Tower says: %s registered to weather tower",
                ((Aircraft) flyable).formatSelfData()
        );
        observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        AvajLauncher.trackOutput(
                "Tower says: %s unregistered from weather tower",
                ((Aircraft) flyable).formatSelfData()
        );
        observers.remove(flyable);
    }

    protected void conditionsChanged() {
        List<Flyable> observersCopy = new ArrayList<>(observers);
        for (Flyable flyable : observersCopy) {
            flyable.updateConditions();
        }
    }
}
