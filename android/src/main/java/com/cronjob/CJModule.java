package com.cronjob;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;

import com.facebook.react.bridge.ReactApplicationContext;

import com.facebook.react.bridge.ReactContextBaseJavaModule;

import com.facebook.react.bridge.ReactMethod;

import android.util.Log;
import android.widget.Toast;

import javax.annotation.Nonnull;
import static android.content.Context.ALARM_SERVICE;
import android.content.SharedPreferences;
import android.content.Context;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.text.DateFormat;

public class CJModule extends ReactContextBaseJavaModule {

   public static final String REACT_CLASS = "CronJob";

   private static ReactApplicationContext reactContext;

   public CJModule(@Nonnull ReactApplicationContext reactContext) {

       super(reactContext);

       this.reactContext = reactContext;

   }

   @Nonnull

   @Override

   public String getName() {

       return REACT_CLASS;

   }

   
   @ReactMethod

   public void startCronJob(int hour,int minute) {

       try{
           SharedPreferences sharedPreferences = this.reactContext.getSharedPreferences("CJ",Context.MODE_PRIVATE);
           SharedPreferences.Editor myEdit = sharedPreferences.edit();
           myEdit.putInt("cj_hour", hour);
           myEdit.putInt("cj_minute", minute);
           myEdit.commit();

           // Get current date
           Date c = Calendar.getInstance().getTime();
           SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
           String currentDate = df.format(c);
           currentDate+=" ";
           if(hour<10){
               currentDate+="0";
           }
           currentDate+=hour;
           currentDate+=":";
           if(minute<10){
               currentDate+="0";
           }
           currentDate+=minute;
           currentDate+=":00";
           DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
           Date date = (Date)formatter.parse(currentDate);
           long serviceStartTime=date.getTime();
           if(serviceStartTime < System.currentTimeMillis()){
               serviceStartTime+= 1000*60*60*24;
           }

           Intent newIntent = new Intent(this.reactContext, BootReceiver.class);
           PendingIntent pendingIntent = PendingIntent.getBroadcast(
                   this.reactContext.getApplicationContext(), 234324243, newIntent, 0);
           AlarmManager alarmManager = (AlarmManager) this.reactContext.getSystemService(ALARM_SERVICE);
           alarmManager.set(AlarmManager.RTC_WAKEUP, serviceStartTime, pendingIntent);


       }
       catch (Exception e){

       }


   }


   @ReactMethod

   public void completeTask() {

       this.reactContext.stopService(new Intent(this.reactContext, CJService.class));

   }

}