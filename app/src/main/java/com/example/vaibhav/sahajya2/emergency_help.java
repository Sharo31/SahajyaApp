package com.example.vaibhav.sahajya2;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.HashMap;
import java.util.Map;



public class emergency_help extends AppCompatActivity {

    // Constants for the notification actions buttons.
    private static final String ACTION_UPDATE_NOTIFICATION = "com.android.example.notifyme.ACTION_UPDATE_NOTIFICATION";
    // Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    // Notification ID.
    private static final int NOTIFICATION_ID = 0;
    private NotificationManager mNotifyManager;

    //private NotificationReceiver mReceiver = new NotificationReceiver();
    private EditText contact;
    private EditText emergency_text;
    private CheckBox yes_box;
    private TextView loc;
    private CheckBox no_box;
    private Button submit_emergency;
    String Latitude;

    public String getLatitude() {
        return Latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    String Longitude;
    private LocationManager locationManager;
    private LocationListener locationListener;



    String[] SPINNERLISTEH = {"A Narayanapura","Adugodi","Agara","Agrahara Dasarahalli","Anjanapura","Arakere","Arakere","B T M Layout","Bagalakunte","Banasavadi","Banashankari Temple","Basavanagudi","Basavanapura","Chickpete","Cv Raman Nagar","Dayananda Nagar","Devasandra","Gandhinagar","Gandhinagar","Gottigere","Hal Airport","Halsoor","Hbr Layout","Hebbal","Hmt","Hosahalli","Hosakerehalli","Hsr Layout","J P Nagar","Jakkasandra","Jalahalli","Jayanagara","JP Park","K R Market","K R Pura","Kammanahalli","Koramangala","Kuvempu Nagar","Malleswaram","Marattahalli","Mattikere","Nagapura","Nagavara","Peenya Industrial Area","Radhakrishna Temple","Rajajinagar","Rajarajeshwari Nagar","Sanjay Nagar","Shakambari Nagar","Shanthi Nagar","Shivaji Nagar","Srinagara","Sunkenahalli","Vasanth Nagar","Vasanthpura","Vijayanagara","Vishveshwara Puram","Vrisabhavathi Nagar","Yelchenahalli","Yeshwanthpura"};

    private FirebaseFirestore firebaseFirestore;
    int flag = 0;
    /*public void checkbox_clicked(View view)
    {
        if (yes_box.isChecked())
        {
            String airlift = "Yes";
        }

        if (no_box.isChecked())
        {   flag=1;
            String airlift ="no";
        }
    }
    */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)
                    ==PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_help);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Emergency Help Portal");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Log.d("Location", location.toString());
                double lat = location.getLatitude();
                double longi = location.getLongitude();
                final String a = ""+lat;
                final String b = "" + longi;
                setLatitude(a);
                setLongitude(b);

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        }
        else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 100, locationListener);
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 100, locationListener);
        firebaseFirestore = FirebaseFirestore.getInstance();
        contact = (EditText) findViewById(R.id.contactid);
        emergency_text = (EditText) findViewById(R.id.emergency_context);
        yes_box = (CheckBox) findViewById(R.id.yesid);
        no_box = (CheckBox) findViewById(R.id.noid);
        submit_emergency = (Button) findViewById(R.id.submit_emergency);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLISTEH);
        final MaterialBetterSpinner betterSpinner = (MaterialBetterSpinner) findViewById(R.id.choice_location);
        betterSpinner.setAdapter(arrayAdapter);

        createNotificationChannel();
        yes_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(emergency_help.this, "You have selected yes", Toast.LENGTH_SHORT).show();
            }
        });

        no_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 1;
            }
        });
        final ArrayAdapter<String> arrayAdaptereh = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLISTEH);
        //final MaterialBetterSpinner materialBetterSpinnereh = (MaterialBetterSpinner) findViewById(R.id.location);
        submit_emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sendNotification();

                String contact_db = contact.getText().toString();
                String emergency_textdb = emergency_text.getText().toString();
                String loc_pref = betterSpinner.getText().toString();
                String airliftdb;
                if (flag == 0) {
                    airliftdb = "yes";
                    //notificationcall();
                    sendNotification();
                } else {
                    airliftdb = "no";

                }

                final Map<String, String> emergency_help_map = new HashMap<>();
                emergency_help_map.put("Contact", contact_db);
                emergency_help_map.put("Emergency", emergency_textdb);
                emergency_help_map.put("Latitude",getLatitude());
                emergency_help_map.put("Longitude",getLongitude());
                emergency_help_map.put("Ward",loc_pref);
                emergency_help_map.put("Airlift", airliftdb);
                firebaseFirestore.collection("Emergency Help").add(emergency_help_map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Toast.makeText(emergency_help.this, "Your response has been submitted", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(emergency_help.this, "Error adding your details to database", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                });
            }
        });
    }

    /*public void notificationcall() {
       /* NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            /*CharSequence name = getString(R.string.common_google_play_services_notification_channel_name);
            String description = getString(R.id.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_MAX);

            notificationChannel.setDescription("Channel Description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }


            NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this, "M_CH_ID");
            notificationBuilder.setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setSmallIcon(R.drawable.ic_notification_icon)
                    .setContentTitle("Airlift Notification")
                    .setAutoCancel(true)
                    .setContentText("Please stay calm, Help is on the way ")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, notificationBuilder.build());
        }*/

    public void sendNotification() {
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
    }



    public void createNotificationChannel()
    {
        mNotifyManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >=android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Mascot Notification", NotificationManager
                    .IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from Mascot");


            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});

            mNotifyManager.createNotificationChannel(notificationChannel);

            // Create a NotificationChannel
        }
    }
    private NotificationCompat.Builder getNotificationBuilder(){
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this,PRIMARY_CHANNEL_ID)
                .setContentTitle("Emergency Help")
                .setContentText("Please stay calm , help is arriving ")
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setAutoCancel(true);
        return notifyBuilder;
    }
}
