package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class bmiact extends AppCompatActivity {

    TextView mbmidisplay, mbmicategory, mgenderdisplay;
    Button mrecalculatebmi;
    Intent intent;

    ImageView mimageview;
    String mbmi;
    float intbmi;

    String height;
    String weight;
    float intheight, intweight;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiact);

        ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor("#1e1d1d"));
        getSupportActionBar().hide();

        intent=getIntent();
        mbmidisplay=findViewById(R.id.bmidisplay);

        mbmicategory=findViewById(R.id.bmicategory);
        mrecalculatebmi=findViewById(R.id.recalculatebmi);

        mimageview=findViewById(R.id.imageview);
        mgenderdisplay=findViewById(R.id.genderdisplay);

        height=intent.getStringExtra("height");
        weight=intent.getStringExtra("weight");

        intheight=Float.parseFloat(height);
        intweight=Float.parseFloat(weight);

        intheight=intheight/100;
        intbmi=intweight/(intheight*intheight);

        mbmi=Float.toString(intbmi);
        System.out.println(mbmi);

        if(intbmi<16)
        {
            mbmicategory.setText("Severe Thinness");
            mimageview.setImageResource(R.drawable.cross);
        }
        else if(intbmi<16.9 && intbmi>16)
        {
            mbmicategory.setText("Moderate Thinness");
            mimageview.setImageResource(R.drawable.warning);
        }
        else if(intbmi<18.4 && intbmi>17)
        {
            mbmicategory.setText("Mild Thinness");
            mimageview.setImageResource(R.drawable.warning);
        }
        else if(intbmi<24.9 && intbmi>18.5)
        {
            mbmicategory.setText("Normal");
            mimageview.setImageResource(R.drawable.pass);
        }
        else if(intbmi<29.9 && intbmi>25)
        {
            mbmicategory.setText("Over Weight");
            mimageview.setImageResource(R.drawable.warning);
        }
        else if(intbmi<34.9 && intbmi>30)
        {
            mbmicategory.setText("Obese Class I");
            mimageview.setImageResource(R.drawable.warning);
        }
        else
        {
            mbmicategory.setText("Obese Class II");
            mimageview.setImageResource(R.drawable.cross);
        }

        mgenderdisplay.setText(intent.getStringExtra("gender"));
        mbmidisplay.setText(mbmi);

        mrecalculatebmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent1);
            }
        });

    }
}