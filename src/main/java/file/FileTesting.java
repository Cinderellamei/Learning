package file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileTesting {
    public static void main(String [] args) throws IOException {
        String fileName =  "/Users/meiaoxue/Desktop/demo.log";
        String result = test(fileName);
        System.out.println(result);
    }

    //返回调用次数最多的接口名
    public static String test(String fileName) throws IOException {
        FileReader fileReader = new FileReader(fileName);

        BufferedReader in = new BufferedReader(fileReader);
        ArrayList<String> arrayList = new ArrayList<>();
        HashMap<String,Integer> map = new HashMap<>();
        String str;
        while((str=in.readLine())!=null) {
            arrayList.add(str);
        }
        in.close();
        fileReader.close();
        for(int i = 0;i<arrayList.size();i++) {
            String logStr = arrayList.get(i);
            String interfaceName = logStr.substring(logStr.indexOf("@")+1);
            if(map.containsKey(interfaceName)) {
                map.put(interfaceName,map.get(interfaceName)+1);
            } else {
                map.put(interfaceName,1);
            }
        }
        int max = 0;
        String maxName = " ";
        for(String strs:map.keySet()) {
            if(map.get(strs)>max) {
                max = map.get(strs);
                maxName=strs;
            }
        }
        return maxName;
    }
}
