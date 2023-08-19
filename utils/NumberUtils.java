package utils;

public class NumberUtils {


    public static boolean isEmpty(Object number){
        return number==null;
    }

    public static boolean isEmpty(int number){
        return  number ==0;
    }
    public static boolean isEmpty(double number){
        return  number ==0;
    }
    public static boolean isEmpty(long number){
        return  number ==0;
    }

    public static boolean isEmpty(String number){
        return number==null || Integer.parseInt(number) ==0;
    }

}
