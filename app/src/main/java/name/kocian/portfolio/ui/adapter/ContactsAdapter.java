package name.kocian.portfolio.ui.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import name.kocian.portfolio.Contact;
import name.kocian.portfolio.R;

/**
 * Contract adapter
 *
 * @author Ing. Michal Kocian
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    /**
     * Context
     */
    private final Context mContext;

    /**
     * Constructor
     *
     * @param context Context
     */
    public ContactsAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_contact, viewGroup, false);

        return new ViewHolder(view);
    }

    /**
     * View binding
     *
     * @param viewHolder View holder
     * @param position   Item position
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        final Contact contact = Contact.factory(position);

        if (contact != null) {
            viewHolder.getTextView().setText(contact.getValueResource());
            viewHolder.getTextView().setCompoundDrawablesWithIntrinsicBounds(contact.getIconResource(), 0, 0, 0);
            viewHolder.getTextView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent intent = contact.getIntent();

                    switch (contact.getId()) {
                        case 0:
                            // Call confirmation dialog
                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext)
                                    .setTitle(mContext.getString(R.string.dialog_call_title))
                                    .setMessage(mContext.getString(R.string.dialog_call_message_uk))
                                    .setNegativeButton(mContext.getString(android.R.string.cancel), null)
                                    .setNeutralButton(mContext.getString(R.string.dialog_call_copy), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            ClipboardManager clipboard = (ClipboardManager)
                                                    mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                                            ClipData clip = ClipData.newPlainText("simple text", mContext.getString(contact.getValueResource()));
                                            clipboard.setPrimaryClip(clip);
                                        }
                                    })
                                    .setPositiveButton(mContext.getString(R.string.dialog_call_call), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            mContext.startActivity(intent);
                                        }
                                    });
                            builder.show();

                            return;

                        case 1:

                            // Call confirmation dialog
                            AlertDialog.Builder builder2 = new AlertDialog.Builder(mContext)
                                    .setTitle(mContext.getString(R.string.dialog_call_title))
                                    .setMessage(mContext.getString(R.string.dialog_call_message_cz))
                                    .setNegativeButton(mContext.getString(android.R.string.cancel), null)
                                    .setNeutralButton(mContext.getString(R.string.dialog_call_copy), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            ClipboardManager clipboard = (ClipboardManager)
                                                    mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                                            ClipData clip = ClipData.newPlainText("simple text", mContext.getString(contact.getValueResource()));
                                            clipboard.setPrimaryClip(clip);
                                        }
                                    })
                                    .setPositiveButton(mContext.getString(R.string.dialog_call_call), new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            mContext.startActivity(intent);
                                        }
                                    });
                            builder2.show();

                            return;

                        case 2:
                            mContext.startActivity(Intent.createChooser(intent, "Send E-mail"));
                            return;

                        case 3:
                            mContext.startActivity(intent);
                            break;

                        case 4:

                            if (isSkypeClientInstalled()) {

                                // Call confirmation dialog
                                AlertDialog.Builder builder3 = new AlertDialog.Builder(mContext)
                                        .setTitle(mContext.getString(R.string.dialog_call_title_skype))
                                        //.setMessage(mContext.getString(R.string.dialog_call_message_cz))
                                        .setNegativeButton(mContext.getString(android.R.string.cancel), null)
                                        .setNeutralButton(mContext.getString(R.string.dialog_call_copy_skype), new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                ClipboardManager clipboard = (ClipboardManager)
                                                        mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                                                ClipData clip = ClipData.newPlainText("simple text", mContext.getString(contact.getValueResource()));
                                                clipboard.setPrimaryClip(clip);
                                            }
                                        })
                                        .setPositiveButton(mContext.getString(R.string.dialog_call_call), new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                mContext.startActivity(intent);
                                            }
                                        });
                                builder3.show();

                            } else {
                                // Open Google Play if Skype is not available
                                Uri marketUri = Uri.parse("market://details?id=com.skype.raider");
                                Intent appIntent = new Intent(Intent.ACTION_VIEW, marketUri);
                                appIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                mContext.startActivity(appIntent);
                            }

                            break;
                    }
                }
            });
        }
    }

    /**
     * Count contacts
     *
     * @return Number of contacts
     */
    @Override
    public int getItemCount() {
        return Contact.values().length;
    }

    /**
     * View Holder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.textView);
        }

        public TextView getTextView() {
            return mTextView;
        }
    }

    /**
     * Determine whether the Skype for Android client is installed on this device
     *
     * @return Skype installed state
     */
    public boolean isSkypeClientInstalled() {

        PackageManager packageManager = mContext.getPackageManager();

        try {
            packageManager.getPackageInfo("com.skype.raider", PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            return (false);
        }
        return (true);
    }
}
