package alpha.a9dev.team.safetyroadhelper;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class ActivityMain extends AppCompatActivity {
    public static final int SELECT_USER = 0;
    public static final int NON_SELECT_USER = 1;
    public static Handler mHandler = null;
    AppDatabase db = null;
    String id = "";
    String pwd = "";
    User user1 = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences test = getSharedPreferences("test", MODE_PRIVATE);
                id = test.getString("user_id", "");
                pwd = test.getString("password", "");
                db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "database-name").build();
                User user = new User();
                user.setId(1);
                user.setUserId(id);
                user.setPassword(pwd);
                db.userDao().insertAll(user);
                user1 = db.userDao().getUser(id, pwd);
                Message msg = mHandler.obtainMessage(SELECT_USER);
                if(user1 != null){
                    msg.what = SELECT_USER;
                } else {
                    msg.what = NON_SELECT_USER;
                }
                mHandler.sendMessage(msg);
            }
        });
        t.start();

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                switch (msg.what){
                    case SELECT_USER :
                        Toast.makeText(ActivityMain.this, user1.getId() + "님이 로그인하셨습니다.",Toast.LENGTH_SHORT).show();
                        break;
                    case NON_SELECT_USER :
                        startActivity(new Intent(ActivityMain.this, ActivityLogin.class));
                        break;
                    default:
                        break;
                }
            }
        };
    }



}
