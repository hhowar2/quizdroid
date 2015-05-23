package hhowar2.washington.edu.quizdroid;

import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

//Main activity that implements Quiz, Answer and Topic fragments
public class MultiuseActivity extends ActionBarActivity implements TopicOverviewFragment.OnFragmentInteractionListener, QuizFragment.OnFragmentInteractionListener,
AnswerFragment.OnFragmentInteractionListener {

    /*static Topic Math;
    static Topic Physics;
    static Topic MarvelSuperHeroes;
    static Topic ScienceFiction;

    List<Question> questions;*/
    String topic;
    String description;

    //QuizApp.Math;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiuse);
        Bundle extras = getIntent().getExtras();
        topic = extras.getString("Topic");
        QuizApp data = (QuizApp)getApplication();

        TopicOverviewFragment topicFragment = new TopicOverviewFragment();
        topicFragment.setArguments(extras);
        if(savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                .add(android.R.id.content, topicFragment)
                .commit();
        }
    }

    public void onFragmentInteraction(Uri uri) {
        Toast toast = Toast.makeText(this, "wheeee", Toast.LENGTH_SHORT);
        toast.show();

    }

    public void startQuiz() {
        Bundle bundle = new Bundle();
        bundle.putString("Topic", topic);
        bundle.putInt("Number", 0);
        FragmentManager fragmentManager = getFragmentManager();
        QuizFragment quiz = new QuizFragment();
        quiz.setArguments(bundle);
        fragmentManager.beginTransaction()
            .replace(android.R.id.content, quiz)
            .commit();


    }

    public void answer(String topic, int correct, int incorrect, int number, String answer, String userAnswer, int questionSize) {
        Bundle bundle = new Bundle();
        bundle.putString("Topic", topic);
        bundle.putInt("Number", number);
        bundle.putInt("Correct", correct);
        bundle.putInt("Incorrect", incorrect);
        bundle.putString("Answer", answer);
        bundle.putString("UserAnswer", userAnswer);
        bundle.putInt("QuestionSize", questionSize);
        FragmentManager fragmentManager = getFragmentManager();
        AnswerFragment answerFrag = new AnswerFragment();
        answerFrag.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(android.R.id.content, answerFrag)
                .commit();
    }
    public void nextQuestion(String topic, int number,int correct, int incorrect) {
        Bundle bundle = new Bundle();
        bundle.putString("Topic", topic);
        bundle.putInt("Number", number);
        bundle.putInt("Correct", correct);
        bundle.putInt("Incorrect", incorrect);
        FragmentManager fragmentManager = getFragmentManager();
        QuizFragment quiz = new QuizFragment();
        quiz.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(android.R.id.content, quiz)
                .commit();
    }

    public void topicSelection() {
        Intent next = new Intent(MultiuseActivity.this, TopicSelection.class);
        startActivity(next);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_multiuse, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        QuizApp app = (QuizApp)getApplication();
        AlarmManager alarm = app.alarm;
        PendingIntent pintent = app.pintent;
        alarm.cancel(pintent);
    }
}
