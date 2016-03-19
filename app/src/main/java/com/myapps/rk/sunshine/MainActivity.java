package com.myapps.rk.sunshine;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "Inside onCreate()");
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Context context = getApplicationContext();
            Toast makeToast = Toast.makeText(context, "Settings Menu", Toast.LENGTH_SHORT);
            makeToast.show();

            Intent intent = new Intent();
            intent.setClass(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_map) {
            Context context = getApplicationContext();
            Toast makeToast = Toast.makeText(context, "Location Menu", Toast.LENGTH_SHORT);
            makeToast.show();

            openPreferedLocationInMap();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openPreferedLocationInMap(){
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String location = sharedPrefs.getString(getString(R.string.pref_location_key),
                getString(R.string.pref_location_default));
        Uri geoLocation = Uri.parse("geo:0,0?").buildUpon()
                .appendQueryParameter("q", location)
                .build();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else {
            Log.d(LOG_TAG, "Could not call " + location);
        }
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Log.d(LOG_TAG, "Inside onStart()");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d(LOG_TAG, "Inside onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(LOG_TAG, "Inside onPause()");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d(LOG_TAG, "Inside onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "Inside onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "Inside onRestart()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d(LOG_TAG, "Inside onSaveInstanceState()");
        outState.putString("SAMPLE_DATA", "sampleString");
    }

    @Override
    protected void onRestoreInstanceState(Bundle inState){
        super.onRestoreInstanceState(inState);
        Log.d(LOG_TAG, "Inside onRestoreInstanceState()");
        String sampleData = inState.getString("SAMPLE_DATA");
        Log.d(LOG_TAG, "SAMPLE_DATA = " +sampleData);
    }
}
