package name.kocian.portfolio.ui.activity;

import android.annotation.TargetApi;
import android.graphics.Outline;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.BaseDrawerItem;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import name.kocian.portfolio.Contact;
import name.kocian.portfolio.PortfolioApplication;
import name.kocian.portfolio.R;
import name.kocian.portfolio.ui.fragment.ContactFragment;

/**
 * Main activity - contact
 *
 * @author Michal Kocian
 */
public class MainActivity extends AppCompatActivity implements Drawer.OnDrawerItemClickListener {

    /**
     * Create activity
     *
     * @param savedInstanceState Saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Contact fragment
        Fragment fragment = new ContactFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frContent, fragment);
        fragmentTransaction.commit();

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.person_name);
        toolbar.setSubtitle(R.string.person_position);
        setSupportActionBar(toolbar);
        // TODO Collapse when scrolling

        // Navigation drawer header
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.primary)
                .withSelectionListEnabled(false)
                .addProfiles(
                        new ProfileDrawerItem().withName(getString(R.string.person_name))
                                .withEmail("michal@kocian.name").withIcon(getResources().getDrawable(R.drawable.michal_kocian))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();
        // TODO Drawer header background image

        // Navigation drawer
        new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.app_spotify).withIcon(FontAwesome.Icon.faw_play_circle),
                        new PrimaryDrawerItem().withName(R.string.app_scores).withIcon(FontAwesome.Icon.faw_list),
                        new PrimaryDrawerItem().withName(R.string.app_library).withIcon(FontAwesome.Icon.faw_book),
                        new PrimaryDrawerItem().withName(R.string.app_bigger).withIcon(FontAwesome.Icon.faw_building),
                        new PrimaryDrawerItem().withName(R.string.app_reader).withIcon(FontAwesome.Icon.faw_barcode),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName(R.string.app_my).withIcon(FontAwesome.Icon.faw_certificate)
                )
                .withOnDrawerItemClickListener(this)
                .build();

        // FAB button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddContact);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Contact.getIntentAddContact());
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fab.setOutlineProvider(new ViewOutlineProvider() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void getOutline(View view, Outline outline) {
                    int diameter = getResources().getDimensionPixelSize(R.dimen.diameter);
                    outline.setOval(0, 0, diameter, diameter);
                }
            });
        }

        // Get tracker.
        Tracker tracker = ((PortfolioApplication) getApplication()).getTracker(PortfolioApplication.TrackerName.APP_TRACKER);
        tracker.setScreenName(this.getClass().getSimpleName());
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    /**
     * Drawer item click
     *
     * @param adapterView Adapter view
     * @param view        View
     * @param position    Position
     * @param id          ID
     * @param drawerItem  Drawer item
     * @return Completed
     */
    @Override
    public boolean onItemClick(AdapterView<?> adapterView, View view, int position, long id, IDrawerItem drawerItem) {

        Toast.makeText(MainActivity.this, "Coming soon: " + getString(((BaseDrawerItem) drawerItem).getNameRes()), Toast.LENGTH_SHORT).show();
        return true;
    }
}
