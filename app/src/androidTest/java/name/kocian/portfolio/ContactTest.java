package name.kocian.portfolio;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import name.kocian.portfolio.ui.activity.MainActivity;

/**
 * Contacts screen
 *
 * @author Michal Kocian
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ContactTest {

    /**
     * Rule
     */
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void initSample() {

        // InstrumentationRegistry.getContext();
    }

    /**
     * Check contacts
     */
    @Test
    public void contactsVisibility() {

        // TODO Select contacts
        // TODO Check assignments
    }
}
