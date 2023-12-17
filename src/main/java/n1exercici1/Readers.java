package n1exercici1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Readers {
    private static Scanner in = new Scanner(System.in);
    public static int readInt(String message){
        while (true){
            System.out.println(message);
            int i;
            try {
                i = in.nextInt();
                in.nextLine();
                return i;
            }
            catch(InputMismatchException ex) {
                System.out.println("Format error.");
                in.nextLine();
            }
        }
    }

    public static String readString(String message){
        while (true){
            System.out.println(message);
            try {
                String s = in.nextLine();
                if (s.chars().allMatch(Character::isDigit)){
                    throw new Exception();
                }
                return s;
            }
            catch(Exception ex) {
                System.err.println("Format error. Cannot be a digit.");
            }
        }
    }
    public static boolean readYesNo(String message){
        String s;
        while (true){
            try {
                s = readString(message);
                if (s.equalsIgnoreCase("y")){
                    return true;
                } else if (s.equalsIgnoreCase("n")){
                    return false;
                } else {
                    throw new Exception();
                }
            } catch (Exception e){
                System.err.println("Please answer with y/n");
            }
        }
    }

    public static float readFloat(String message){
        while (true){
            System.out.println(message);
            float d;
            try {
                d = in.nextFloat();
                in.nextLine();
                return d;
            }
            catch(InputMismatchException ex) {
                System.out.println("Format error. Please introduce decimal number.");
                in.nextLine();
            }
        }
    }
}
