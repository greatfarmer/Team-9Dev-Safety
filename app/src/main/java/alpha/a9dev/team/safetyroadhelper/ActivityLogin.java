package alpha.a9dev.team.safetyroadhelper;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityLogin extends AppCompatActivity {
    private EditText user_id;
    private EditText password;
    private Button login;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences test = getSharedPreferences("test", MODE_PRIVATE);
        final SharedPreferences.Editor editor = test.edit();
        user_id = (EditText)findViewById(R.id.id);
        password = (EditText)findViewById(R.id.pwd);
        login = (Button)findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId =  user_id.getText().toString();
                String pwd = password.getText().toString();
                editor.putString("user_id", userId);
                editor.putString("password", pwd);
                editor.commit();
                user.setId(1);
                user.setUserId(userId);
                user.setPassword(pwd);

            }
        });
    }
}
