package e_r_c.hdxscheduler.Calendar;

import java.util.Comparator;
import java.util.TreeSet;

import JSON.Courses.Course;
import e_r_c.hdxscheduler.SimpleDate;
import lombok.Value;

public class TimeSet {

    TreeSet<TimeDuple> times;

    public TimeSet(){
        Comparator<TimeDuple> comparator = new Comparator<TimeDuple>() {
            @Override
            public int compare(TimeDuple timeDuple, TimeDuple t1) {
                return SimpleDate.getComparator().compare(timeDuple.getDate(),t1.getDate());
            }
        };
        times = new TreeSet<>(comparator);
    }
    public void insert(Course c){
        SimpleDate tempStart  = new SimpleDate(c.getStartTime());
        SimpleDate tempEnd = new SimpleDate(c.getEndTime());
        TimeDuple start = new TimeDuple(tempStart,true);
        TimeDuple end = new TimeDuple(tempEnd,false);
        times.add(start);
        times.add(end);
    }

    public boolean validToInsert(Course c){
        SimpleDate tempStart  = new SimpleDate(c.getStartTime());
        SimpleDate tempEnd = new SimpleDate(c.getEndTime());
        TimeDuple start = new TimeDuple(tempStart,true);
        TimeDuple end = new TimeDuple(tempEnd,false);
        times.add(start);
        times.add(end);
        byte level = 0;
        for(TimeDuple t: times){
            if(t.isStart()){
                level++;
            } else{
                level--;
            }
            if (level > 1 || level < -1){
                return false;
            }
        }
        times.remove(start);
        times.remove(end);
        return true;
    }

    @Value
    private class TimeDuple {
        final SimpleDate date;
        final boolean start;
    }
}
