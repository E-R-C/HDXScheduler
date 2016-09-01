package JSON.Courses;

import org.json.JSONObject;

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

    private final String BeginDateTime, Building, CatalogDescription, CourseCode, CourseNumber,
            CrossListed, CrossListedParent, Days, Division, EndDateTime, EndTime, FastSearch,
            InstructorFirstName, InstructorLastName, Instructors, LearningDomain, Period, Room,
            SectionNumber, StartTime, SubjectCode, Term, Title, Year;

    private final int MaxEnrollment, CurrentEnrollment, capacity;

    public Course(JSONObject obj){
        CourseCode = obj.optString("CourseCode");
        BeginDateTime = obj.optString("BeginDateTime");
        Building = obj.optString("Building");
        capacity = obj.optInt("Capacity");
        CatalogDescription = obj.optString("CatalogDescription");
        CourseNumber = obj.optString("CourseNumber");
        CrossListed = obj.optString("CrossListed");
        CrossListedParent = obj.optString("CrossListedParent");
        CurrentEnrollment = obj.optInt("CurrentEnrollment");
        Days = obj.optString("Days");
        Division = obj.optString("Division");
        EndDateTime = obj.optString("EndDateTime");
        EndTime = obj.optString("EndTime");
        FastSearch = obj.optString("FastSearch");
        InstructorFirstName = obj.optString("InstructorFirstName");
        InstructorLastName = obj.optString("InstructorLastName");
        Instructors = obj.optString("Instructors");
        LearningDomain = obj.optString("LearningDomain");
        MaxEnrollment = obj.optInt("MaxEnrollment");
        Period = obj.optString("Period");
        Room = obj.optString("Room");
        SectionNumber = obj.optString("SectionNumber");
        StartTime = obj.optString("StartTime");
        SubjectCode = obj.optString("SubjectCode");
        Term = obj.optString("Term");
        Title = obj.optString("Title");
        Year = obj.optString("Year");
    }
    public String get(String FieldName){
        String out = "";
        switch(FieldName){
            case "BeginDateTime":
                out = BeginDateTime;
                break;
            case "CatalogDescription":
                out = CatalogDescription;
                break;
            case "CourseCode":
                out = CourseCode;
                break;
            case "CourseNumber":
                out = CourseNumber;
                break;
            case "CrossListed":
                out = CrossListed;
                break;
            case "CrossListedParent":
                out = CrossListedParent;
                break;
            case "Days":
                out = Days;
                break;
            case "Division":
                out = Division;
                break;
            case "EndDateTime":
                out = EndDateTime;
                break;
            case "EndTime":
                out = EndTime;
                break;
            case "FastSearch":
                out = FastSearch;
                break;
            case "InstructorFirstName":
                out = InstructorFirstName;
                break;
            case "InstructorLastName":
                out = InstructorLastName;
                break;
            case "Instructors":
                out = Instructors;
                break;
            case "LearningDomain":
                out = LearningDomain;
                break;
            case "Period":
                out = Period;
                break;
            case "Room":
                out = Room;
                break;
            case "SectionNumber":
                out = SectionNumber;
                break;
            case "StartTime":
                out = StartTime;
                break;
            case "SubjectCode":
                out = SubjectCode;
                break;
            case "Term":
                out = Term;
                break;
            case "Title":
                out = Title;
                break;
            case "Year":
                out = Year;
                break;
            case "MaxEnrollment":
                out = String.valueOf(MaxEnrollment);
                break;
            case "CurrentEnrollment":
                out = String.valueOf(CurrentEnrollment);
                break;
            case "capacity":
                out = String.valueOf(capacity);
                break;
        }
        return out;

    }

    public boolean isEqual(Course target){
        return this.getCourseCode().equals(target.getCourseCode());
    }

}
