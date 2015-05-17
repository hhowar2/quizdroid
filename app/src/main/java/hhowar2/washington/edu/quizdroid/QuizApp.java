package hhowar2.washington.edu.quizdroid;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Button;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONArray;

/**
 * Created by Howard on 5/7/2015.
 */
public class QuizApp extends android.app.Application implements TopicRepository {
    Topic Math;
    Topic Physics;
    Topic MarvelSuperHeroes;
    Topic ScienceFiction;
    List<Topic> TopicList = new ArrayList<Topic>();
    AlarmManager alarm;
    PendingIntent pintent;
    Intent intent;

    private static QuizApp instance = null;
    public QuizApp() {
        if(instance == null) {
            instance = this;
        } else {
            throw new RuntimeException("Cannot create more than one QuizApp");
        }
    }
    @Override
    public void onCreate() {
        String json = null;
        super.onCreate();
        Log.i("Application", "application object called");
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String url = SP.getString("URL", "http://tednewardsandbox.site44.com/questions.json");
        int minutes = Integer.parseInt(SP.getString("Minute", "10"));
        Log.i("Application", url + " " + minutes);
        alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        intent = new Intent();
        intent.setAction("com.tutorialspoint.CUSTOM_INTENT");

        intent.putExtra("message", url);
        pintent = PendingIntent.getBroadcast(QuizApp.this, 0, intent, 0);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 10000 * minutes, pintent);
        //alarm.cancel(pintent);


        try {
            String result = null;
            InputStream inputStream = getAssets().open("questions.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            result = sb.toString();
            Log.i("Application", result);
            JSONArray readerJSON = new JSONArray(result);

            //String title = readerJSON.getString("title");
            Log.i("Application", readerJSON.length() + "something");
            //In the object
            for(int i = 0; i < readerJSON.length(); i++) {
                JSONObject topic = readerJSON.getJSONObject(i);
                String title = topic.getString("title");
                String description = topic.getString("desc");
                Log.i("Application", title + " " + description);
                JSONArray questions = topic.getJSONArray("questions");
                List<Question> questionList = new ArrayList<Question>();
                //Iterating through questions
                for(int j = 0; j < questions.length(); j++) {
                    JSONObject question = questions.getJSONObject(j);
                    String text = question.getString("text");
                    int position = Integer.parseInt(question.getString("answer"));
                    JSONArray answers = question.getJSONArray("answers");
                    String[] answerList = new String[answers.length()];
                    //iterating through answers
                    for(int k = 0; k < answers.length(); k++) {
                        answerList[k] = answers.getString(k);
                        Log.i("Application", answers.getString(k) + " " + k);
                    }
                    Question q = new Question(text, answerList, (position - 1));
                    questionList.add(q);
                    Log.i("Application", text + " " + position + " " + answerList.toString());
                }
                //if(title.equals(""))
                TopicList.add(new Topic(title, questionList, description, ""));

            }

        } catch(IOException e) {
            e.printStackTrace();
        } catch(JSONException e) {
            e.printStackTrace();
        }
        //loadData();
    }

    public void loadData() {
        Data data = new Data();
        this.Math = data.Math;
        this.MarvelSuperHeroes = data.MarvelSuperHeroes;
        this.Physics = data.Physics;
        this.ScienceFiction = data.ScienceFiction;
        this.TopicList = data.TopicsList;
    }



}
