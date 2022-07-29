package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.content.DialogInterface;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView mcurrentheight, mcurrentweight;
    ImageView mincrementweight, mdecrementweight;
    SeekBar mseekbarforheight;
    Button mcalculatebmi;
    RelativeLayout mmale, mfemale;

    int intweight=65;
    int currentprogress;

    String mintprogress="180", typerofuser="0", weight2="65";

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        mcurrentweight=findViewById(R.id.currentweight);
        mcurrentheight=findViewById(R.id.currentheight);
        mincrementweight=findViewById(R.id.incrementweight);
        mdecrementweight=findViewById(R.id.decrementweight);
        mcalculatebmi=findViewById(R.id.calculatebmi);
        mseekbarforheight=findViewById(R.id.seekbarforheight);
        mmale=findViewById(R.id.male);
        mfemale=findViewById(R.id.female);

        mmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mmale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.genderfocus));
                mfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.gendernotfocus));
                typerofuser="Male";
            }
        });

        mfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.genderfocus));
                mmale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.gendernotfocus));
                typerofuser="Female";
            }
        });

        mseekbarforheight.setMax(300);
        mseekbarforheight.setProgress(0);

        mseekbarforheight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                currentprogress=progress;
                mintprogress=String.valueOf(currentprogress);
                mcurrentheight.setText(mintprogress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mincrementweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intweight=intweight+1;
                weight2=String.valueOf(intweight);
                mcurrentweight.setText(weight2);
            }
        });

        mdecrementweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intweight=intweight-1;
                weight2=String.valueOf(intweight);
                mcurrentweight.setText(weight2);
            }
        });

        mcalculatebmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(typerofuser.equals("0"))
                {
                    Toast.makeText(getApplicationContext(), "Select your Gender First", Toast.LENGTH_SHORT).show();
                }
                else if(mintprogress.equals("0"))
                {
                    Toast.makeText(getApplicationContext(), "Select your Height First", Toast.LENGTH_SHORT).show();
                }
                else if(intweight==0 || intweight<0)
                {
                    Toast.makeText(getApplicationContext(), "Weight is Incorrect", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    final ProgressDialog mProgressDialog= new ProgressDialog(MainActivity.this);
                    mProgressDialog.setMessage("Waiting in vain"+ "\n" +"loading");
                    mProgressDialog.setIndeterminate(false);
                    mProgressDialog.setProgress(0);
                    mProgressDialog.setMax(10);
                    mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    mProgressDialog.setCancelable(false);
                    Thread t= new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int progress=0;
                            while(progress<=10){

                                try{
                                    mProgressDialog.setProgress(progress);
                                    progress++;
                                    Thread.sleep(300);

                                }catch (Exception ex){
                                }
                            }
                            mProgressDialog.dismiss();

                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    Intent intent = new Intent(MainActivity.this, bmiact.class);
                                    intent.putExtra("gender", typerofuser);
                                    intent.putExtra("height", mintprogress);
                                    intent.putExtra("weight", weight2);
                                    startActivity(intent);
                                    Toast.makeText(MainActivity.this,"Completed",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    t.start();
                    mProgressDialog.show();

                }

            }

        });


    }
}