package hhowar2.washington.edu.quizdroid;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Howard on 5/22/2015.
 */
public class DisplayToast implements Runnable {
    private final Context mContext;
    String mText;

    public DisplayToast(Context mContext, String text){
        this.mContext = mContext;
        mText = text;
    }

    public void run(){
        Toast.makeText(mContext, mText, Toast.LENGTH_LONG).show();
    }
}
