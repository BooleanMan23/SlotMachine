package com.example.slotmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView msg;
    private ImageView img1, img2;
    private Wheel wheel1, wheel2, wheel3;
    private Button btn;
    private Button lihatSkorButton;
    private Button resetSkorButton;
    private boolean isStarted;
    private List<String> listHasilSkor;

    public static final Random RANDOM = new Random();

    public static long randomLong(long lower, long upper) {
        return lower + (long) (RANDOM.nextDouble() * (upper - lower));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listHasilSkor = new ArrayList<>();
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        btn = (Button) findViewById(R.id.btn);
        lihatSkorButton = findViewById(R.id.lihatSkorButton);
        resetSkorButton = findViewById(R.id.resetSkorButton);
        msg = (TextView) findViewById(R.id.msg);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isStarted) {
                    wheel1.stopWheel();
                    wheel2.stopWheel();
                    //kertas itu 0
                    //batu itu 1
                    //gunting itu 2

                    Log.i("wheel", "======================");
                    Log.i("wheel1", String.valueOf(wheel1.currentIndex));
                    Log.i("wheel2", String.valueOf(wheel2.currentIndex));
                    int hasilWheel1 = wheel1.currentIndex;
                    int hasilWheel2 = wheel2.currentIndex;

                    if (hasilWheel1 == hasilWheel2){
                        String messege = "Seri";
                        msg.setText(messege);
                        listHasilSkor.add(messege);
                    }
                    if (hasilWheel1 == 0 && hasilWheel2==1){
                        String messege = "Manusia Menang";
                        msg.setText(messege);
                        listHasilSkor.add(messege);
                    }
                    if (hasilWheel1 == 0 && hasilWheel2 == 2){
                        String messege = "CPU menang";
                        msg.setText(messege);
                        listHasilSkor.add(messege);
                    }
                    if (hasilWheel1 == 1 && hasilWheel2 == 0){
                        String messege = "CPU menang";
                        msg.setText(messege);
                        listHasilSkor.add(messege);
                    }
                    if (hasilWheel1 == 1 && hasilWheel2 ==2){
                        String messege = "Manusia Menang";
                        msg.setText(messege);
                        listHasilSkor.add(messege);
                    }
                    if (hasilWheel1 == 2 && hasilWheel1 == 0){
                        String messege = "Manusia Menang";
                        msg.setText(messege);
                        listHasilSkor.add(messege);
                    }
                    if (hasilWheel1 ==2 && hasilWheel2 == 1){
                        String messege = "CPU Menang";
                        msg.setText(messege);
                        listHasilSkor.add(messege);
                    }

                    btn.setText("Start");
                    isStarted = false;

                } else {

                    wheel1 = new Wheel(new Wheel.WheelListener() {
                        @Override
                        public void newImage(final int img) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    img1.setImageResource(img);
                                }
                            });
                        }
                    }, 200, randomLong(0, 200));

                    wheel1.start();

                    wheel2 = new Wheel(new Wheel.WheelListener() {
                        @Override
                        public void newImage(final int img) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    img2.setImageResource(img);
                                }
                            });
                        }
                    }, 200, randomLong(150, 400));

                    wheel2.start();



                    btn.setText("Stop");
                    msg.setText("");
                    isStarted = true;
                }
            }
        });

        lihatSkorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i<listHasilSkor.size(); i++){
                    Log.i("Hasil Skor", listHasilSkor.get(i));
                }
            }
        });

        resetSkorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listHasilSkor.clear();
            }
        });
    }
}