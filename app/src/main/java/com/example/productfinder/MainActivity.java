package com.example.productfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton imageButton = (ImageButton) findViewById(R.id.scan);
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.nearby_shops);
        ImageButton imageButton3 = (ImageButton) findViewById(R.id.list);

        TextView textView = (TextView)findViewById(R.id.textView);

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);


        if(timeOfDay <= 5){
            textView.setText("DZIEŃ DOBRY");
        }else if(timeOfDay >= 18){
            textView.setText("DOBRY WIECZÓR");}

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, barcode_scan.class);
                startActivity(intent1);
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this, nearby_shops.class);
                startActivity(intent2);
            }
        });
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(MainActivity.this, product_list.class);
                startActivity(intent3);
            }
        });
    }
}