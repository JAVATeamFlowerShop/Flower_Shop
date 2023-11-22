package n1exercici1;

import java.util.*;

public class Main {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

    }
    private static int readInt(String message){
        while (true){
            System.out.println(message);
            int i;
            try {
                i = in.nextInt();
                in.nextLine();
                return i;
            }
            catch(InputMismatchException ex) {
                System.out.println("Error de format, introdueix un enter");
                in.nextLine();
            }
        }
    }
    private static double readDouble(String message){
        while (true){
            System.out.println(message);
            double d;
            try {
                d = in.nextDouble();
                in.nextLine();
                return d;
            }
            catch(InputMismatchException ex) {
                System.out.println("Error de format, introdueix un número decimal");
                in.nextLine();
            }
        }
    }
    private static String readString(String message){
        while (true){
            System.out.println(message);
            String s;
            try {
                s = in.nextLine();
                boolean allDigits = true;
                for(int i = 0; i < s.length(); ++i) {
                    char c = s.charAt(i);
                    if(Character.isAlphabetic(c)){
                        allDigits = false;
                    }
                }
                if(allDigits){
                    throw new Exception();
                }
                return s;
            }
            catch(Exception ex) {
                System.out.println("Error de format, introdueix només caràcters alfabètics");
            }
        }
    }
}
