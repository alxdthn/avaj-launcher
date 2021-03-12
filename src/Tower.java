import java.util.ArrayList;
import java.util.List;

public class Tower {

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
