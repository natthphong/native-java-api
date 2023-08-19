package utils;

import java.time.format.DateTimeFormatter;

public class Constant {
    private Constant(){}

    public static class Date{
        private  Date(){}

        public static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        public  static  DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    }


}
