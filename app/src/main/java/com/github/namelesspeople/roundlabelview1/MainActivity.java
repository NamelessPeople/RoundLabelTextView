package com.github.namelesspeople.roundlabelview1;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.namelesspeople.roundlabeltextview.RoundLabelTextView;

public class MainActivity extends AppCompatActivity {
    RoundLabelTextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.round_text);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setBackGroundColor(ContextCompat.getColor(MainActivity.this,R.color.colorPrimaryDark));
                textView.invalidate();
            }
        });
    }
}
