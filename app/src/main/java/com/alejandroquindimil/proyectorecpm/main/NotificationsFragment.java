package com.alejandroquindimil.proyectorecpm.main;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.alejandroquindimil.proyectorecpm.MainActivity;
import com.alejandroquindimil.proyectorecpm.R;

public class NotificationsFragment extends Fragment {

    private Button btn1,btn2,btn3,btn4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notifications, container, false);
        createNotificationChannel();
        initViews(v);
        return v;
    }

    private void initViews(View v) {
        btn1 = v.findViewById(R.id.frg_notif_notify1);
        btn2 = v.findViewById(R.id.frg_notif_notify2);
        btn3 = v.findViewById(R.id.frg_notif_notify3);
        btn4 = v.findViewById(R.id.frg_notif_notify4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNotif1();
            }


        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNotif2();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNotif3();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchNotif4();
            }
        });

    }

    private void launchNotif1() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "1")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Notificacion 1")
                .setContentText("Tienes una nueva notificacion")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Notification noif = builder.build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity());

        notificationManager.notify(1, noif);
    }

    private void launchNotif2() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "1")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Notificacion 2")
                .setContentText("Texto Corto")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Esto es un titulo de una notificación de una aplicación android. Esto es un titulo de una notificación de una aplicación android. \n \n Esto es un titulo de una notificación de una aplicación android."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Notification noif = builder.build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity());

        notificationManager.notify(2, noif);
    }

    private void launchNotif3() {

        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("DO_LOGOUT", true);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Notificacion 3")
                .setContentText("Clicka aqui para accerder ")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        Notification noif = builder.build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity());

        notificationManager.notify(3, noif);
    }

    private void launchNotif4() {

        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("DO_LOGOUT", true);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Notificacion 4")
                .setContentText("Esta notificacion no hace nada")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);;

        Notification noif = builder.build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity());

        notificationManager.notify(3, noif);

    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "cnala";
            String description = "desc_ canal";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("1", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        
    }
}
