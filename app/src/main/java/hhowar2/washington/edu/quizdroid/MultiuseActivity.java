package hhowar2.washington.edu.quizdroid;

import android.app.FragmentManager;
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


public class MultiuseActivity extends ActionBarActivity implements TopicOverviewFragment.OnFragmentInteractionListener, QuizFragment.OnFragmentInteractionListener,
AnswerFragment.OnFragmentInteractionListener {

    static Topic Math;
    static Topic Physics;
    static Topic MarvelSuperHeroes;
    static Topic ScienceFiction;

    List<Question> questions;
    String topic;
    String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiuse);
        Bundle extras = getIntent().getExtras();
        topic = extras.getString("Topic");
        questions = new ArrayList<Question>();
        //if(topic.equals("Math")) {
            questions.add(new Question("1 + 1", "2"));
            questions.add(new Question("2 + 1", "3"));
            questions.add(new Question("3 + 1", "4"));
            questions.add(new Question("4 + 1", "5"));
            description = "The study of relationships and numbers";
            Math = new Topic("Math", questions, description);
        //} else if(topic.equals("Physics")) {
        questions = new ArrayList<Question>();

        questions.add(new Question("Gravity", "-9.8m/s^2"));
            questions.add(new Question("F=", "ma"));
            questions.add(new Question("e=", "mc^2"));
            questions.add(new Question("p=", "MV"));
            description = "Explaining real-world laws through math";
            Physics = new Topic("Physics", questions, description);
        //} else if(topic .equals("Marvel Super Heroes")) {
        questions = new ArrayList<Question>();

        questions.add(new Question("Spider Man's identity?", "Peter Parker"));
            questions.add(new Question("Super Man's Identity", "Clark Kent"));
            questions.add(new Question("Batman's Identity", "Bruce Wayne"));
            questions.add(new Question("Flash's Identity ", "Barry Gordon"));
            description = "The coolest kids you'll ever meet";
            MarvelSuperHeroes = new Topic("Marvel Super Heroes", questions, description);
        //} else if(topic.equals("Science Fiction")) {
        questions = new ArrayList<Question>();

        questions.add(new Question("Isaac Asimov wrote?", "IRobot"));
            questions.add(new Question("Frank Herbert wrote?", "Dune"));
            questions.add(new Question("Robert Heinlein wrote?", "Starship Trooper"));
            questions.add(new Question("Terry Practchett wrote? ", "Discworld"));
            description = "The genre that defines the future";
            ScienceFiction = new Topic("Science Fiction", questions, description);
        //}*/
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

    public void answer(String topic, int correct, int incorrect, int number, String answer, String userAnswer) {
        Bundle bundle = new Bundle();
        bundle.putString("Topic", topic);
        bundle.putInt("Number", number);
        bundle.putInt("Correct", correct);
        bundle.putInt("Incorrect", incorrect);
        bundle.putString("Answer", answer);
        bundle.putString("UserAnswer", userAnswer);
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
}
