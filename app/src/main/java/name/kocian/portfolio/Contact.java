package name.kocian.portfolio;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;

/**
 * Contacts
 *
 * @author Ing. Michal Kocian
 */
public enum Contact {
    /**
     * ID
     * Contact value
     * Contact icon
     * Action intent
     */
    PHONE_UK(
            0,
            R.string.contact_phone_uk,
            R.drawable.ic_contact_phone,
            Contact.getIntentCallUk()
    ),
    PHONE_CZ(
            1,
            R.string.contact_phone_cz,
            R.drawable.ic_contact_phone,
            Contact.getIntentCallCz()
    ),
    EMAIL(
            2,
            R.string.contact_email,
            R.drawable.ic_contact_email,
            Contact.getIntentSendEmail()
    ),
    WEB(
            3,
            R.string.contact_web,
            R.drawable.ic_contact_web,
            Contact.getIntentWeb()
    ),
    SKYPE(
            4,
            R.string.contact_skype,
            R.drawable.ic_contact_skype,
            Contact.getIntentSkype()
    );

    /**
     * Contact unique ID
     */
    private final int mId;

    /**
     * Contact name resource
     */
    private final int mValueResource;

    /**
     * OnClickListener
     */
    private final Intent mIntent;

    /**
     * Contact icon resource
     */
    private final int mIconResource;

    /**
     * Constructor
     *
     * @param id            Contact ID
     * @param valueResource Value
     * @param iconResource  Icon
     * @param intent        Intent
     */
    Contact(int id, int valueResource, int iconResource, Intent intent) {
        mId = id;
        mValueResource = valueResource;
        mIconResource = iconResource;
        mIntent = intent;
    }

    public int getId() {
        return mId;
    }

    public int getValueResource() {
        return mValueResource;
    }

    public int getIconResource() {
        return mIconResource;
    }

    public Intent getIntent() {
        return mIntent;
    }

    /**
     * Factory - get Contact from ID
     *
     * @param id Contact ID
     * @return Contact
     */
    public static Contact factory(int id) {
        for (Contact contact : values()) {

            if (contact.getId() == id) {
                return contact;
            }
        }

        return null;
    }

    /**
     * Call on phone
     *
     * @return Intent
     */
    private static Intent getIntentCallUk() {

        // TODO Move hardcoded value to strings
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:00447958493981"));

        return intent;
    }

    /**
     * Call on phone
     *
     * @return Intent
     */
    private static Intent getIntentCallCz() {

        // TODO Move hardcoded value to strings
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:00420737320927"));

        return intent;
    }

    /**
     * Send an e-mail
     *
     * @return Intent
     */
    private static Intent getIntentSendEmail() {

        // TODO Move hardcoded values to strings
        Intent intent = new Intent(new Intent(Intent.ACTION_SEND));
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"michal@kocian.name"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Android");
        intent.putExtra(Intent.EXTRA_TEXT, "Hi Michal, ");

        return intent;
    }

    /**
     * Open web page
     *
     * @return Intent
     */
    private static Intent getIntentWeb() {

        // TODO Move hardcoded values to strings
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://kocian.name"));

        return intent;
    }

    /**
     * Call on Skype
     *
     * @return Intent
     */
    private static Intent getIntentSkype() {

        // TODO Move hardcoded values to strings
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("skype:michal.kocian?call"));
        intent.setComponent(new ComponentName("com.skype.raider", "com.skype.raider.Main"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        return intent;
    }

    /**
     * Add contact
     *
     * @return Intent
     */
    public static Intent getIntentAddContact() {

        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);

        // TODO Move hardcoded values to strings
        intent.putExtra(ContactsContract.Intents.Insert.NAME, "Michal Kocian");
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, "+447958493981");
        intent.putExtra(ContactsContract.Intents.Insert.SECONDARY_PHONE, "+420737320927");
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, "michal@kocian.name");
        intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, "Android developer");

        return intent;
    }
}
