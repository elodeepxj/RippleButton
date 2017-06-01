package com.jokerpeng.demo.ripplebutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RippleButton rippleButton = (RippleButton) findViewById(R.id.rb);
        rippleButton.setmListender(new RippleButton.OnBeforeClickedListender() {
            @Override
            public void onBeforeClicked(View view) {
            }
        });
        rippleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
