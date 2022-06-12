package com.example.productfinder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.mlkit.common.MlKitException;
import com.example.productfinder.R;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning;
import java.util.Locale;
import java.util.Random;

public class barcode_scan extends AppCompatActivity {

    private WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scan);

        TextView barcodeResultView = findViewById(R.id.barcode_result_view);
        findViewById(R.id.scan_barcode_button)
                .setOnClickListener(
                        v -> {
                            GmsBarcodeScanner gmsBarcodeScanner = GmsBarcodeScanning.getClient(this);
                            gmsBarcodeScanner
                                    .startScan()
                                    .addOnSuccessListener(
                                            barcode -> {
                                                barcodeResultView.setText(getSuccessfulMessage(barcode));
                                                setContentView(R.layout.activity_find_in_ceneo);
                                                webView = findViewById(R.id.webView);
                                                webView.setWebViewClient(new WebViewClient());
                                                webView.getSettings().setDomStorageEnabled(true);
                                                webView.getSettings().setAppCacheEnabled(true);
                                                webView.getSettings().setLoadsImagesAutomatically(true);
                                                webView.getSettings().setJavaScriptEnabled(true);
                                                webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
                                                webView.loadUrl("https://www.ceneo.pl/;szukaj-" + getSuccessfulMessage(barcode) + "/Url");


                                            })
                                    .addOnFailureListener(
                                            e -> {
                                                barcodeResultView.setText(getErrorMessage((MlKitException) e));
                                            });



                        });

        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, find_in_ceneoActivity.class);
                startActivity(intent1);
            }
        });*/
    }

    public String getSuccessfulMessage(Barcode barcode) {
        String barcodeValue =
                String.format(
                        Locale.US,
                        //"Display Value: %s\nRaw Value: %s\nFormat: %s\nValue Type: %s",
                        barcode.getDisplayValue());
        //barcode.getRawValue(),
        //barcode.getFormat(),
        //barcode.getValueType());
        //return getString(R.string.barcode_result, barcodeValue);
        return barcodeValue;
    }

    @SuppressLint("SwitchIntDef")
    public String getErrorMessage(MlKitException e) {
        switch (e.getErrorCode()) {
            case MlKitException.CODE_SCANNER_CANCELLED:
                return getString(R.string.error_scanner_cancelled);
            case MlKitException.CODE_SCANNER_CAMERA_PERMISSION_NOT_GRANTED:
                return getString(R.string.error_camera_permission_not_granted);
            case MlKitException.CODE_SCANNER_APP_NAME_UNAVAILABLE:
                return getString(R.string.error_app_name_unavailable);
            default:
                return getString(R.string.error_default_message, e);
        }
    }
}




