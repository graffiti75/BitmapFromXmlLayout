package rodrigo.example.com.bitmapfromxmllayout.activity;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import rodrigo.example.com.bitmapfromxmllayout.R;

/**
 * CoordinatorAdapter.java.
 *
 * @author Rodrigo Cericatto
 * @since Jan 26, 2016
 */
public class CoordinatorAdapter extends RecyclerView.Adapter<CoordinatorAdapter.ViewHolder> {

    //--------------------------------------------------
    // Attributes
    //--------------------------------------------------

    private Activity mActivity;

    //--------------------------------------------------
    // Constructor
    //--------------------------------------------------

    public CoordinatorAdapter(Activity context) {
        mActivity = context;
    }

    //--------------------------------------------------
    // Adapter Methods
    //--------------------------------------------------

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_coordinator, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.titleTextView.setText("Title");
        holder.contentTextView.setText(mActivity.getString(R.string.cheese_ipsum));
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    //--------------------------------------------------
    // View Holder
    //--------------------------------------------------

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView contentTextView;

        public ViewHolder(View view) {
            super(view);
            titleTextView = (TextView)view.findViewById(R.id.id_adapter_coordinator__title_text_view);
            contentTextView = (TextView)view.findViewById(R.id.id_adapter_coordinator__content_text_view);
        }
    }
}