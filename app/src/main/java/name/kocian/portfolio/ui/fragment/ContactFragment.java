package name.kocian.portfolio.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import name.kocian.portfolio.R;
import name.kocian.portfolio.ui.adapter.ContactsAdapter;

/**
 * Contact fragment
 *
 * @author Michal Kocian
 */
public class ContactFragment extends Fragment {

    /**
     * Create fragment
     *
     * @param savedInstanceState Saved state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    /**
     * Create view
     *
     * @param inflater           Inflater
     * @param container          Container
     * @param savedInstanceState State
     * @return View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);

        // Initialize contact list
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        ContactsAdapter adapter = new ContactsAdapter(getActivity());
        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rvContacts);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);

        return rootView;
    }

    /**
     * Create action menu
     *
     * @param menu     Menu
     * @param inflater Inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.contact, menu);
    }

    /**
     * Option item selected
     *
     * @param item Item
     * @return Action consumed
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_about:

                // About dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                        .setTitle(getString(R.string.dialog_about_title))
                        .setMessage(getString(R.string.dialog_about_message))
                        .setPositiveButton(getString(R.string.dialog_about_button_ok), null);
                builder.show();
                // TODO Add few words about me

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
