package com.funstech.woodcalc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class round_wood extends AppCompatActivity {

    AdView mAdView;
    LinearLayout calculate_LL;
    AlertDialog dialog;
    EditText berinch, longinch, longft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_wood);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        berinch = findViewById(R.id.berinch);
        longinch = findViewById(R.id.longinch);
        longft = findViewById(R.id.longft);

        calculate_LL = findViewById(R.id.calculate_LL);

        calculate_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(berinch.getText().toString())){
                    Toast.makeText(round_wood.this, "Pls enter data correctly",
                    Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(longinch.getText().toString()) && TextUtils.isEmpty(longft.getText().toString())){
                    Toast.makeText(round_wood.this, "Pls enter data correctly",
                            Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(longinch.getText().toString())){
                    longinch.setText("0");

                    showdialog();

                } else if (TextUtils.isEmpty(longft.getText().toString())){
                    longft.setText("0");

                    showdialog();

                } else {

                    showdialog();
                }


            }
        });
    }


    public void showdialog(){

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.roundresdialogue, null);
        dialog = new AlertDialog.Builder(round_wood.this). setView(view).create();
        dialog.show();

        // =====Calculation start===========================

        String strberinch = berinch.getText().toString();
        String strlonginch = longinch.getText().toString();
        String strlongft = longft.getText().toString();

        double dblberinch = Double.parseDouble(strberinch);
        double dbllonginch = Double.parseDouble(strlonginch);
        double dbllongft = Double.parseDouble(strlongft);

        double longinchinft = dbllonginch / 12;
        double totallongft = longinchinft + dbllongft;
        double allmultiple = dblberinch * dblberinch * totallongft;
        double resultincft = allmultiple/ 2304;
        double resultinmcube = resultincft * 0.03;
        double resultwaste = resultincft * 0.215;
        double resultusable = resultincft - resultwaste;

        String strresultincft = String.format("%.2f", resultincft);
        String strresultwaste = String.format("%.2f", resultwaste);
        String strresultusable = String.format("%.2f", resultusable);


        TextView TVtotalwood= view. findViewById(R.id.TVtotalwood);
        TextView TVwastelwood= view. findViewById(R.id.TVwastewood);
        TextView TVusablewood= view. findViewById(R.id.TVusablewood);

        TVtotalwood.setText("Total wood: " + strresultincft + " CFT");
        TVwastelwood.setText("Waste wood: " + strresultwaste + " CFT");
        TVusablewood.setText("Usable wood: " + strresultusable + " CFT");

        // =====Calculation start===========================

        LinearLayout dismis_dial = view. findViewById(R.id.dismis_dial);

        dismis_dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}