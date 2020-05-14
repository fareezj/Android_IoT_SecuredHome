package com.fareez.rumahmelaka;

import android.app.Notification;
import android.app.NotificationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;
    private EditText editTextTitle;
    private EditText editTextMessage;
    DatabaseReference mref;
    String pintu_dapur_belakang;
    String tingkap_tepi_homestay;
    String dapur_gas;
    String api_dapur;
    TextView status_pintu_dapur_belakang;
    TextView status_tingkap_tepi_homestay;
    TextView status_dapur_gas;
    TextView status_api_dapur;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        notificationManager = NotificationManagerCompat.from(this);



        mref = FirebaseDatabase.getInstance().getReference();
        status_pintu_dapur_belakang = findViewById(R.id.status_pintu_dapur_belakang);
        status_tingkap_tepi_homestay = findViewById(R.id.status_tingkap_tepi_homestay);
        status_dapur_gas = findViewById(R.id.status_dapur_gas);
        status_api_dapur = findViewById(R.id.status_api_dapur);


        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pintu_dapur_belakang = dataSnapshot.child("Pintu_Dapur_Belakang").getValue().toString();
//                tingkap_tepi_homestay = dataSnapshot.child("Tingkap_Tepi_Homestay").getValue().toString();
//                dapur_gas = dataSnapshot.child("Dapur_Gas").getValue().toString();
                api_dapur = dataSnapshot.child("Api_Dapur").getValue().toString();
                status_api_dapur.setText(api_dapur);
//                status_dapur_gas.setText(dapur_gas);
                status_pintu_dapur_belakang.setText(pintu_dapur_belakang);
//                status_tingkap_tepi_homestay.setText(tingkap_tepi_homestay);

                 // Notification !

                if(pintu_dapur_belakang.equals("Detected")) {

                    Notification notification = new NotificationCompat.Builder(MainActivity.this, App.CHANNEL_1_ID)
                            .setSmallIcon(R.drawable.ic_1)
                            .setContentTitle("WARNING !")
                            .setContentText("Object Detected ! (Pintu Dapur Belakang)")
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                            .build();

                    notificationManager.notify(1, notification);
                }
                /*
                if(tingkap_tepi_homestay.equals("Detected")) {

                    Notification notification = new NotificationCompat.Builder(MainActivity.this, App.CHANNEL_1_ID)
                            .setSmallIcon(R.drawable.ic_1)
                            .setContentTitle("WARNING !")
                            .setContentText("Object Detected ! (Tingkap Tepi Homestay)")
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                            .build();

                    notificationManager.notify(1, notification);
                }
                if(dapur_gas.equals("Danger")) {

                    Notification notification = new NotificationCompat.Builder(MainActivity.this, App.CHANNEL_1_ID)
                            .setSmallIcon(R.drawable.ic_1)
                            .setContentTitle("WARNING !")
                            .setContentText("Danger Gas Level Detected !")
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                            .build();

                    notificationManager.notify(1, notification);
                }
                */
                if(api_dapur.equals("Fire Detected")) {

                    Notification notification = new NotificationCompat.Builder(MainActivity.this, App.CHANNEL_1_ID)
                            .setSmallIcon(R.drawable.ic_1)
                            .setContentTitle("WARNING !")
                            .setContentText("Fire Detected !!")
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                            .build();

                    notificationManager.notify(1, notification);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }





}
