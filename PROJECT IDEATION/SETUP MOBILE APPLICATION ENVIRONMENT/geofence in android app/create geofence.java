private List<Geofence> geofenceList = new ArrayList<>();
private PendingIntent geofencePendingIntent;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        
         geofenceList.add(new Geofence.Builder()
                    // Set the request ID of the geofence. This is a string to identify this
                    // geofence.
                    .setRequestId("unique id")
                    .setCircularRegion(
                     Double.parseDouble(locationModel.getLat()),// lat
                     Double.parseDouble(locationModel.getLon()),// lng
                    (float) radius)// add the radius in float.
                    .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER)
                    .setExpirationDuration(Geofence.NEVER_EXPIRE)
                    .setNotificationResponsiveness(1000)
                    .build());