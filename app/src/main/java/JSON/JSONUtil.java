package JSON;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import JSON.Courses.CodeTitleDuple;
import JSON.Courses.Course;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * JSON Utility class
 *
 * @author E-R-C
 */

// FIXME: 9/1/16  Difficult to mock. Allow a context to be passed in with the constants?
// FIXME: 9/1/16  Break apart class into one handling network calls and the other handling the straight json.
// FIXME: 9/1/16  Remove System.out.printlns and use logs instead.
// FIXME: 9/1/16  Add the exception when an error is logged. Like Log.e(LOG_TAG, message, e)
@NoArgsConstructor(access= AccessLevel.PRIVATE)
public final class JSONUtil {
    private static final String LOG_TAG = "JSONUtil";
    public static final String DEFAULT_JSON_NAME = "recordsJSON.json";
    public static final String SUBJECTS_JSONS_NAME = "subjectJSON.json";
    public static final String LEARNINGDOMAINS_NAME = "learningDomains.json";
    public static final String LEARNINGDOMAINS_URL = "http://hoike.hendrix.edu/odata/LearningDomains";
    public static final String SUBJECTS_URL = "http://hoike.hendrix.edu/odata/Subjects";

    public static String getJSONFrom(String urlIn){
        // TODO address issue if there is no internet connection
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            StringBuilder sb = new StringBuilder();
            URL url = new URL(urlIn);
            System.out.println(url);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("accept", "application/json");
            int problem = urlConnection.getResponseCode();
            urlConnection.connect();
            if (problem != 200){
                System.out.println("The error code is " + problem);
            }
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null){
                Log.e(LOG_TAG,"Input Stream Null");
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = reader.readLine()) != null){
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0){
                Log.e(LOG_TAG,"Buffer was empty");
                return null;
            }
            return buffer.toString();

        } catch (MalformedURLException e) {
            Log.e(LOG_TAG,"Invalid URL!");
            return null;
        } catch (IOException e) {
            Log.e(LOG_TAG,"Invalid urlConnection!");
            e.printStackTrace();
            return null;
        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG,"Error Closing Stream");
                }
            }
        }
        }
    public static String currentURL(){
        // TODO: Move this to strings.xml.
        return "http://hoike.hendrix.edu/odata/Courses?$filter=Year%20eq%20%272016%27";
    }
    public static HashMap<String,Course> parseJSONCourseList(String json){
        try {
            HashMap<String,Course> result = new HashMap<>();
            JSONObject wholeObject = new JSONObject(json);
            JSONArray classes = wholeObject.getJSONArray("value");
            for (int i = 0; i < classes.length(); i++){
                JSONObject current = classes.getJSONObject(i);
                Course c = new Course(current);
                result.put(c.getCourseCode(),c);
            }
            return result;

        } catch (JSONException e) {
            Log.e(LOG_TAG,"Improper JSON Object");
            return null;
        }

    }
    public static void saveJSONToFile(Context c, String json, String title){
        FileOutputStream outStream = null;
        try {
            outStream = c.openFileOutput(title,c.MODE_PRIVATE);
            outStream.write(json.getBytes());
        } catch (FileNotFoundException e) {
            System.out.println("Failed to create or open " + DEFAULT_JSON_NAME);
            Log.e(LOG_TAG,"Failed to create or open file");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Failed to Write");
            Log.e(LOG_TAG,"Failed to write");
            e.printStackTrace();
        } finally {
            if (outStream != null){
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static String openSavedJSON(Context c, String title){
        FileInputStream filein = null;
        try {
            filein = c.openFileInput(title);
            byte[] b = new byte[filein.available()];
            filein.read(b);
            return new String(b);

        } catch (FileNotFoundException e) {
            System.out.println("Can't find file: + " + DEFAULT_JSON_NAME);
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (filein != null){
                try {
                    filein.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static Date getLastUpdatedJSONTime(){
        File file = new File(DEFAULT_JSON_NAME);
        Date lastModDate = new Date(file.lastModified());
        return lastModDate;
    }
    public static HashMap<String,Course> getRefreshedParsedJSON(Context c, int year){
        final long TIME_DIFFERENCE_DEFAULT = 700000000; // 1 week
        Date currentDate = new Date();
        File path = new File(DEFAULT_JSON_NAME);
        String json = null;

        if (currentDate.getTime() - getLastUpdatedJSONTime().getTime() < TIME_DIFFERENCE_DEFAULT &&
                !path.isFile()) {
            json = getJSONFrom(currentURL());
            saveJSONToFile(c,json,JSONUtil.DEFAULT_JSON_NAME);
        } else{
            json = openSavedJSON(c,JSONUtil.DEFAULT_JSON_NAME);
        }
        return parseJSONCourseList(json);
    }
    public static ArrayList<Course> sortByAscending(boolean ascending, ArrayList<Course> json){
        Comparator<Course> ALPHABETICAL_ORDER1 = new Comparator<Course>() {
            public int compare(Course object1, Course object2) {
                int res = String.CASE_INSENSITIVE_ORDER.compare(object1.getCourseCode(),
                        object2.getCourseCode());
                return res;
            }
        };
        if (ascending){
            Collections.sort(json, ALPHABETICAL_ORDER1);
        } else {
            Collections.sort(json, Collections.reverseOrder(ALPHABETICAL_ORDER1));
        }
        return json;
    }
    public static ArrayList<Course> sortByAscending(boolean ascending, HashSet<Course> courses){
        return sortByAscending(ascending, new ArrayList<Course>(courses));
    }
    public static HashSet<Course> searchForCriteriaIn(String word, String criteria, HashSet<Course> source){
        HashSet<Course> result = new HashSet<>(source.size());
        for(Course c: source){
            if (c.get(criteria).startsWith(word)){
                result.add(c);
            }
        }
        return result;
    }
    public static ArrayList<CodeTitleDuple> getAndSaveLearningDomainsJSON(Context c){
        String domain = getJSONFrom(LEARNINGDOMAINS_URL);
        saveJSONToFile(c,domain,LEARNINGDOMAINS_NAME);
        return parseCodeTitle(domain);
    }
    public static ArrayList<CodeTitleDuple> parseCodeTitle(String JSONString){
        ArrayList<CodeTitleDuple> result = new ArrayList<>();
        result.add(new CodeTitleDuple("All",""));
        try {
            JSONObject whole = new JSONObject(JSONString);
            JSONArray array = whole.getJSONArray("value");
            for(int i = 0; i < array.length(); i++){
                JSONObject current = array.getJSONObject(i);

                result.add(new CodeTitleDuple(current.getString("Code"),
                        current.getString("Title")));
            }
            return result;
        } catch (JSONException e) {
            Log.e(LOG_TAG,"Failed to create JSONOBject");
            e.printStackTrace();
            return null;
        }
    }
    public static ArrayList<CodeTitleDuple> getAndSaveSubjectJSON(Context c){
        String domain = getJSONFrom(JSONUtil.SUBJECTS_URL);
        saveJSONToFile(c,domain,JSONUtil.SUBJECTS_JSONS_NAME);
        return parseCodeTitle(domain);
    }
    public static ArrayList<CodeTitleDuple> openExistingSubjectJSON(Context c){
        return parseCodeTitle(openSavedJSON(c,JSONUtil.SUBJECTS_JSONS_NAME));
    }
    public static ArrayList<CodeTitleDuple> openExistingLearningDomainJSON(Context c){
        return parseCodeTitle(openSavedJSON(c,JSONUtil.LEARNINGDOMAINS_NAME));
    }
    }


