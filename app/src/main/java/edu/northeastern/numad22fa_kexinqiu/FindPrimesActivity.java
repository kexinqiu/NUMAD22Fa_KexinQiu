package edu.northeastern.numad22fa_kexinqiu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;


public class FindPrimesActivity extends AppCompatActivity {

        private Handler textHandler = new Handler();
        //TextView statusText;
        private View view;
        private TextView currNum;
        private TextView lastPrime;
        private boolean findStarted;
        Thread primeThread;
        private int num;
        private int currPrime;

        final private String start = "Start to check primes!";
        final private String end = "Checking primes ended!";
        final private String back = "Are you sure you want to terminate the checking?";
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_find_primes);

            currNum = findViewById(R.id.current_num);
            lastPrime = findViewById(R.id.last_prime_num);

            findStarted = false;
        }

        public void findPrimesStart(View view) {
            findStarted = true;
            num = 3;
            RunnableThread runnable = new RunnableThread();
            primeThread = new Thread(runnable);
            primeThread.start();
            Snackbar.make(view, start, Snackbar.LENGTH_LONG).show();
        }

        public void findPrimeEnd(View view) {
            findStarted = false;
            Snackbar.make(view, end, Snackbar.LENGTH_LONG).show();
        }

        @Override
        public void onBackPressed() {
            // super.onBackPressed();
            if (findStarted) {
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), back, Snackbar.LENGTH_LONG);
                snackbar.setAction("Yes", v -> {
                    snackbar.dismiss();
                    finish();
                });
                snackbar.show();
            } else {
                finish();
            }
        }

    //Class which implements the Runnable interface.
        class RunnableThread implements Runnable {

            @Override
            public void run() {
                while(findStarted) {
                        if (checkPrimes(num)) {
                            currPrime = num;
                        }
                        int finalCurPrime = currPrime;
                        final int finalI = num;
                        //The handler changes the TextView running in the UI thread.
                        textHandler.post(new Runnable() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void run() {
                                currNum.setText(Integer.toString(finalI));
                                lastPrime.setText(Integer.toString(finalCurPrime));
                            }
                        });
                        //Log.d(TAG, "Running on a different thread using Runnable Interface: " + i);

                        try {
                            Thread.sleep(200); //Makes the thread sleep or be inactive for 10 seconds
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        num = num + 2;
                }
            }

            private boolean checkPrimes(int n) {
                for (int i = 2; i < n; i++) {
                    if (n % i == 0) {
                        return false;
                    }
                }
                return true;
            }
        }
    }
