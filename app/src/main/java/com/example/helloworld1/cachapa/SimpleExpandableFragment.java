package com.example.helloworld1.cachapa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;

import com.example.helloworld1.R;

import net.cachapa.expandablelayout.ExpandableLayout;

public class SimpleExpandableFragment extends Fragment {

    private ExpandableLayout simpleExpandableLayout;
    private Button simpleToggleButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_expandable, container, false);

        simpleExpandableLayout = view.findViewById(R.id.simpleExpandableLayout);
        simpleToggleButton = view.findViewById(R.id.simpleToggleButton);

        simpleToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (simpleExpandableLayout.isExpanded()) {
                    simpleExpandableLayout.collapse();
                } else {
                    simpleExpandableLayout.expand();
                }
            }
        });

        return view;
    }
}

