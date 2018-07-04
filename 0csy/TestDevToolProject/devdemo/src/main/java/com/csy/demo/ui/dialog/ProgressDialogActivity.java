package com.csy.demo.devtool.ui.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


import com.csy.demo.devtool.R;
import com.tool.csy.devcsytool.ui.dialog.materialishprogress.ProgressWheel;
import com.tool.csy.devcsytool.utils.convert.DensityUtils;


public class ProgressDialogActivity extends ActionBarActivity {

    private ProgressWheel progressWheel;
    private ProgressWheel progressWheelInterpolated;
    private ProgressWheel progressWheelLinear;
    private ProgressWheel progressWithLogo;
    private TextView interpolatedValue;
    private TextView linearValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        Button buttonAbout = (Button) findViewById(R.id.button_about);
        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ProgressDialogActivity.this)
                        .setTitle(R.string.about)
                        .setMessage(R.string.about_text)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                dialog.show();
            }
        });

        progressWheel = (ProgressWheel) findViewById(R.id.progress_wheel);
        progressWheelInterpolated = (ProgressWheel) findViewById(R.id.interpolated);
        progressWheelLinear = (ProgressWheel) findViewById(R.id.linear);


        progressWithLogo = (ProgressWheel) findViewById(R.id.progressWithLogo);
//        progressWithLogo.setProgress(0.1f);
        progressWithLogo.setBarColor(Color.RED);//进度颜色
        progressWithLogo.setBackgroundColor(Color.YELLOW);//进度条背景色
        progressWithLogo.setRimColor(Color.BLUE);
        progressWithLogo.setCircleRadius((int)DensityUtils.px2dp(ProgressDialogActivity.this,400));
//        progressWithLogo.setCircleRadius(38);
//        progressWithLogo.setCallback(new ProgressWheel.ProgressCallback() {//通过进度循环转圈
//            @Override
//            public void onProgressUpdate(float progress) {

//                if(progress == 0.1f) {
//                    progressWithLogo.setProgress(0.5f);
//                } else if(progress == 0.5f) {
//                    progressWithLogo.setProgress(0.1f);
//                }
//
//                //interpolatedValue.setText(String.format("%.2f", progress));
//            }
//        });


        interpolatedValue = (TextView) findViewById(R.id.interpolatedValue);
        linearValue = (TextView) findViewById(R.id.linearValue);

        Spinner spinnerOptions = (Spinner) findViewById(R.id.spinner_options);
        spinnerOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0:
                        progressWheelLinear.setProgress(0.0f);
                        progressWheelInterpolated.setProgress(0.0f);

                        progressWheelInterpolated.setCallback(new ProgressWheel.ProgressCallback() {
                            @Override
                            public void onProgressUpdate(float progress) {
                                if(progress == 0) {
                                    progressWheelInterpolated.setProgress(1.0f);
                                } else if(progress == 1.0f) {
                                    progressWheelInterpolated.setProgress(0.0f);
                                }

                                interpolatedValue.setText(String.format("%.2f", progress));
                            }
                        });

                        progressWheelLinear.setCallback(new ProgressWheel.ProgressCallback() {
                            @Override
                            public void onProgressUpdate(float progress) {
                                if(progress == 0) {
                                    progressWheelLinear.setProgress(1.0f);
                                } else if(progress == 1.0f) {
                                    progressWheelLinear.setProgress(0.0f);
                                }

                                linearValue.setText(String.format("%.2f", progress));
                            }
                        });
                        break;
                    case 1:
                        setProgress(0.0f);
                        break;
                    case 2:
                        setProgress(0.1f);
                        break;
                    case 3:
                        setProgress(0.25f);
                        break;
                    case 4:
                        setProgress(0.5f);
                        break;
                    case 5:
                        setProgress(0.75f);
                        break;
                    case 6:
                        setProgress(1.0f);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final int defaultBarColor = progressWheel.getBarColor();
        final int defaultWheelColor = progressWheel.getRimColor();

        Spinner colorOptions = (Spinner) findViewById(R.id.spinner_options_color);
        colorOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0:
                        progressWheel.setBarColor(defaultBarColor);
                        progressWheelInterpolated.setBarColor(defaultBarColor);
                        progressWheelLinear.setBarColor(defaultBarColor);
                        break;
                    case 1:
                        progressWheel.setBarColor(Color.RED);
                        progressWheelInterpolated.setBarColor(Color.RED);
                        progressWheelLinear.setBarColor(Color.RED);
                        break;
                    case 2:
                        progressWheel.setBarColor(Color.MAGENTA);
                        progressWheelInterpolated.setBarColor(Color.MAGENTA);
                        progressWheelLinear.setBarColor(Color.MAGENTA);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner wheelColorOptions = (Spinner) findViewById(R.id.spinner_options_rim_color);
        wheelColorOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0:
                        progressWheel.setRimColor(defaultWheelColor);
                        progressWheelInterpolated.setRimColor(defaultWheelColor);
                        progressWheelLinear.setRimColor(defaultWheelColor);
                        break;
                    case 1:
                        progressWheel.setRimColor(Color.LTGRAY);
                        progressWheelInterpolated.setRimColor(Color.LTGRAY);
                        progressWheelLinear.setRimColor(Color.LTGRAY);
                        break;
                    case 2:
                        progressWheel.setRimColor(Color.GRAY);
                        progressWheelInterpolated.setRimColor(Color.GRAY);
                        progressWheelLinear.setRimColor(Color.GRAY);
                        break;
                }
            }





            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setProgress(float progress) {
        progressWheelLinear.setCallback(new ProgressWheel.ProgressCallback() {
            @Override
            public void onProgressUpdate(float progress) {
                linearValue.setText(String.format("%.2f", progress));
            }
        });
        progressWheelInterpolated.setCallback(new ProgressWheel.ProgressCallback() {
            @Override
            public void onProgressUpdate(float progress) {
                interpolatedValue.setText(String.format("%.2f", progress));
            }
        });

        progressWheelLinear.setProgress(progress);
        progressWheelInterpolated.setProgress(progress);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }
}
