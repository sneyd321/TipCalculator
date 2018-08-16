package com.example.ryan.simpleapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void save(View view) {

        int intValue = 12;
        float floatValue = 34.89f;
        String stringValue = "Howdy";
        boolean booleanValue = true;


        SharedPreferences myPrefs = getPreferences(MODE_PRIVATE);


        SharedPreferences.Editor editor = myPrefs.edit();

        editor.putInt("intValue", intValue);
        editor.putFloat("floatValue", floatValue);
        editor.putString("stringValue", stringValue);
        editor.putBoolean("booleanValue", booleanValue);


        editor.apply();


    }

    public void read(View view) {
        SharedPreferences myPrefs = getPreferences(MODE_PRIVATE);

        StringBuilder builder = new StringBuilder(("Here are the values:\n"));

        builder.append(myPrefs.getInt("intValue", -1)).append("\n");
        builder.append(myPrefs.getFloat("floatValue", -1.0f)).append("\n");
        builder.append(myPrefs.getString("StringValue", null)).append("\n");
        builder.append(myPrefs.getBoolean("BooleanValue", false)).append("\n");

        TextView info = (TextView) findViewById(R.id.txtOutput);

        info.setText(builder.toString());




    }

    public void clear(View view) {

        SharedPreferences myPrefs = getPreferences(MODE_PRIVATE);

        SharedPreferences.Editor editor = myPrefs.edit();

        editor.clear().apply();

    }
}
