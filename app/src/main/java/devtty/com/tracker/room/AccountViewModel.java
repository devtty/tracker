package devtty.com.tracker.room;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class AccountViewModel extends AndroidViewModel {

    private AccountRepository mRepository;

    private LiveData<List<Account>> mAllAccounts;

    public AccountViewModel(Application application){
        super(application);
        mRepository = new AccountRepository(application);
        mAllAccounts = mRepository.getAllAccounts();
    }

    LiveData<List<Account>> getAllAccounts(){ return mAllAccounts; }

    public void insert(Account account) { mRepository.insert(account); }

}
