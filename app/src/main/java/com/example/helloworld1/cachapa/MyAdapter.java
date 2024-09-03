package com.example.helloworld1.cachapa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld1.R;

import net.cachapa.expandablelayout.ExpandableLayout;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> data;
    private int expandedPosition = -1;  // 현재 확장된 아이템의 포지션

    public MyAdapter(List<String> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = data.get(position);
        holder.titleTextView.setText(item);

        final boolean isExpanded = position == expandedPosition;
        holder.expandableLayout.setExpanded(isExpanded, false);  // 현재 아이템의 확장 상태 설정

        holder.titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded) {
                    expandedPosition = -1;  // 현재 아이템이 이미 확장된 상태라면 닫기
                    notifyItemChanged(position);
                } else {
                    int prevExpandedPosition = expandedPosition;
                    expandedPosition = position;  // 새로 확장할 아이템의 위치 설정
                    notifyItemChanged(prevExpandedPosition);  // 이전에 확장된 아이템 닫기
                    notifyItemChanged(position);  // 새로 확장할 아이템 열기
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ExpandableLayout expandableLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.itemTitle);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);
        }
    }
}


