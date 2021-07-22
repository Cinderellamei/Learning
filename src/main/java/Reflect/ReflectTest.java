package Reflect;


import java.lang.reflect.*;

public class ReflectTest {
    public static void main(String [] args) {
        /*String name = "test";
        Class c1 = name.getClass();
        System.out.println(c1.getName());*/

        /*String name = "java.lang.String";
        Class c1 = null;
        try {
            c1 = Class.forName(name);
            System.out.println(c1.getName());
        } catch (ClassNotFoundException e) {

        }*/

        /*Class c1 = Boolean.TYPE;
        Class c2 = Byte.TYPE;
        Class c3 = Float.TYPE;
        Class c4 = Double.TYPE;*/

        Test test = new Test();
        Class c4 = test.getClass();
        Constructor constructor;
        //constructors = c4.getDeclaredConstructors();

        /*for(int i = 0;i<constructors.length;i++) {
            System.out.print(Modifier.toString(constructors[i].getModifiers())+"参数");
            Class [] parametertypes = constructors[i].getParameterTypes();
            for(int j = 0;j<parametertypes.length;j++) {
                System.out.print(parametertypes[j]+"");
            }
            System.out.println("");
        }*/

        /*try {
            constructor = c4.getDeclaredConstructor();
            System.out.println(Modifier.toString(constructor.getModifiers())+"");

        } catch(NoSuchMethodException e) {
            e.printStackTrace();
        }*/

       /* Class[] p = {String.class};
        try {
            Method method = c4.getDeclaredMethod("welcome",p);
            method.setAccessible(true);
            Object args1 [] = {"huanyignhuanying"};
            try {
                method.invoke(test,args1);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }*/
        Field field = null;
        try {
            field = c4.getDeclaredField("name");
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
            try {
                e.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try {
            field.set(test,"nnn");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
