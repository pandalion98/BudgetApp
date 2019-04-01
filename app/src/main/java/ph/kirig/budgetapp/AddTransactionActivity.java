package ph.kirig.budgetapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import androidx.appcompat.app.AppCompatActivity;
import io.objectbox.Box;
import ph.kirig.budgetapp.models.Account;
import ph.kirig.budgetapp.models.TransactionRecord;

public class AddTransactionActivity extends AppCompatActivity {

    private Box<TransactionRecord> txBox;
    private EditText etAmount, etNote;
    private Spinner spinnerAccount;
    private Button btnCommitTx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        txBox = ObjectBoxHelper.get().boxFor(TransactionRecord.class);

        etAmount = findViewById(R.id.edittext_tx_amt);
        etNote = findViewById(R.id.edittext_tx_note);
        spinnerAccount = findViewById(R.id.spinner_account);
        btnCommitTx = findViewById(R.id.btn_commit_tx);

        btnCommitTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commitTx();
            }
        });
    }

    private void commitTx() {
        Toast.makeText(this, UUID.randomUUID().toString(), Toast.LENGTH_SHORT).show();
        TransactionRecord transactionRecord = new TransactionRecord();

        transactionRecord.setOwnerAccount(((Account) spinnerAccount.getSelectedItem()).getAccountUuid());
        transactionRecord.setTxAmount(new BigDecimal(etAmount.getText().toString()));

        txBox.put(transactionRecord);
    }

    private class AccountAdapter extends BaseAdapter implements SpinnerAdapter {
        private Context ctx;
        private Box<Account> acctBox; // Should be read-only in this context
        private List<Account> accountList;

        public AccountAdapter(Context context) {
            ctx = context;
            acctBox = ObjectBoxHelper.get().boxFor(Account.class);
            accountList = acctBox.getAll();
        }

        @Override
        public int getCount() {
            return accountList.size();
        }

        @Override
        public Object getItem(int position) {
            return accountList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return accountList.get(position).id;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                // attachToRoot should be false for list items.
                convertView = LayoutInflater.from(ctx).inflate(R.layout.tx_spinner_accounts, parent, false);

                holder = new ViewHolder();
                holder.accountName = convertView.findViewById(R.id.title);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.accountName.setText(accountList.get(position).accountName);
            return convertView;
        }

        private class ViewHolder {
            TextView accountName;
        }
    }
}
