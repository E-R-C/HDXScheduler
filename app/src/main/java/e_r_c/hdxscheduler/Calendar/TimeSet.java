package e_r_c.hdxscheduler.Calendar;

import java.util.Comparator;
import java.util.TreeSet;

import JSON.Courses.Course;
import e_r_c.hdxscheduler.SimpleDate;

public class TimeSet {

    TreeSet<TimeDuple> times;
    public TimeSet(){
        Comparator<TimeDuple> comparator = new Comparator<TimeDuple>() {
            @Override
            public int compare(TimeDuple timeDuple, TimeDuple t1) {
                return SimpleDate.getComparator().compare(timeDuple.getDate(),t1.getDate());
            }
        };
        times = new TreeSet<TimeDuple>(comparator);
    }
    public void insert(Course c){
        SimpleDate tempStart  = new SimpleDate(c.getStartTime());
        SimpleDate tempEnd = new SimpleDate(c.getEndTime());
        TimeDuple start = new TimeDuple(tempStart,true);
        TimeDuple end = new TimeDuple(tempEnd,false);
        times.add(start);
        times.add(end);
    }
    public void remove(Course c){
        TimeDuple start = new TimeDuple(new SimpleDate(c.getStartTime()), true);
        TimeDuple end = new TimeDuple(new SimpleDate(c.getEndTime()),false);
        for(TimeDuple t: times){
            if (start.isEqual(t) || end.isEqual(t)){
                times.remove(t);
            }
        }
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
    private class TimeDuple{
        SimpleDate d;
        boolean start;

        public TimeDuple(SimpleDate d, boolean start){
            this.d = d;
            this.start = start;
        }
        public boolean isStart(){
            return start;
        }
        public SimpleDate getDate(){
            return d;
        }
        public boolean isEqual(TimeDuple t){
            SimpleDate t2 = t.getDate();
            return (d.getYear() == t2.getYear() && d.getMonth() == t2.getMonth() &&
                    d.getDay() == t2.getDay() && d.getHour() == t2.getHour() &&
                    d.getSecond() == d.getSecond());
        }

    }
}
