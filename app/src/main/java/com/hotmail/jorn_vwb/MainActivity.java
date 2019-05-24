package com.hotmail.jorn_vwb;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileWriter;


public class MainActivity extends Activity implements SensorEventListener{

    private TextView xText, yText, zText;
    private Sensor mySensor;
    private SensorManager SM;
    private FileWriter writer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create SensorManager
        SM = (SensorManager)getSystemService(SENSOR_SERVICE);

        //Create Accelerometer
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //Register Sensor Listener
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

        //Assign TextView
        xText = (TextView)findViewById(R.id.xText);
        yText = (TextView)findViewById(R.id.yText);
        zText = (TextView)findViewById(R.id.zText);
    }

    public void onStartClick(View view) {
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
        Toast.makeText(getApplicationContext(), "Started writing to myfile.txt", Toast.LENGTH_SHORT).show();
    }

    public void onStopClick(View view) {
        SM.unregisterListener(this);
        Toast.makeText(getApplicationContext(), "Stopped", Toast.LENGTH_SHORT).show();
    }
    protected void onResume() {
        super.onResume();
        //writer = new FileWriter("AccelarationSenSorData.txt",true);
    }

    protected void onPause() {
        super.onPause();

        if(writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        xText.setText("X = " + event.values[0]);
        yText.setText("Y = " + event.values[1]);
        zText.setText("Z = " + event.values[2]);

        



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Not in use
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}

