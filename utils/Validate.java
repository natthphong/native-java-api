package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Validate {


    public static boolean validateNotNullNotBlank(Object ... args){
        for (var a  : args){
            if (a==null){
                return false;
            }
           else if (a instanceof String){
                   if (StringUtils.isBlank((String)a)){
                       return false;
                   };
            }
        }
        return true;
    }
}
