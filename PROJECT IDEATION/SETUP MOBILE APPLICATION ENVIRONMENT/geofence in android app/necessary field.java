<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.WAKE_LOCK" />
    <uses-permission android:name="android.permission.ACCESS_BACKGROUND_LOCATION" />
    <uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
 
 
 
 
 <meta-data
            android:name="com.google.android.geo.API_KEY"
            android:value="@string/google_maps_key" />
 <receiver
            android:name=".MyBroadCastReceiver"
            android:allowBackup="true"
            android:directBootAware="true"
            android:enabled="true"
            android:exported="false">

          
        </receiver>
         <service
            android:name=".LocationService"
            android:enabled="true"
            android:exported="false" />