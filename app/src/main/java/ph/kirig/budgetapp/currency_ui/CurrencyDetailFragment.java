/*
 * Copyright (c) 2019 Kirig Technologies. All rights reserved.
 *
 * This document is considered proprietary and confidential. It may not be stored, reproduced,
 * or transmitted by any means without express written permission from Kirig Technologies.
 */

package ph.kirig.budgetapp.currency_ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

import ph.kirig.budgetapp.R;
import ph.kirig.budgetapp.models.Currency;
import ph.kirig.budgetapp.persistence.room.BudgetDb;
import ph.kirig.budgetapp.persistence.room.CurrencyDao;

/**
 * A fragment representing a single Currency detail screen.
 * This fragment is either contained in a {@link CurrencyListActivity}
 * in two-pane mode (on tablets) or a {@link CurrencyDetailActivity}
 * on handsets.
 */
public class CurrencyDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Currency mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */

    private BudgetDb db;

    public CurrencyDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null && getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.

            db = BudgetDb.getInstance(getActivity());
            CurrencyDao localRepository = db.currencyDao();

            List<Currency> currencyList
                    = localRepository.get(getArguments().getString(ARG_ITEM_ID));

            mItem = currencyList.get(0);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getFullName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.currency_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.currency_detail)).setText(mItem.getAbbreviation());
        }

        return rootView;
    }
}
