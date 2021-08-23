package Algorithm.leecode.practices;


import java.util.HashMap;
import java.util.Stack;

public class Testing {

    /**
     * 给定一个字符串，其中的字符只包含三种括号：花括号{ }、中括号[ ]、圆括号( )，即它仅由 “( ) [ ] { }” 这六个字符组成。
     * 设计算法，判断该字符串是否有效，即字符串中括号是否匹配。括号匹配要求括号必须以正确的顺序配对，如 “{ [ ] ( ) }” 或 “[ ( { } [ ] ) ]” 等为正确的格式，
     * 而 “[ ( ] )” 或 “{ [ ( ) }” 或 “( { } ] )” 均为不正确的格式。
     */
    public static boolean isValid(String str) {
        if(str == null) {
            return true;
        }
        HashMap<Character,Character> map = new HashMap<>();
        map.put('}','{');
        map.put(']','[');
        map.put(')','(');
        Stack<Character> stack = new Stack<>();
        for(int i = 0;i<str.length();i++) {
            char ch = str.charAt(i);
            if(map.containsKey(ch)) {
                if(stack.isEmpty() || !map.get(ch).equals(stack.peek())) {
                    return false;
                } else {
                    stack.pop();
                }
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String [] args) {
        String str = "[]()}";
        System.out.println(isValid(str));
    }

    /**
     * 有一个订单的表order_info   列：id，order_id,create_time,exp_time,status,
     * 1、查出order_id 是2，4，5，6，7，10的订单信息
     * 2、把订单状态=4的数据有多少条统计出来
     * 3、更新status=5的所有订单创建时间，create_time=1900-00-00 exp_time=2021-07-07
     * 4、查询id最大的那条数据的所有字段信息
     */

    public void test() {
        String sql1 ="select * from order_info where order_id in(2,4,5,6,7,10)";
        String sql2 ="select count(order_id) from order_info where status=4";
        String sql3 = "update order_info set create_time=1900-00-00,exp_time=2021-07-07 where status=5";
        String sql4="select * from order_info order by id desc limit 1";
    }

}
