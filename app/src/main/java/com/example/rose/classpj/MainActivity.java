package com.example.rose.classpj;

import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.net.Uri;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Intents;
import android.provider.MediaStore;

import static com.example.rose.classpj.R.id.calender;
import static com.example.rose.classpj.R.id.camera;
import static com.example.rose.classpj.R.id.contact;
import static com.example.rose.classpj.R.id.email;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 10001;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(email);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "milanthapaliya@gmail.com", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "hello");
                intent.putExtra(Intent.EXTRA_TEXT, "new email");
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });

        Button button2 = (Button) findViewById(calender);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType("vnd.android.cursor.item/event");

                Calendar cal = Calendar.getInstance();
                long startTime = cal.getTimeInMillis();
                long endTime = cal.getTimeInMillis() + 60 * 60 * 1000;

                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime);
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime);

                intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

                //This is Information about Calender Event.
                intent.putExtra(Events.TITLE, "project");
                intent.putExtra(Events.DESCRIPTION, "This is a description");
                intent.putExtra(Events.EVENT_LOCATION, "school");
                intent.putExtra(Events.RRULE, "FREQ=YEARLY");

                startActivity(intent);
            }
        });

        Button button3 = (Button) findViewById(contact);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                startActivity(intent);
            }
        });

        Button button4 = (Button) findViewById(camera);
        button4.setOnClickListener(new View.OnClickListener() {
           
            public boolean outputFileUri;
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }

           
        });
    }

    private void startActivityForResult(Intent cameraIntent) {
    }
}


