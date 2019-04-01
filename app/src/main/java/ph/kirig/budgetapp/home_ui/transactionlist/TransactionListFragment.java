package ph.kirig.budgetapp.home_ui.transactionlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import ph.kirig.budgetapp.R;

public class TransactionListFragment extends Fragment {

    private TransactionListViewModel mViewModel;

    public static TransactionListFragment newInstance() {
        return new TransactionListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tx_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TransactionListViewModel.class);
        // TODO: Use the ViewModel
    }

}
