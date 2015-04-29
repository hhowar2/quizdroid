package hhowar2.washington.edu.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class AnswerActivity extends ActionBarActivity {
    String answer;
    String useranswer;
    int correct;
    int incorrect;
    int number;
    String topic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        Bundle extras = getIntent().getExtras();
        number = extras.getInt("Number");
        number++;
        topic = extras.getString("Topic");
        useranswer = extras.getString("UserAnswer");
        answer = extras.getString("Answer");
        correct = extras.getInt("Correct");
        incorrect = extras.getInt("Incorrect");
        TextView text = (TextView) findViewById(R.id.textView4);
        text.setText("My Answer: " + useranswer);
        text = (TextView) findViewById(R.id.textView5);
        text.setText("Correct Answer: " + answer);
        text = (TextView) findViewById(R.id.textView6);
        text.setText(correct + " correct out of " + (incorrect + correct) + " questions");
        Button nextQuestion = (Button) findViewById(R.id.button3);
        if(number < 3) {
            nextQuestion.setText("Next");
        } else {
            nextQuestion.setText("Finish");
        }
        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number < 4) {
                    Intent next = new Intent(AnswerActivity.this, Quizz.class);
                    next.putExtra("Correct", correct);
                    next.putExtra("Incorrect", incorrect);
                    next.putExtra("Number", number);
                    next.putExtra("Topic", topic);
                    startActivity(next);
                } else {
                    Intent next = new Intent(AnswerActivity.this, TopicSelection.class);
                    startActivity(next);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_answer, menu);
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
