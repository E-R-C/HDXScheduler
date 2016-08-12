package e_r_c.hdxscheduler.Calendar;

import java.util.HashMap;
import java.util.HashSet;

import JSON.Courses.Course;

public class Week {
    public static final String splitValue = "UniQuES#tRiNgWEEK";
    public static final String splitTitle = "uSTiT#le";
    String title;
    HashMap<String, Day> days;
    public Week(String title){
        this.title = title;
        this.days = new HashMap<>(5);
        loadDefaultDays();
    }
    public Week(){
        title = null;
        days = new HashMap<>(5);
    }
    private void loadDefaultDays(){
        days.put("M",new Day("M"));
        days.put("T",new Day("T"));
        days.put("W",new Day("W"));
        days.put("R",new Day("T"));
        days.put("F",new Day("F"));
    }
    public void addCourse(Course c){
        HashSet<Day> days = getDaysforCourse(c);
        if (validCourse(c)){
            for (Day d: days){
                d.add(c);
            }
        }
    }
    public Day getDay(String s){
        return days.get(s);
    }
    public boolean validCourse(Course c){
        HashSet<Day> validdays = getDaysforCourse(c);
        for (Day d: validdays){
            if (!d.validToAdd(c)){
                return false;
            }
        }
        return true;
    }
    public HashSet<Day> getDaysforCourse(Course c){
        HashSet<Day> result = new HashSet<>(5);
        String[] split = c.getDays().split("\\|");
        for (String s: split){
            result.add(days.get(s));
        }
        return result;
    }
    public void setTitle(String s){
        this.title = s;
    }
    public String getTitle(){
        return title;
    }
    public void addDay(Day d){
        days.put(d.getTitle(),d);
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(title);
        sb.append(splitTitle);
        for (String s: days.keySet()){
            Day d = days.get(s);
            sb.append(d.toString());
            sb.append(splitValue);
        }
        return sb.toString();
    }
    public static Week fromString(String s, HashMap<String,Course> courses){
        Week result = new Week();
        String[] s1 = s.split(splitTitle);
        result.setTitle(s1[0]);
        String[] split = s1[1].split(splitValue);
        for (String in: split){
            if (in.length() > 1){
                Day d = Day.fromString(in,courses);
                result.addDay(d);
            }
        }
        return result;
    }
}
