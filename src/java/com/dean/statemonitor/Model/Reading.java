package com.dean.statemonitor.Model;

import com.dean.statemonitor.Servlets.TotalSensorReadings;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Reading {

    private static boolean isReadingStarted = false;
    private static ArrayList<TotalSensorReadings> TotSenReadOver = null;
    private static int value;

    /**
     * @return the TotSenReadOver
     */
    public static ArrayList<TotalSensorReadings> getTotSenReadOver() {
        return TotSenReadOver;
    }

    public void StartReading() {

        if (!Reading.isReadingStarted) {
            value = Integer.parseInt(System.getProperty("Readingfreq"));
            Thread t1 = new Thread(new Runnable() {

                @Override
                public void run() {
                    Timer t = new Timer();
                    t.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            TotalSensorReadings tosenred = new TotalSensorReadings();
                            ArrayList<TotalSensorReadings> alltored = tosenred.getLatestOverReadings();
                            Reading.TotSenReadOver = alltored;
                        }
                    }, 0, value);
                }
            });

            isReadingStarted = true;
            t1.start();

        }



    }
}
