package com.example.helloworld1.cachapa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;

import com.example.helloworld1.R;

import net.cachapa.expandablelayout.ExpandableLayout;

public class HorizontalExpandableFragment extends Fragment {

    private ExpandableLayout horizontalExpandableLayout;
    private Button horizontalToggleButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_horizontal_expandable, container, false);

        horizontalExpandableLayout = view.findViewById(R.id.horizontalExpandableLayout);
        horizontalToggleButton = view.findViewById(R.id.horizontalToggleButton);

        horizontalToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (horizontalExpandableLayout.isExpanded()) {
                    horizontalExpandableLayout.collapse();
                } else {
                    horizontalExpandableLayout.expand();
                }
            }
        });

        return view;
    }
}

