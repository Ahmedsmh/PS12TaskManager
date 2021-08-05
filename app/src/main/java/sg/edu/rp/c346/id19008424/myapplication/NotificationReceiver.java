
package sg.edu.rp.c346.id19008424.myapplication;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;

import java.util.ArrayList;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationReceiver extends BroadcastReceiver {

    int reqCode = 12345;

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager nm = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        String name = intent.getStringExtra("name");
        String desc = intent.getStringExtra("desc");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new
                    NotificationChannel("default", "Default Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);

            channel.setDescription("This is for default notification");
            nm.createNotificationChannel(channel);
        }

        Intent i = new Intent(context,MainActivity.class);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, reqCode,
                        i, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action action = new
                NotificationCompat.Action.Builder(
                R.mipmap.ic_launcher,
                "Launch Task Manager",
                pendingIntent).build();
        Intent intentreply = new Intent(context,
                MainActivity.class);
        PendingIntent pendingIntentReply = PendingIntent.getActivity
                (context, 0, intentreply,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteInput ri = new RemoteInput.Builder("status")
                .setLabel("Status report")
                .setChoices(new String [] {"Done", "Not yet"})
                .build();

        NotificationCompat.Action action2 = new
                NotificationCompat.Action.Builder(
                R.mipmap.ic_launcher,
                "Delete",
                pendingIntentReply)
                .addRemoteInput(ri)
                .build();



        NotificationCompat.WearableExtender extender = new
                NotificationCompat.WearableExtender();
        extender.addAction(action);
        extender.addAction(action2);


        NotificationCompat.Builder builder = new
                NotificationCompat.Builder(context, "default");
        builder.setSmallIcon(android.R.drawable.btn_star_big_off);

        // Attach the action for Wear notification created abov
        builder.setContentText(desc);
        builder.setContentTitle(name);
        builder.extend(extender);

        Notification notification = builder.build();

        nm.notify(reqCode, notification);

    }
}