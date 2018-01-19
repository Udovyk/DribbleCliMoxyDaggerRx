package udovyk.dribbleclimoxydaggerrx.ui.adapters;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import udovyk.dribbleclimoxydaggerrx.R;
import udovyk.dribbleclimoxydaggerrx.common.ShotDetailConstants;
import udovyk.dribbleclimoxydaggerrx.di.qualifiers.ActivityContext;
import udovyk.dribbleclimoxydaggerrx.network.model.Shot;
import udovyk.dribbleclimoxydaggerrx.ui.view.ShotItemView;


public class ShotsAdapter extends BaseAdapter<Shot, ShotItemView, BaseViewHolder<ShotItemView>> {

    private static final String TAG = "ShotsAdapter";
    public static final int ITEM = 0;
    public static final int LOADING = 1;

    private ItemClickListener itemClickListener;
    private Context context;
    private boolean isLoadingAdded = false;


    public ShotsAdapter(Context context) {
        this.context = context;
    }


    /*public List<Shot> getShotsList() {
        return shotsList;
    }*/

    /*public void setShotsList(List<Shot> shotsList) {
        this.shotsList = shotsList;
    }*/

    @Override
    public BaseViewHolder<ShotItemView> onCreateViewHolder(ViewGroup parent, int viewType) {

        return new BaseViewHolder<>(new ShotItemView(context));

        /*RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;*/


    }

   /* @NonNull
    public RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.shots_item, parent, false);
        viewHolder = new ShotsVH(v1);
        return viewHolder;
    }*/
       @Override
    public void onBindViewHolder(BaseViewHolder<ShotItemView> holder, int position) {
           super.onBindViewHolder(holder, position);
           holder.getView().setClickListener(itemClickListener);
        /*final Shot shot = shotsList.get(position);


        switch (getItemViewType(position)) {
            case ITEM:
                final ShotsVH shotsVH = (ShotsVH) holder;

                Picasso.with(context)
                        .load(shot.getImages().getNormal())
                        .into(shotsVH.imShotItem);
                shotsVH.tvShotTitle.setText(shot.getTitle());
                shotsVH.tvShotAutor.setText(shot.getUser().getName());

                String dateRaw = shot.getCreatedAt();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                SimpleDateFormat sdfF = new SimpleDateFormat("dd-MM-yyyy");
                sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                Date date;
                try {

                    date = sdf.parse(dateRaw);
                    String finalFormatedDate = sdfF.format(date);
                    Log.d(TAG, "onBindViewHolder: date = " + finalFormatedDate);
                    shotsVH.tvShotDate.setText(finalFormatedDate);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //shotsVH.cardViewShots.setOnClickListener(getOnClickListener(shot));
                //shotsVH.imShotItem.setOnClickListener(getOnClickListener(shot));

                break;
            case LOADING:
                // Do nothing
                break;
        }*/


    }

    /*private View.OnClickListener getOnClickListener(Shot shot) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (parentFragment.getFragmentManager().findFragmentByTag(ShotDetailFragment.TAG) == null) {
                    ShotDetailFragment shotDetailFragment = new ShotDetailFragment();
                    Bundle bundle = new Bundle();
                    setDataToBundle(bundle, shot);
                    shotDetailFragment.setArguments(bundle);

                    context.getFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                            .add(R.id.shots_frag, shotDetailFragment, ShotDetailFragment.TAG)
                            .addToBackStack(null)
                            .commit();
                }
            }
        };
    }*/

    @Override
    public int getItemCount() {
        return mContent == null ? 0 : mContent.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == mContent.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }


    /*public void add(Shot shot) {
        shotsList.add(shot);
        notifyItemInserted(shotsList.size() - 1);
    }

    public void addAll(List<Shot> list) {
        for (Shot shot : list) {
            add(shot);
        }
    }

    public void remove(Shot shot) {
        int position = shotsList.indexOf(shot);
        if (position > 1) {
            shotsList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public Shot getItem(int position) {
        return shotsList.get(position);
    }*/

    public void addLoadingFooter() {
        isLoadingAdded = true;
        addData(new Shot());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = mContent.size() - 1;
        Shot shot = getData(position);

        if (shot != null) {
            mContent.remove(position);
            notifyItemRemoved(position);
        }
    }


   /* private void setDataToBundle(Bundle bundle, Shot shot) {
        bundle.putString(ShotDetailConstants.TITLE, shot.getTitle());
        bundle.putString(ShotDetailConstants.DESCRIPTION, shot.getDescription());

        bundle.putString(ShotDetailConstants.IMAGE_URL, shot.getImages().getNormal());
        bundle.putInt(ShotDetailConstants.LIKES_COUNT, shot.getLikesCount());
        bundle.putInt(ShotDetailConstants.VIEWS_COUNT, shot.getViewsCount());
        bundle.putInt(ShotDetailConstants.COMMENTS_COUNT, shot.getCommentsCount());
        bundle.putInt(ShotDetailConstants.ID, shot.getId());
    }*/

    public void setOnClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /*protected class ShotsVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cardViewShots;
        ImageView imShotItem;
        TextView tvShotTitle;
        TextView tvShotAutor;
        TextView tvShotDate;

        ShotsVH(View viewItem) {
            super(viewItem);


            cardViewShots = viewItem.findViewById(R.id.cvShot);
            imShotItem = viewItem.findViewById(R.id.imShotItem);
            tvShotTitle = viewItem.findViewById(R.id.tvShotTitle);
            tvShotAutor = viewItem.findViewById(R.id.tvShotAutor);
            tvShotDate = viewItem.findViewById(R.id.tvShotDate);

            cardViewShots.setOnClickListener(this);
            imShotItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context, "item was clicked", Toast.LENGTH_SHORT).show();
        }
    }

    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }*/
}
