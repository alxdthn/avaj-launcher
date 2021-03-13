package java.avaj.launcher;

import java.avaj.launcher.flyable.Flyable;
import java.util.ArrayList;
import java.util.List;

public abstract class Tower {

    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        //  TODO logic
    }

    public void unregister(Flyable flyable) {
        //  TODO logic
    }

    protected void conditionsChanged() {
        //  TODO check logic
        for (Flyable flyable : observers) {
            flyable.updateConditions();
        }
    }
}
