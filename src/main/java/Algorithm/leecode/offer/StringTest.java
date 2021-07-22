package Algorithm.leecode.offer;

public class StringTest {
    /**
     * 替换空格
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     * 方法一：使用StringBuffer，如果是字符直接填入，如果是空格填入%20
     */
    public String replaceSpace(String s) {
        StringBuffer str = new StringBuffer();
        for(int i = 0;i<s.length();i++) {
            if(s.charAt(i) == ' ') {
                str.append("%20");
            } else {
                str.append(s.charAt(i));
            }
        }
        return str.toString();
    }

    /**
     * 方法二：使用字符数组，先遍历一遍字符串可计算有多少个空格，然后新建一个字符串数组，数组长度在字符串基础上加上空格数量的2倍，
     * 然后再遍历字符串，遇到字符直接存入字符，遇到空格存入"%20"，用size表示字符串数组的下标记，处理结束后将数组变成字符串输出
     */
    public String replaceSpace1(String s) {
        int count = 0;
        for(int i = 0;i<s.length();i++) {
            if(s.charAt(i) == ' ') {
                count++;
            }
        }

        char [] charArray = new char[s.length()+count*2];
        int size = 0;
        for(int i = 0;i<s.length();i++) {
            if(s.charAt(i) == ' ') {
                charArray[size] = '%';
                charArray[size+1] = '2';
                charArray[size+2] = '3';
                size += 3;
            } else {
                charArray[size++] = s.charAt(i);
            }
        }
        String str = new String(charArray,0,size);
        return str;
    }
}
