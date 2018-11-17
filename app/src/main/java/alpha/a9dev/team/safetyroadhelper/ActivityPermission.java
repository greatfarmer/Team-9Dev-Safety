package alpha.a9dev.team.safetyroadhelper;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class ActivityPermission extends Activity {
    private  boolean locationPermission = false;
    private  boolean AlarmPermission = false;
    private  boolean CallPermission = false;
    private  String[] pArr = new String[3];
    private Button btn_permission;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_permission);
        Log.d("info","permission_page!!!");

        Intent intent = getIntent();
        if(!intent.getBooleanExtra("location",false)){
            pArr[0] = Manifest.permission.ACCESS_FINE_LOCATION;
        }
        if(!intent.getBooleanExtra("alarm",false)){
            pArr[1] = Manifest.permission.SET_ALARM;
        }
        if(!intent.getBooleanExtra("call",false)){
            pArr[2] = Manifest.permission.CALL_PHONE;
        }
        btn_permission = (Button)findViewById(R.id.btn_permission);
        btn_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("user", "onClick: btn_permission!!!");
                ActivityCompat.requestPermissions(ActivityPermission.this, pArr
                        ,200);
                Intent intent = new Intent();
                intent.putExtra("result", "데이터 넘어감!!");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 200 && grantResults.length > 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                locationPermission = true;
            }
            if(grantResults[1] == PackageManager.PERMISSION_GRANTED){
                AlarmPermission = true;
            }
            if(grantResults[2] == PackageManager.PERMISSION_GRANTED){
                CallPermission = true;
            }
        }
    }
}
