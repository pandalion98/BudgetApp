/*
 * Copyright (c) 2019 Kirig Technologies. All rights reserved.
 *
 * This document is considered proprietary and confidential. It may not be stored, reproduced,
 * or transmitted by any means without express written permission from Kirig Technologies.
 */

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
import ph.kirig.budgetapp.models.Account;
import ph.kirig.budgetapp.models.TransactionRecord;
import ph.kirig.budgetapp.persistence.local.AccountLocalRepository;
import ph.kirig.budgetapp.persistence.local.AccountQuery;

public class AddTransactionActivity extends AppCompatActivity {

    private EditText etAmount, etNote;
    private Spinner spinnerAccount;
    private Button btnCommitTx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        etAmount = findViewById(R.id.edittext_tx_amt);
        etNote = findViewById(R.id.edittext_tx_note);
        spinnerAccount = findViewById(R.id.spinner_account_select);
        btnCommitTx = findViewById(R.id.btn_commit_tx);

        btnCommitTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commitTx();
            }
        });

        AccountLocalRepository aclr = new AccountLocalRepository(AddTransactionActivity.this);
        final List<Account> accounts = aclr.query(new AccountQuery());

        AccountAdapter accountAdapter =
                new AccountAdapter(AddTransactionActivity.this, accounts);

        spinnerAccount.setAdapter(accountAdapter);
    }

    private void commitTx() {
        Toast.makeText(this, UUID.randomUUID().toString(), Toast.LENGTH_SHORT).show();
        TransactionRecord transactionRecord = new TransactionRecord();

        transactionRecord.setOwnerAccount(((Account) spinnerAccount.getSelectedItem()).getUuid());
        transactionRecord.setTxAmount(new BigDecimal(etAmount.getText().toString()));

    }

    private class AccountAdapter extends BaseAdapter implements SpinnerAdapter {
        private Context ctx;
        private List<Account> accountList;

        public AccountAdapter(Context context, List<Account> accountList) {
            ctx = context;
            this.accountList = accountList;
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
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                // attachToRoot should be false for list items.
                convertView = LayoutInflater.from(ctx).inflate(R.layout.tx_spinner_accounts, parent, false);

                holder = new ViewHolder();
                holder.accountName = convertView.findViewById(R.id.tv_account);

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
