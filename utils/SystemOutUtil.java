package utils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class SystemOutUtil {


    public static void printObjects(Object... objects) {
        for (Object obj : objects) {
            if (obj == null) {
                System.out.println("");
            } else if (obj.getClass().isArray()) {
                printArray(obj);
            } else if (obj instanceof List) {
                printList((List<?>) obj);
            } else if (obj instanceof Map) {
                printMap((Map<?, ?>) obj);
            } else {
                printFields(obj);
            }
        }
        System.out.println("");
    }

    private static void printArray(Object array) {
        int length = java.lang.reflect.Array.getLength(array);
        System.out.print("[");
        for (int i = 0; i < length; i++) {
            System.out.print(java.lang.reflect.Array.get(array, i));
            if (i!=length-1){
                System.out.print(",");
            }
        }
        System.out.print("]");
    }
    private static void printMap(Map<?, ?> map) {
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            System.out.print(entry.getKey() + ": " + entry.getValue() + " | ");
        }
        System.out.println("\n");
    }

    private static void printList(List<?> list) {
        System.out.print("[");
        int i  = 0 ;
        for (Object obj : list) {
            if (obj instanceof String
                    || obj instanceof Number
                    || obj instanceof LocalDate
                    || obj instanceof LocalDateTime
                    ){
                System.out.print(obj);
            }else{
                System.out.print("{");
                printFields(obj);
                System.out.print("}");
                if (i!=list.size()-1){
                    System.out.println(",");
                }
            }
            i++;
        }
        System.out.println("]");
    }

    private static void printFields(Object obj) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        int i  = 0 ;
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object fieldValue = field.get(obj);
                System.out.print(field.getName() + ":" + fieldValue);
                if (i != fields.length-1){
                    System.out.print(",");
                }
            } catch (IllegalAccessException e) {
                System.out.println("Error accessing field: " + e.getMessage());
            }
            i++;
        }
    }

}
