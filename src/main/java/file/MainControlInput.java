package file;

import java.util.Scanner;

public class MainControlInput {
    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            String s = sc.nextLine();
            System.out.println(s);
        }
        sc.close();
    }
}
