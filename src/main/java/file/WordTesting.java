package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class WordTesting {
    public static <type> void main (String[] args) throws FileNotFoundException {
        File file = new File("E:\\word.txt");                  //读取文件
        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }
        Scanner scanner = new Scanner(file);
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        //System.out.println("文章-----------------------------------");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            //System.out.println(line);
            String[] lineWords = line.split("\\W+");          //导入文章，但是被注释掉了
            Set<String> wordSet = hashMap.keySet();
            for (int i = 0; i < lineWords.length; i++) {
                if (wordSet.contains(lineWords[i])) {
                    Integer number = hashMap.get(lineWords[i]);
                    number++;
                    hashMap.put(lineWords[i], number);
                } else {
                    hashMap.put(lineWords[i], 1);
                }
            }
        }
        //System.out.println("统计单词：------------------------------");
        Iterator<String> iterator = hashMap.keySet().iterator();
        int max = 0;
        String maxword = null;
        while (iterator.hasNext()) {
            String word = iterator.next();
            //System.out.printf("单词:%-12s 出现次数:%d\n",word,hashMap.get(word));
            if (hashMap.get(word) > max) {                          //比较出现次数最多的单词
                max = hashMap.get(word);
                maxword = word;
            }
        }
        System.out.println("出现最多的是" + maxword);
        System.out.println("最多出现了" + max + "次");
    }
}
