package alpha.a9dev.team.safetyroadhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class ActivitySplash extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startMainActivity();
        finish();
    }

    private void startMainActivity() {
        startActivity(new Intent(this, ActivityMain.class));
    }
}
