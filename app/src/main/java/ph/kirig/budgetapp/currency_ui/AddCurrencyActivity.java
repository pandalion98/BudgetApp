/*
 * Copyright (c) 2019 Kirig Technologies. All rights reserved.
 *
 * This document is considered proprietary and confidential. It may not be stored, reproduced,
 * or transmitted by any means without express written permission from Kirig Technologies.
 */

package ph.kirig.budgetapp.currency_ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import ph.kirig.budgetapp.R;
import ph.kirig.budgetapp.models.Currency;
import ph.kirig.budgetapp.persistence.local.CurrencyLocalRepo;

public class AddCurrencyActivity extends AppCompatActivity {

    private TextView lblCurrDecimals;
    private EditText currDispName, currAbbrev, currSymb;
    private SeekBar seekCurrDecimals;
    private Button btnCommitCurr;

    private CurrencyLocalRepo repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_currency);

        repository = new CurrencyLocalRepo(AddCurrencyActivity.this);

        lblCurrDecimals = findViewById(R.id.label_decimals_selected);
        currDispName = findViewById(R.id.edittext_currency_name);
        currAbbrev = findViewById(R.id.edittext_currency_abbrev);
        currSymb = findViewById(R.id.edittext_currency_symbol);
        seekCurrDecimals = findViewById(R.id.seekbar_decimalplaces);
        btnCommitCurr = findViewById(R.id.btn_commit_curr);

        lblCurrDecimals.setText(String.valueOf(seekCurrDecimals.getProgress()));
        seekCurrDecimals.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String progressString = String.valueOf(progress);
                lblCurrDecimals.setText(progressString);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnCommitCurr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCurrency(
                        currDispName.getText().toString(),
                        currAbbrev.getText().toString(),
                        currSymb.getText().toString(),
                        seekCurrDecimals.getProgress()
                );
            }
        });
    }

    private void createCurrency(String currFullName, String abbrv, String symb, int scale) {
        if (currFullName.trim().isEmpty()) {
            toasty(getString(R.string.toast_curr_name_empty));
            return;
        }

        if (abbrv.trim().isEmpty()) {
            toasty(getString(R.string.toast_curr_abbr_empty));
            return;
        }

        if (symb.trim().isEmpty()) {
            toasty(getString(R.string.toast_curr_symb_empty));
            return;
        }

        Currency c = new Currency();
        c.setFullName(currFullName.trim());
        c.setAbbreviation(abbrv.trim());
        c.setSymbol(symb.trim());
        c.setNumericScale(scale);

        repository.add(c);
    }


    private void toasty(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void toasty(String message, int length) {
        Toast.makeText(this, message, length).show();
    }
}
