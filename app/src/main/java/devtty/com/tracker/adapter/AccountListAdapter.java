package devtty.com.tracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import devtty.com.tracker.R;
import devtty.com.tracker.room.Account;

public class AccountListAdapter extends RecyclerView.Adapter<AccountListAdapter.AccountViewHolder> {

    class AccountViewHolder extends RecyclerView.ViewHolder{
        private final TextView accountItemView;

        private AccountViewHolder(View accountView){
            super(accountView);
            accountItemView = accountView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Account> mAccounts;

    public AccountListAdapter(Context context){ mInflater = LayoutInflater.from(context); }

    @Override
    public AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new AccountViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AccountViewHolder holder, int position){
        if(mAccounts != null){
            Account current = mAccounts.get(position);
            holder.accountItemView.setText(current.getName());

        }else{
            holder.accountItemView.setText("No account");
        }
    }

    public void setAccounts(List<Account> accounts){
        mAccounts = accounts;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if(mAccounts != null){
            return mAccounts.size();
        }else
            return 0;
    }

}
