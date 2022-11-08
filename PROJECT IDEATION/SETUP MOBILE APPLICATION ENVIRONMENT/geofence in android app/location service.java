public class LocationService extends Service {

 @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
 @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
     Intent notificationIntent = new Intent(this, MapsActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Location Alarm")
                .setContentText("You reached the location.")
                .setSmallIcon(R.drawable.ic_add_location_black_24dp)
                .setContentIntent(pendingIntent)
                .build();

        new DownloadFilesTask().execute();

        startForeground(1, notification);
        //do heavy work on a background thread
        //stopSelf();
        return START_NOT_STICKY;
    }
    @SuppressLint("StaticFieldLeak")
    private class DownloadFilesTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            getLocation();
            return null;
        }

    }

}