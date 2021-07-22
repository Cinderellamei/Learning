package designPattern.flyWeight.example;

import java.util.Hashtable;

public class WebsiteFactory {
    private Hashtable flyWeights = new Hashtable();

    public Website getWebsite(String key) {
        if(!flyWeights.contains(key)) {
            flyWeights.put(key,new ConcreteWebsite(key));
        }
        return (Website)flyWeights.get(key);
    }

    public int getWebsiteCount() {
        return flyWeights.size();
    }
}
