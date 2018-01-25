package udovyk.dribbleclimoxydaggerrx.ui.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import udovyk.dribbleclimoxydaggerrx.R;
import udovyk.dribbleclimoxydaggerrx.network.model.Shot;
import udovyk.dribbleclimoxydaggerrx.ui.view.ShotItemView;


public class ShotsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM = 0;
    public static final int LOADING = 1;

    private List<Shot> shotsList;
    private Context context;
    private boolean isLoadingAdded = false;

    private ItemClickListener mItemClickListener;

    @Inject
    public ShotsAdapter(Context context) {
        this.context = context;
        shotsList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = new ShotsVH(new ShotItemView(context));
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case ITEM:
                ShotsVH shotsVH = (ShotsVH) holder;
                shotsVH.setData(shotsList.get(position));

                break;
            case LOADING:
                break;
        }

    }

    public List<Shot> getShotsList() {
        return shotsList;
    }

    public void setShotsList(List<Shot> shotsList) {
        this.shotsList = shotsList;
    }


    @Override
    public int getItemCount() {
        return shotsList == null ? 0 : shotsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == shotsList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public void add(Shot shot) {
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
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Shot());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = shotsList.size() - 1;
        Shot shot = getItem(position);

        if (shot != null) {
            shotsList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void setOnClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }


    protected class ShotsVH extends RecyclerView.ViewHolder {
        ShotItemView shotItemView;

        ShotsVH(ShotItemView viewItem) {
            super(viewItem);
            shotItemView = viewItem;
            shotItemView.setClickListener(mItemClickListener);
        }

        void setData(Shot data) {
            shotItemView.setData(data);
        }

    }

    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);

        }
    }


}
