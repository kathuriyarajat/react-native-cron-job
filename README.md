# react-native-cron-job

## Getting started

`$ npm install react-native-cron-job --save`

### Mostly automatic installation

`$ react-native link react-native-cron-job`

## Usage

This package allows you to create cron job locally in your react-native app. You can schedule your task everyday at a particular time. The Cron job will get executed everyday at the given time even if the app is closed.

# index.js
Add the below code in your index.js file.

```javascript
import CronJob from "react-native-cron-job";
const CronJobTask = async () => {

      // Do your task here.

      // Be sure to call completeTask at the end.
      CronJob.completeTask();
};
AppRegistry.registerHeadlessTask('CRONJOB', () => CronJobTask);
AppRegistry.registerComponent(appName, () => App);
```



# AndroidManifest.xml

In your AndroidManifest.xml file add the below code in application tag -
```javascript

        <service
            android:name="com.cronjob.CJService"
            android:enabled="true"
            android:exported="false" >
        </service>

        <service
            android:name="com.cronjob.CJEventService">
        </service>

        <receiver
            android:name="com.cronjob.BootReceiver"
            android:enabled="true"
            android:permission="android.permission.RECEIVE_BOOT_COMPLETED">
            <intent-filter>
                <action android:name="android.intent.action.BOOT_COMPLETED" />
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </receiver>

```

Additionally add below permissions in AndroidManifest.xml - 
```javascript
    <uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
    <uses-permission android:name="android.permission.WAKE_LOCK" />
```


# Start Service
To start cron job just execute the below code. Below function takes 2 integer arguments which is hour and minute (the time at which cronjob starts everyday even if the app is closed).
place this code anywhere where you want to start your task.
Time range is 0 <= hour <= 23  ,  0 <= minute <= 59 

```javascript
CronJob.startCronJob(22,12); // starts cronjob everyday at 10:12 PM
```
