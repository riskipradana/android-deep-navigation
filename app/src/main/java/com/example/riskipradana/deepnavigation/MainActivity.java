package com.example.riskipradana.deepnavigation;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.DeadObjectException;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnOpenDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpenDetail = findViewById(R.id.btn_open_detail);
        btnOpenDetail.setOnClickListener(this);

        DelayAsync delayAsync = new DelayAsync();
        delayAsync.execute();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_open_detail){
            Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
            detailIntent.putExtra(DetailActivity.EXTRA_TITLE, "Hola, Good News");
            detailIntent.putExtra(DetailActivity.EXTRA_MESSAGE, "Now you can learn android in Dicoding");
            startActivity(detailIntent);
        }
    }

    private class DelayAsync extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            String title = "Hi, How are You?";
            String message = "Do you have any plan this weekend? Let's hangout";
            int notifId = 110;
            showNotification(MainActivity.this, title, message, notifId);
        }
    }

    private void showNotification(Context context, String title, String message, int notifId) {

        Intent notifDetailIntent = new Intent(MainActivity.this, DetailActivity.class);
        notifDetailIntent.putExtra(DetailActivity.EXTRA_TITLE, title);
        notifDetailIntent.putExtra(DetailActivity.EXTRA_MESSAGE, message);

        PendingIntent pendingIntent = null;

        if(Build.VERSION.SDK_INT > 15) {
            pendingIntent =
                    TaskStackBuilder.create(this)
                            .addParentStack(DetailActivity.class)
                            .addNextIntent(notifDetailIntent)
                            .getPendingIntent(110, PendingIntent.FLAG_UPDATE_CURRENT);
        }

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(message)
                .setColor(ContextCompat.getColor(context, android.R.color.white))
//                .setVibrate(new Integer[] {1000, 1000, 1000, 1000, 1000})
                .setSound(alarmSound)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        notificationManager.notify(notifId, builder.build());
    }
}
