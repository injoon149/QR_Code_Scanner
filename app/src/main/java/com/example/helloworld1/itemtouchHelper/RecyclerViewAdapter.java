package com.example.helloworld1.itemtouchHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld1.R;

import java.util.Collections;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> implements ItemTouchHelperListener {

    private List<String> nameList;

    public RecyclerViewAdapter(List<String> nameList) {
        this.nameList = nameList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sample_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.onBind(nameList.get(position));
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    @Override
    public boolean onItemMove(int from_position, int to_position) {
        Collections.swap(nameList, from_position, to_position);
        notifyItemMoved(from_position, to_position);
        return true;
    }

    @Override
    public void onItemSwipe(int position) {
        nameList.remove(position);
        notifyItemRemoved(position);
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView nameView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.name_view);
        }

        public void onBind(String name) {
            nameView.setText(name);
        }
    }
}

