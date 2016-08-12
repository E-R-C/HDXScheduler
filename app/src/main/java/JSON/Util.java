package JSON;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static Date parseDate(String dateTime){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            return format.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
