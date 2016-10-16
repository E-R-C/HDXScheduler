package JSON.Courses;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * Container for all data available for a course.
 *
 * @author E-R-C
 */

@Getter
@Setter
public class Course{

    private HashMap<String, String> data;
    /* Variables str Stored: BeginDateTime, Building, CatalogDescription, CourseCode, CourseNumber,
            CrossListed, CrossListedParent, Days, Division, EndDateTime, EndTime, FastSearch,
            InstructorFirstName, InstructorLastName, Instructors, LearningDomain, Period, Room,
            SectionNumber, StartTime, SubjectCode, Term, Title, Year */

    // Integers Stored MaxEnrollment, CurrentEnrollment, capacity;

    public Course(JSONObject obj){
        data = new HashMap<>();
        Iterator keys = obj.keys();
        while(keys.hasNext()){
            String key = keys.next().toString();
            Object item = obj.opt(key);
            String value = "";
            if (item instanceof Integer){
                value = Integer.toString((Integer) item);
            } else {
                value = item.toString();
            }
            data.put(key,value);
        }

    }
    public String get(String FieldName)throws IllegalArgumentException{
        if (data.get(FieldName) == null){
            throw new IllegalArgumentException();
        }
        return data.get(FieldName);
    }

    @Override
    public int hashCode() {
        return data.get("SubjectCode").hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj.hashCode() == data.get("SubjectCode").hashCode();
    }
}
