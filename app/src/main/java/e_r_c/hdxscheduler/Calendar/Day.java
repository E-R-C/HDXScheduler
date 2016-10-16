package e_r_c.hdxscheduler.Calendar;

import android.util.Log;

import java.util.HashMap;
import java.util.HashSet;

import JSON.Courses.Course;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class Day {
    private static final String LOG_TAG = "Day";

    final static String splitValue = "UnIqUeStRiNgDAY";
    final static String titleSplitValue = "TITLEVALUESPLITT";

    @Setter
    @Getter
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

    public void add(Course c){
        if (timeSet.validToInsert(c)){
            timeSet.insert(c);
            courses.add(c);
        } else {
            Log.v(LOG_TAG, "invalid Entry just attempted, Check Week Implementation");
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
            sb.append(c.get("CourseCode"));
            sb.append(splitValue);
        }
        return sb.toString();
    }

}
