package JSON;

import junit.framework.Assert;

import org.junit.Test;

import java.io.File;
import java.util.HashSet;

import JSON.Courses.Course;

public class JSONUtilTest {
    String classes;

    @Test
    public void testGetRefreshJSON() throws Exception {
        String classes = JSONUtil.getRefreshJSON(2016);
        Assert.assertNotNull(classes);
    }

    @Test
    public void testParseJSON() throws Exception {
        HashSet<Course> d = JSONUtil.parseJSONCourseList(classes);
        for (Course c: d){
            System.out.println(c.getCourseCode());
        }
    }

    @Test
    public void testSaveJSON() throws Exception{
//        JSONUtil.saveJSONToFile(c,classes);
        Assert.assertTrue(new File(JSONUtil.DEFAULT_JSON_NAME).exists());
    }
    @Test
    public void testOpenSavedJSON() throws Exception {
//        testSaveJSON();
//        String s2 = classes;
//        String s1 = JSONUtil.openSavedJSON(getTestContext());
//        Assert.assertEquals(s1,s2);
//        Assert.assertEquals(JSONUtil.parseJSONCourseList(s1),JSONUtil.parseJSONCourseList(s2));
    }
}