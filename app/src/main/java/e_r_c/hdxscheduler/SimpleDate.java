package e_r_c.hdxscheduler;

import java.util.Comparator;

import lombok.Getter;

/**
 *
 * Container for a parsed date string.
 *
 * @author E-R-C
 */
@Getter
public class SimpleDate {

    private final int month, day, year, hour, minute, second;

    public SimpleDate(String UTCTimeFromJSON){
        // Ex: 2016-12-14T21:00:00
        if(UTCTimeFromJSON.length() != 19){
            throw new IllegalArgumentException("Length was not 19");
        }
        String[] parsed = UTCTimeFromJSON.split(":|-|T");
        year = Integer.parseInt(parsed[0]);
        month = Integer.parseInt(parsed[1]);
        day = Integer.parseInt(parsed[2]);
        hour = Integer.parseInt(parsed[3]);
        minute = Integer.parseInt(parsed[4]);
        second = Integer.parseInt(parsed[5]);
    }

    public static Comparator<SimpleDate> getComparator(){
        return new Comparator<SimpleDate>() {
            @Override
            public int compare(SimpleDate simpleDate, SimpleDate t1) {
                if(simpleDate.getTimeInSec() != t1.getTimeInSec()){
                    return simpleDate.getTimeInSec() - t1.getTimeInSec() > 0 ? 1 : -1;
                } else {
                    return 0;
                }
            }
        };
    }

    public int compare(SimpleDate s2){
        return getComparator().compare(this,s2);
    }

    private long getTimeInSec(){
        return second + minute*60 + hour*60*60;
    }
}
