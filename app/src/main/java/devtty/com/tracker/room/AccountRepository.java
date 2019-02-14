package devtty.com.tracker.room;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class AccountRepository {

    private AccountDao mAccountDao;
    private LiveData<List<Account>> mAllAccounts;

    AccountRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        mAccountDao = db.accountDao();
        mAllAccounts = mAccountDao.getAll();
    }

    LiveData<List<Account>> getAllAccounts(){
        return mAllAccounts;
    }

    public void insert(Account account){
        new insertAsyncTask(mAccountDao).execute(account);
    }

    private static class insertAsyncTask extends AsyncTask<Account, Void, Void>{
        private AccountDao mAsyncTaskDao;

        insertAsyncTask(AccountDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Account... params){
            mAsyncTaskDao.insertAll(params[0]);
            return null;
        }
    }
}
