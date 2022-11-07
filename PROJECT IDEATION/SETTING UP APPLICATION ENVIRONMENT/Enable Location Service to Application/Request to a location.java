@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == Activity.RESULT_OK) {
        if (requestCode == AppConstants.GPS_REQUEST) {
            isGPS = true; // flag maintain before get location
        }
    }
}
