package hhowar2.washington.edu.quizdroid;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Howard on 5/22/2015.
 */
public class DownloadComplete extends BroadcastReceiver {
    DownloadManager dm;
    @Override
    public void onReceive(Context context, Intent intent) {
        dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        if(DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())) {
            long reference = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            Toast.makeText(context, "testing", Toast.LENGTH_LONG).show();
            Log.i("Test", "receiver registered");
            if (reference != 0) {
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
                Toast.makeText(context, status + " " + savedFilePath, Toast.LENGTH_LONG).show();
                switch(status) {
                    case DownloadManager.STATUS_PAUSED:
                    case DownloadManager.STATUS_PENDING:
                    case DownloadManager.STATUS_RUNNING:
                        break;
                    case DownloadManager.STATUS_SUCCESSFUL:

                }

            }
        }
    }
}
