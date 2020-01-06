package com.illicitintelligence.mypermissionsapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 707;

    @BindView(R.id.main_text)
    TextView mainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission();
        }

    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_SMS},
                REQUEST_CODE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE) {
//            Log.d("TAG_X", "Request code match");
//
//            if (permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION) &&
//                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(this, "Location permission was granted.", Toast.LENGTH_SHORT).show();
//            }
//            else if(permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION)){
//
//                if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
//                    requestLocationPermission();
//                }
//                else {
//                    Toast.makeText(this, "Show manual tutorial...", Toast.LENGTH_SHORT).show();
//                    mainText.setText("Permission must be granted from phone settings.");
//
//                    mainText.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            Intent settingsIntent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                            Uri uri = Uri.fromParts("package", getPackageName(), null);
//                            settingsIntent.setData(uri);
//                            startActivity(settingsIntent);
//                        }
//                    });
//
//                }
//
//

            for (int index = 0; index < permissions.length; index++) {


//                Location Permission
                if (permissions[index].equals(Manifest.permission.ACCESS_FINE_LOCATION) && grantResults[index] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Location permission was granted.", Toast.LENGTH_SHORT).show();
                } else if (permissions[index].equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION))
                        requestLocationPermission();
                    else
                        mainText.setText("User will have to manually enable location permission.");
                }


//                Read SMS permissions
                if (permissions[index].equals(Manifest.permission.READ_SMS) && grantResults[index] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "SMS permission was granted.", Toast.LENGTH_SHORT).show();
                } else if (permissions[index].equals(Manifest.permission.READ_SMS)) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS))
                        requestLocationPermission();
                    else
                        mainText.setText("User will have to manually enable SMS permission.");
                }
            }

        }

    }
}
