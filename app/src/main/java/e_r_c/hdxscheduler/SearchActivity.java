package e_r_c.hdxscheduler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

import JSON.Courses.Course;
import JSON.JSONUtil;

public class SearchActivity extends AppCompatActivity {
    HashMap<String,Course> courses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        courses = JSONUtil.getRefreshedParsedJSON(this,2016);

    }
}
