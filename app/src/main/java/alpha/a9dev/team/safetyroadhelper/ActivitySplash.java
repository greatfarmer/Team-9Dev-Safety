package alpha.a9dev.team.safetyroadhelper;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class ActivitySplash extends AppCompatActivity {
    private  boolean locationPermission = false;
    private  boolean AlarmPermission = false;
    private  boolean CallPermission = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            locationPermission = true;
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SET_ALARM) == PackageManager.PERMISSION_GRANTED){
            AlarmPermission = true;
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
            CallPermission = true;
        }


        if(!locationPermission || !AlarmPermission || !CallPermission) {
            Intent intent = new Intent(this, ActivityPermission.class);
            intent.putExtra("location", locationPermission);
            intent.putExtra("alarm", AlarmPermission);
            intent.putExtra("call", CallPermission);
            startActivityForResult(intent, 10);
        } else {
            startMainActivity();
            finish();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            String result = data.getStringExtra("result");
            Log.d("debug", "onActivityResult: " + result);
            startMainActivity();
            finish();
        }
    }

    private void startMainActivity() {
        startActivity(new Intent(this, ActivityMain.class));
    }
}
