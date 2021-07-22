package designPattern.PublishSubscribe;

import java.util.ArrayList;
import java.util.List;

public class Secretary {
    private List<StockObserver> observers = new ArrayList<>();

    public String action;

    public void attach(StockObserver observer) {
        observers.add(observer);
    }

    public void notifys() {
        for(StockObserver o:observers) {
            //o.update();
        }
    }

    public String secretaryAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
