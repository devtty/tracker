package devtty.com.tracker.room;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface AccountDao {

    @Query("SELECT * FROM account")
    LiveData<List<Account>> getAll();

    @Insert
    void insertAll(Account... accounts);

    @Delete
    void delete(Account account);

}
