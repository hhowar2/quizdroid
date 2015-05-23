package hhowar2.washington.edu.quizdroid;

import android.app.DownloadManager;
import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;


/**
 * Created by Howard on 5/19/2015.
 */
public class DownloadService extends IntentService {
    private DownloadManager dm;
    private long downloadID;
    private BroadcastReceiver receiverDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //long reference = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            Log.i("Test", "receiver registered");
            /*if (downloadID == reference) {
                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(reference);
                Cursor cursor = dm.query(query);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                int status = cursor.getInt(columnIndex);
                int fileNameIndex = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME);
                String savedFilePath = cursor.getString(fileNameIndex);
                int columnReason = cursor.getColumnIndex(DownloadManager.COLUMN_REASON);
                int reason = cursor.getInt(columnReason);
                Log.i("Test", status + " " + savedFilePath);
            }*/
        }
    };;

    public DownloadService() {
        super("DownloadService");

    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        IntentFilter test = new IntentFilter("com.tutorialspoint.CUSTOM_INTENT");
        /*receiverDownloadComplete = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long reference = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                Log.i("Test", "receiver registered");
                if (downloadID == reference) {
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(reference);
                    Cursor cursor = dm.query(query);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                    int status = cursor.getInt(columnIndex);
                    int fileNameIndex = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME);
                    String savedFilePath = cursor.getString(fileNameIndex);
                    int columnReason = cursor.getColumnIndex(DownloadManager.COLUMN_REASON);
                    int reason = cursor.getInt(columnReason);
                    Log.i("Test", status + " " + savedFilePath);
                }
            }
        };*/
        registerReceiver(receiverDownloadComplete, test);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("Application", "download service called");
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(this);
        String url = SP.getString("URL", "http://tednewardsandbox.site44.com/questions.json");
        dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("Question data");
        request.setTitle("Question File");
        request.setVisibleInDownloadsUi(true);
        downloadID = dm.enqueue(request);
        //do the work in here
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiverDownloadComplete);
    }


}
