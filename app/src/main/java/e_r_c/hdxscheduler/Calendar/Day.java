package e_r_c.hdxscheduler.Calendar;

import java.util.HashMap;
import java.util.HashSet;

import JSON.Courses.Course;

public class Day {
    final static String splitValue = "UnIqUeStRiNgDAY";
    final static String titleSplitValue = "TITLEVALUESPLITT";
    String Title;
    // Remember that normally there won't be more tha 4 courses per day.
    HashSet<Course> courses;
    TimeSet timeSet;

    public Day(){
        Title = "Has not been set";
        courses = new HashSet<>();
        timeSet = new TimeSet();
    }
    public Day(String title){
        Title = title;
        courses = new HashSet<>();
        timeSet = new TimeSet();
    }


    public void setTitle(String s){
        Title = s;
    }
    public String getTitle(){
        return Title;
    }

    public void add(Course c){
        if (timeSet.validToInsert(c)){
            timeSet.insert(c);
            courses.add(c);
        } else {
            System.out.println("invalid Entry just attempted, Check Week Implementation");
        }
    }
    public boolean contains(Course c){
        for(Course in: courses){
            if (in.isEqual(c)){
                return true;
            }
        }
        return false;
    }
    public void remove(Course c){
        timeSet.remove(c);
        for (Course in: courses){
            if (in.isEqual(c)){
                courses.remove(in);
                break;
            }
        }
    }
    public boolean validToAdd(Course c){
        return timeSet.validToInsert(c);
    }
    public static Day fromString(String s, HashMap<String,Course> courses){
        Day result = new Day();
        String[] split1 = s.split(titleSplitValue);
        result.setTitle(split1[0]);
        String[] splitStrings = split1[1].split(splitValue);
        for (String in: splitStrings){
            if (in.length() > 1){
                result.add(courses.get(in));
            }
        }
        return result;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(Title);
        sb.append(titleSplitValue);
        for (Course c: courses){
            sb.append(c.getCourseCode());
            sb.append(splitValue);
        }
        return sb.toString();
    }
    public HashSet<Course> getCourses(){
        return courses;
    }
}
