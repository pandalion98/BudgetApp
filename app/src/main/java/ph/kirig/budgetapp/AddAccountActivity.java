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
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import ph.kirig.budgetapp.models.Account;
import ph.kirig.budgetapp.models.Currency;
import ph.kirig.budgetapp.persistence.local.AccountLocalRepo;
import ph.kirig.budgetapp.persistence.local.CurrencyLocalRepo;
import ph.kirig.budgetapp.persistence.local.CurrencyQuery;

public class AddAccountActivity extends AppCompatActivity {

    private TextInputEditText accountName;
    private Spinner spinnerCurrency;
    private Button btnCommitAcct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        accountName = findViewById(R.id.edittext_account_name);
        spinnerCurrency = findViewById(R.id.spinner_account_select);
        btnCommitAcct = findViewById(R.id.btn_commit_acct);

        CurrencyLocalRepo cclr = new CurrencyLocalRepo(AddAccountActivity.this);
        final List<Currency> currencies = cclr.query(new CurrencyQuery());

        CurrencyAdapter currencyAdapter =
                new CurrencyAdapter(AddAccountActivity.this, currencies);

        spinnerCurrency.setAdapter(currencyAdapter);


        btnCommitAcct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount(accountName.getText().toString().trim(),
                        (Currency) spinnerCurrency.getSelectedItem());
            }
        });
    }

    private void createAccount(String accountName, Currency currency) {
        if (accountName.trim().isEmpty()) {
            toasty(getString(R.string.toast_acct_name_blank));
            return;
        }

        if (currency == null) {
            toasty(getString(R.string.toast_acct_curr_blank));
            return;
        }

        Account account = new Account();
        account.setName(accountName);
        account.setCurrencyUuid(currency.getUuid());

        AccountLocalRepo aclr = new AccountLocalRepo(AddAccountActivity.this);
        aclr.add(account);
    }

    private void toasty(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    private class CurrencyAdapter extends BaseAdapter implements SpinnerAdapter {
        private Context ctx;
        private List<Currency> currencyList;

        public CurrencyAdapter(Context context, List<Currency> currencies) {
            ctx = context;
            currencyList = currencies;
        }

        @Override
        public int getCount() {
            return currencyList.size();
        }

        @Override
        public Object getItem(int position) {
            return currencyList.get(position);
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
                convertView = LayoutInflater.from(ctx)
                        .inflate(R.layout.acct_spinner_accounts, parent, false);

                holder = new ViewHolder();
                holder.currencyText = convertView.findViewById(R.id.tv_account);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.currencyText.setText(currencyList.get(position).getFullName());
            return convertView;
        }

        private class ViewHolder {
            TextView currencyText;
        }
    }
}
