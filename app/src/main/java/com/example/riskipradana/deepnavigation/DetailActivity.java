package com.example.riskipradana.deepnavigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE= "extra_title";
    public static final String EXTRA_MESSAGE= "extra_message";
    private TextView tvTitle;
    private TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.tv_title);
        tvMessage = findViewById(R.id.tv_message);

        String title = getIntent().getStringExtra(EXTRA_TITLE);
        tvTitle.setText(title);
        String message = getIntent().getStringExtra(EXTRA_MESSAGE);
        tvMessage.setText(message);

    }
}
