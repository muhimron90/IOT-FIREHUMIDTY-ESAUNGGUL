package com.example.chickenronz.iot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pusher.pushnotifications.PushNotifications;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private TextView mtext,txtcomm,txtkel,txttemp,txthum;
    private FirebaseDatabase myDatabase;
   private DatabaseReference myRef,myRef1,myRef2,MyRefTemp,MyRefHum,MyServo;
    GifImageView fanGif,nofangif;
    private Switch fanSwitch;

    //    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
//    DatabaseReference mState = myRef.child("FLAME");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PushNotifications.start(getApplicationContext(), "6566d7b9-f019-4942-87e6-91814637baa4");
        PushNotifications.subscribe("asdads");

        mtext=(TextView)findViewById(R.id.txt_m);
        txtcomm = (TextView)findViewById(R.id.txt_comm);
        txtkel = (TextView)findViewById(R.id.txt_kel);
        txttemp = (TextView)findViewById(R.id.txt_temp);
        txthum = (TextView)findViewById(R.id.txt_hum);
        fanSwitch = (Switch)findViewById(R.id.fan_sw);
        fanGif = (GifImageView)findViewById(R.id.gifImageView);
        nofangif = (GifImageView)findViewById(R.id.nofan);

        myDatabase = FirebaseDatabase.getInstance();
        myRef= myDatabase.getReference().child("FLAME");
        myRef1= myDatabase.getReference().child("Communication");
        myRef2= myDatabase.getReference().child("KEL");
        MyRefTemp = myDatabase.getReference().child("Temp");
        MyRefHum = myDatabase.getReference().child("Humidity");
        MyServo = myDatabase.getReference().child("SERVO STATE");

        nofangif.setVisibility(View.VISIBLE);
        fanGif.setVisibility(View.INVISIBLE);
        fanSwitch.setOnCheckedChangeListener(this);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                mtext.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                txtcomm.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                txtkel.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        MyRefTemp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double value = dataSnapshot.getValue(Double.class);
                double d = value.doubleValue();
                txttemp.setText(String.valueOf(d)+ " \u2103");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        MyRefHum.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double value = dataSnapshot.getValue(Double.class);
                double d = value.doubleValue();
                txthum.setText(String.valueOf(d));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(fanSwitch.isChecked()){
            MyServo.setValue(1);
            fanGif.setVisibility(View.VISIBLE);
            nofangif.setVisibility(View.INVISIBLE);
        }else{
            MyServo.setValue(0);
            nofangif.setVisibility(View.VISIBLE);
            fanGif.setVisibility(View.INVISIBLE);
        }
    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//        mState.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String value = dataSnapshot.getValue(String.class);
//                mtext.setText(value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                mtext.setText("ERROR");
//            }
//        });
//    }
}
