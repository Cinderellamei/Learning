package designPattern.flyWeight;

import java.util.Hashtable;

public class FlyWeightFactory {
    private Hashtable flyWeights = new Hashtable();

    public FlyWeightFactory(){
        flyWeights.put("X",new ConcreteFlyWeight());
        flyWeights.put("Y",new ConcreteFlyWeight());
        flyWeights.put("Z",new ConcreteFlyWeight());
    }

    public FlyWeight getFlyWeight(String key) {
        return (FlyWeight)flyWeights.get(key);
    }
}
