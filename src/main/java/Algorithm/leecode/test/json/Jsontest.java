/*
package Algorithm.leecode.test.json;

import designPattern.Iterator.Iterator;


public class Jsontest {
    boolean flag = false;
    public void compareJson(Object o1,Object o2) {
        if(o1 instanceof JSONObject) {
            conpareJson(o1,o2);
        } else if(o1 instanceof JSONArray) {

        } else if(o1 instanceof String) {

        } else {
            compareJson(o1.toString(),o2.toString(),key);
        }
    }

    public void compareJson(JSONObject jsonObj1,JSONObject jsonObj2,String key) {
        Iterator<String> i = json1.keySet().iterator();
        while(i.hasNext()) {
            key = i.next();
            compareJson(jsonObj1.get(key),jsonObj2.get(key),key);
        }
    }

    public void compareJson(JSONArray jsonArray1,JSONArray jsonArray2,String key) {
        if(jsonArray1 != null && jsonArray2 != null) {
            Iterator i1 = jsonArray1.iterator();
            Iterator i2 = jsonArray2.iterator();
            while(i1.hasNext()) {
                compareJson(i1.next(),i2.next(),key);
            }
        } else If(jsonArray1 == null && jsonArray2 == null) {
            System.out.println(" key"+key+"在jsonArray1和jsonArray2中都不存在");
        } else if(jsonArray1 == null) {
            System.out.println("key"+key+"在jsonArrat1中不存在");
        } else if(jsonArray2 != null) {
            System.out.println("key"+key+"在jsonArray2中不存在");
        } else{
            System.out.println("key不一致"+key+"未知原因");
        }
    }

    public void compareJson(String str1,String str2,String key) {
        if(!str1.equals(str2)) {
            System.out.println("不一致,json1:"+str1+"json2:"+str2);
        } else {
            System.out.println("一致,json1:"+str1+"json2:"+str2);
        }
    }


}
*/
