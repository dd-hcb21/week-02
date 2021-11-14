package com.nhom4.lifecycle;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends Activity {
    private Context context;

    private EditText txtMsg;
    private Button btnExit;
    private TextView txtSpy;
    private LinearLayout myScreen;
    private String PREFNAME = "myPrefFile1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMsg = (EditText)findViewById(R.id.txtMsg);
        btnExit = (Button)findViewById(R.id.btnExit);
        txtSpy = (TextView)findViewById(R.id.txtSpy);
        myScreen = (LinearLayout)findViewById(R.id.myScreen);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        txtMsg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                String chosenColor = editable.toString().toLowerCase(Locale.US);
                txtSpy.setText(chosenColor);
                setBackgroundColor(chosenColor);
            }
        });

        context = getApplicationContext();
        Toast.makeText(context, "onCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(context, "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(context, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveStateData();
        Toast.makeText(context, "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(context, "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(context, "onDestroy", Toast.LENGTH_SHORT).show();
    }

    private void setBackgroundColor(String color) {
        if (color.contains("red"))
            myScreen.setBackgroundColor(0xffff0000);
        else if (color.contains("green"))
            myScreen.setBackgroundColor(0xff00ff00);
        else if (color.contains("blue"))
            myScreen.setBackgroundColor(0xff0000ff);
        else if (color.contains("white"))
            myScreen.setBackgroundColor(0xffffffff);
        else if (color.contains("gray"))
            myScreen.setBackgroundColor(0xffffffff);
    }

    private void saveStateData() {
        SharedPreferences myPrefContainer = getSharedPreferences(PREFNAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor myPrefEditor = myPrefContainer.edit();
        String key = "chosenBackgroundColor";
        String value = txtSpy.getText().toString();
        myPrefEditor.putString(key, value);
        myPrefEditor.commit();
    }
}