package file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 计算txt文件每一行冒号后数字的平均值
 */
public class TxtTesting {
    public static void main(String [] args) throws IOException{
        String fileName = "/Users/meiaoxue/Desktop/demos.log";
        int result = avgOfNumber(fileName);
        System.out.println(result);
    }

    public static int avgOfNumber(String fileName) throws IOException {
        FileReader fileReader = new FileReader(fileName);

        BufferedReader bf = new BufferedReader(fileReader);
        ArrayList<String> arrayList = new ArrayList<>();

        String str;
        while((str =bf.readLine()) != null) {
            arrayList.add(str);
        }
        bf.close();

        int result = 0;
        for(int i = 0;i<arrayList.size();i++) {
            String s = arrayList.get(i);
            Integer x = new Integer(s.substring(s.indexOf(":")+1));
            int y = x.intValue();
            result += y;
        }
        return result/arrayList.size();
    }
}
