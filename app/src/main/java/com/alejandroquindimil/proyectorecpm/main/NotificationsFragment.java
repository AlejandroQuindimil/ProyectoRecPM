package com.alejandroquindimil.proyectorecpm.main;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alejandroquindimil.proyectorecpm.R;

public class NotificationsFragment extends Fragment {

    private Button btn1,btn2;
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


    }

    private void launchNotif1() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "1")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Titulo")
                .setContentText("Esto es un titulo de una notificación de una aplicación android")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Notification noif = builder.build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity());

        notificationManager.notify(1, noif);
    }

    private void launchNotif2() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "1")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Titulo")
                .setContentText("Texto Corto")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Esto es un titulo de una notificación de una aplicación android. Esto es un titulo de una notificación de una aplicación android. \n \n Esto es un titulo de una notificación de una aplicación android."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Notification noif = builder.build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity());

        notificationManager.notify(2, noif);
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
