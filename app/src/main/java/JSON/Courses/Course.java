package JSON.Courses;

import org.json.JSONObject;

public class Course{
    private String BeginDateTime, Building, CatalogDescription, CourseCode, CourseNumber,
            CrossListed, CrossListedParent, Days, Division, EndDateTime, EndTime, FastSearch,
            InstructorFirstName, InstructorLastName, Instructors, LearningDomain, Period, Room,
            SectionNumber, StartTime, SubjectCode, Term, Title, Year;
    private int MaxEnrollment, CurrentEnrollment, capacity;

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
    public String getBeginDateTime() {
        return BeginDateTime;
    }

    public void setBeginDateTime(String beginDateTime) {
        BeginDateTime = beginDateTime;
    }

    public String getBuilding() {
        return Building;
    }

    public void setBuilding(String building) {
        Building = building;
    }

    public String getCatalogDescription() {
        return CatalogDescription;
    }

    public void setCatalogDescription(String catalogDescription) {
        CatalogDescription = catalogDescription;
    }

    public String getCourseCode() {
        return CourseCode;
    }

    public void setCourseCode(String courseCode) {
        CourseCode = courseCode;
    }

    public String getCourseNumber() {
        return CourseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        CourseNumber = courseNumber;
    }

    public String getCrossListed() {
        return CrossListed;
    }

    public void setCrossListed(String crossListed) {
        CrossListed = crossListed;
    }

    public String getCrossListedParent() {
        return CrossListedParent;
    }

    public void setCrossListedParent(String crossListedParent) {
        CrossListedParent = crossListedParent;
    }

    public String getDays() {
        return Days;
    }

    public void setDays(String days) {
        Days = days;
    }

    public String getDivision() {
        return Division;
    }

    public void setDivision(String division) {
        Division = division;
    }

    public String getEndDateTime() {
        return EndDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        EndDateTime = endDateTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getFastSearch() {
        return FastSearch;
    }

    public void setFastSearch(String fastSearch) {
        FastSearch = fastSearch;
    }

    public String getInstructorFirstName() {
        return InstructorFirstName;
    }

    public void setInstructorFirstName(String instructorFirstName) {
        InstructorFirstName = instructorFirstName;
    }

    public String getInstructorLastName() {
        return InstructorLastName;
    }

    public void setInstructorLastName(String instructorLastName) {
        InstructorLastName = instructorLastName;
    }

    public String getInstructors() {
        return Instructors;
    }

    public void setInstructors(String instructors) {
        Instructors = instructors;
    }

    public String getLearningDomain() {
        return LearningDomain;
    }

    public void setLearningDomain(String learningDomain) {
        LearningDomain = learningDomain;
    }

    public String getPeriod() {
        return Period;
    }

    public void setPeriod(String period) {
        Period = period;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public String getSectionNumber() {
        return SectionNumber;
    }

    public void setSectionNumber(String sectionNumber) {
        SectionNumber = sectionNumber;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getSubjectCode() {
        return SubjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        SubjectCode = subjectCode;
    }

    public String getTerm() {
        return Term;
    }

    public void setTerm(String term) {
        Term = term;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public int getMaxEnrollment() {
        return MaxEnrollment;
    }

    public void setMaxEnrollment(int maxEnrollment) {
        MaxEnrollment = maxEnrollment;
    }

    public int getCurrentEnrollment() {
        return CurrentEnrollment;
    }

    public void setCurrentEnrollment(int currentEnrollment) {
        CurrentEnrollment = currentEnrollment;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isEqual(Course target){
        return this.getCourseCode().equals(target.getCourseCode());
    }

}
