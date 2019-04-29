package com.med.com.cubegame.Utils;

import android.view.LayoutInflater;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.med.com.cubegame.R;
import com.med.com.cubegame.itemData;

import java.util.List;


public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {
    private List<itemData> _itemsData;

    public myAdapter(List<itemData> _itemsData) {
        this._itemsData = _itemsData;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public myAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.img_row_app, null);

        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData

        viewHolder.txtViewTitle.setText(_itemsData.get(position).item_title);
        viewHolder.txtViewDesc.setText(_itemsData.get(position).item_desc);
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtViewTitle;
        public TextView txtViewDesc;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.textView);
            txtViewDesc = (TextView) itemLayoutView.findViewById(R.id.text_view_desc);
        }
    }


    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return _itemsData.size();
    }

}

