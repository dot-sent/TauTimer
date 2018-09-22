package nl.dotsent.tautimer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.GregorianCalendar;

import static java.lang.Math.round;

public class MainActivity extends AppCompatActivity implements AlarmSetter {

    private ManualAlarm f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        f = (ManualAlarm) getSupportFragmentManager().findFragmentById(R.id.fragmentManualAlarm);
    }

    public void setManualAlarm(View v){
        f.onButtonPressed(v);
    }

    @Override
    public void setAlarm(Integer units) {
        Long time = new GregorianCalendar().getTimeInMillis() + units * 864;
        Intent intentAlarm = new Intent(this, AlarmReciever.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,time, PendingIntent.getBroadcast(this,1,  intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));

        Context context = getApplicationContext();
        CharSequence text = "Timer would be set in " + units.toString() + " unit(s) from now";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
