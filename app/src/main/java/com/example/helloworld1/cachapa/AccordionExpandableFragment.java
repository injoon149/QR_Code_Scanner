package com.example.helloworld1.cachapa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;

import com.example.helloworld1.R;

import net.cachapa.expandablelayout.ExpandableLayout;

public class AccordionExpandableFragment extends Fragment {

    private ExpandableLayout accordionLayout1, accordionLayout2;
    private Button accordionButton1, accordionButton2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accordion_expandable, container, false);

        accordionLayout1 = view.findViewById(R.id.accordionLayout1);
        accordionLayout2 = view.findViewById(R.id.accordionLayout2);
        accordionButton1 = view.findViewById(R.id.accordionButton1);
        accordionButton2 = view.findViewById(R.id.accordionButton2);

        accordionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (accordionLayout1.isExpanded()) {
                    accordionLayout1.collapse();
                } else {
                    accordionLayout1.expand();
                    accordionLayout2.collapse();
                }
            }
        });

        accordionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (accordionLayout2.isExpanded()) {
                    accordionLayout2.collapse();
                } else {
                    accordionLayout2.expand();
                    accordionLayout1.collapse();
                }
            }
        });

        return view;
    }
}

