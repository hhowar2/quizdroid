package hhowar2.washington.edu.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.content.Intent;

import java.util.List;


public class TopicSelection extends ActionBarActivity {

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_selection);

        listView = (ListView)findViewById(R.id.listView);
        QuizApp data = (QuizApp)getApplication();

        List<Topic> TopicList = data.TopicList;
        String[] values = new String[TopicList.size()];
        for(int i = 0; i < TopicList.size(); i++ ) {
            values[i] = TopicList.get(i).getName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                String itemValue = (String)listView.getItemAtPosition(position);
                Log.i("Topic Selected", itemValue);
                //Intent next = new Intent(TopicSelection.this, TopicOverview.class);
                Intent next = new Intent(TopicSelection.this, MultiuseActivity.class);
                next.putExtra("Topic", itemValue);
                startActivity(next);
                /*Toast.makeText(getApplicationContext(), "Position:" + itemPosition + " ListItem" + itemValue, Toast.LENGTH_LONG)
                        .show();*/
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_topic_selection, menu);
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
            Log.i("Application", "setting button hit");
            Intent i = new Intent(this, AppPreference.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
