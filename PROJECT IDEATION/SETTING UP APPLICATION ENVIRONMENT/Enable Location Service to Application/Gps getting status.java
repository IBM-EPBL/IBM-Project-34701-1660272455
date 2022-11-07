new GpsUtils(this).turnGPSOn(new GpsUtils.onGpsListener() {
    @Override
    public void gpsStatus(boolean isGPSEnable) {
        // turn on GPS
        isGPS = isGPSEnable;
    }
});