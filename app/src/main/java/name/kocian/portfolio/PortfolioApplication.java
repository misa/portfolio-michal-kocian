package name.kocian.portfolio;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import java.util.HashMap;

/**
 * Portfolio application
 *
 * @author Michal Kocian
 */
public class PortfolioApplication extends Application {

    /**
     * Google Analytic tracker
     */
    private final HashMap<TrackerName, Tracker> mTrackers = new HashMap<>();

    /**
     * Enum used to identify the tracker that needs to be used for tracking.
     *
     * Tracker used only in this app.
     */
    public enum TrackerName {
        APP_TRACKER
    }

    /**
     * Google Analytics tracker initialization
     *
     * @param trackerId Tracker ID
     * @return Tracker
     */
    public synchronized Tracker getTracker(TrackerName trackerId) {

        if (!mTrackers.containsKey(trackerId)) {
            GoogleAnalytics.getInstance(this).setDryRun(BuildConfig.ANALYTICS_DRY_RUN);
            GoogleAnalytics.getInstance(this).getLogger().setLogLevel(BuildConfig.ANALYTICS_LOG_LEVEL);

            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            Tracker tracker = analytics.newTracker(R.xml.tracker);
            mTrackers.put(trackerId, tracker);
        }

        return mTrackers.get(trackerId);
    }
}
