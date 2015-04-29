package hhowar2.washington.edu.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class TopicOverview extends ActionBarActivity {

    Topic Math;
    Topic Physics;
    Topic MarvelSuperHeroes;
    Topic ScienceFiction;

    Button Begin;
    List<Question> questions;
    String topic;
    String description;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_overview);
        questions = new ArrayList<Question>();




        Begin = (Button) findViewById(R.id.button);




        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            topic = extras.getString("Topic");
            if(topic.equals("Math")) {
                questions.add(new Question("1 + 1", "2"));
                questions.add(new Question("2 + 1", "3"));
                questions.add(new Question("3 + 1", "4"));
                questions.add(new Question("4 + 1", "5"));
                description = "The study of relationships and numbers";
                Math = new Topic("Math", questions, description);
            } else if(topic.equals("Physics")) {
                questions.add(new Question("Gravity", "-9.8m/s^2"));
                questions.add(new Question("F=", "ma"));
                questions.add(new Question("e=", "mc^2"));
                questions.add(new Question("p=", "MV"));
                String description = "Explaining real-world laws through math";
                Physics = new Topic("Physics", questions, description);
            } else if(topic .equals("Marvel Super Heroes")) {
                questions.add(new Question("Spider Man's identity?", "Peter Parker"));
                questions.add(new Question("Super Man's Identity", "Clark Kent"));
                questions.add(new Question("Batman's Identity", "Bruce Wayne"));
                questions.add(new Question("Flash's Identity ", "Barry Gordon"));
                String description = "The coolest kids you'll ever meet";
                MarvelSuperHeroes = new Topic("Marvel Super Heroes", questions, description);
            } else if(topic.equals("Science Fiction")) {
                questions.add(new Question("Isaac Asimov wrote?", "IRobot"));
                questions.add(new Question("Frank Herbert wrote?", "Dune"));
                questions.add(new Question("Robert Heinlein wrote?", "Starship Trooper"));
                questions.add(new Question("Terry Practchett wrote? ", "Discworld"));
                String description = "The genre that defines the future";
                ScienceFiction = new Topic("Science Fiction", questions, description);
            }
            TextView newTextView = (TextView) findViewById(R.id.textView2);
            newTextView.setText(topic);
            newTextView = (TextView) findViewById(R.id.Question);
            newTextView.setText("Number of Questions:" + questions.size());
            newTextView = (TextView) findViewById(R.id.Description);
            newTextView.setText(description);
        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(TopicOverview.this, Quizz.class);
                next.putExtra("Topic", topic);
                next.putExtra("Number", 0);
                startActivity(next);
            }
        };
            Begin.setOnClickListener(oclBtn);

        }

}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_topic_overview, menu);
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
