package shangguigu.reference;

import com.google.common.collect.HashBiMap;
import sun.jvm.hotspot.utilities.BitMap;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class ReferenceDemo {
    public static void main(String [] args) {
        Map<String, SoftReference<BitMap>> imageCache = new HashMap<String,SoftReference<BitMap>>();
    }
}
