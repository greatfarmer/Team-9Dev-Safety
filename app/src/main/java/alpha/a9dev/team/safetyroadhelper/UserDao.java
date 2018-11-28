package alpha.a9dev.team.safetyroadhelper;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user where id = (:id)")
    List<User> getUserNum(int id);

    @Query("SELECT * FROM user where user_id = (:user_id) and password = (:password)")
    User getUser(String user_id, String password);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}
