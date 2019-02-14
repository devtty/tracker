package devtty.com.tracker.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Account.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract AccountDao accountDao();

    private static volatile AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (AppDatabase.class){
                if (INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database").addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{
        private final AccountDao mDao;

        PopulateDbAsync(AppDatabase db){
            mDao = db.accountDao();
        }

        @Override
        protected Void doInBackground(final Void... params){
            mDao.deleteAll();
            Account account = new Account();
            mDao.insertAll(account);
            account = new Account();
            mDao.insertAll(account);
            return null;
        }

    }

}
