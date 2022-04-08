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

public class saw_wood extends AppCompatActivity {

    AdView mAdView;
    LinearLayout calculate_LLL;
    AlertDialog dialog;
    EditText widthinch, thickinch,longinch, longft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saw_wood);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        widthinch = findViewById(R.id.widthinch);
        thickinch = findViewById(R.id.thickinch);
        longinch = findViewById(R.id.longinch);
        longft = findViewById(R.id.longft);

        calculate_LLL = findViewById(R.id.calculate_LLL);

        calculate_LLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(widthinch.getText().toString())){
                    Toast.makeText(saw_wood.this, "Pls enter data correctly",
                            Toast.LENGTH_SHORT).show();
                }

                else if (TextUtils.isEmpty(thickinch.getText().toString())){
                    Toast.makeText(saw_wood.this, "Pls enter data correctly",
                            Toast.LENGTH_SHORT).show();

                }

                else if (TextUtils.isEmpty(longinch.getText().toString()) && TextUtils.isEmpty(longft.getText().toString())){
                    Toast.makeText(saw_wood.this, "Pls enter data correctly",
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
        View view = inflater.inflate(R.layout.sawresdialogue, null);
        dialog = new AlertDialog.Builder(saw_wood.this). setView(view).create();
        dialog.show();

        // =====Calculation start===========================

        String strwidthinch = widthinch.getText().toString();
        String strthickinch = thickinch.getText().toString();
        String strlonginch = longinch.getText().toString();
        String strlongft = longft.getText().toString();

        double dblwidthinch = Double.parseDouble(strwidthinch);
        double dblthickinch = Double.parseDouble(strthickinch);
        double dbllonginch = Double.parseDouble(strlonginch);
        double dbllongft = Double.parseDouble(strlongft);

        double widthinft = dblwidthinch / 12;
        double thickinft = dblthickinch / 12;
        double longinchinft = dbllonginch / 12;
        double totallongft = longinchinft + dbllongft;

        double resultincft = widthinft * thickinft * totallongft;
        double resultinmcube = resultincft * 0.03;


        String strresultincft = String.format("%.2f", resultincft);



        TextView TVtotalwood= view. findViewById(R.id.TVtotalwood);


        TVtotalwood.setText("Total wood: " + strresultincft + " CFT");

        // =====Calculation start===========================

        LinearLayout dismis_dial = view. findViewById(R.id.dismis_dials);

        dismis_dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}