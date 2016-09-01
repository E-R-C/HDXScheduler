package e_r_c.hdxscheduler;

import android.app.Application;
import android.content.Context;
import android.test.ApplicationTestCase;

import junit.framework.Assert;

import java.io.File;
import java.util.HashMap;

import JSON.Courses.Course;
import JSON.JSONUtil;

public class ApplicationTest extends ApplicationTestCase<Application> {
    Context context = getContext();
    public ApplicationTest() {
        super(Application.class);
    }



    public void testJSONBattery() throws Exception{
        context = getContext();
        Assert.assertNotNull(context);
        String s = JSONUtil.getJSONFrom(JSONUtil.currentURL());
        Assert.assertNotNull(s);
        JSONUtil.saveJSONToFile(context,s,JSONUtil.DEFAULT_JSON_NAME);
        Assert.assertTrue(new File(context.getFilesDir(),JSONUtil.DEFAULT_JSON_NAME).exists());
        String s2 = JSONUtil.openSavedJSON(context,JSONUtil.DEFAULT_JSON_NAME);
        HashMap<String,Course> h1 = JSONUtil.parseJSONCourseList(s);
        HashMap<String,Course> h2 = JSONUtil.parseJSONCourseList(s2);
        Assert.assertNotNull(h1);
        Assert.assertNotNull(h2);
    }

}