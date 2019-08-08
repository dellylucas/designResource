package com.example.designresource;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListDrawFragment extends Fragment {
    private ISelected implement;
    private int lastCheck = -1;

    public interface ISelected {
        void classSelected(int select);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            implement = (ISelected) context;
        } catch (ClassCastException ex) {
            throw new ClassCastException(context.toString());
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        List<ModeDraw> list = new ArrayList<>();
        String[] classTittles = getResources().getStringArray(R.array.tittles);
        String[] classDescriptions = getResources().getStringArray(R.array.descriptions);
        for (int i = 0; i <= classTittles.length; i++) {
            list.add(new ModeDraw(classTittles[i], classDescriptions[i]));
        }
        DrawAdapter viewList = new DrawAdapter(list, getContext());
        ListView listOptions = null;
        if (container != null) {
            listOptions = container.findViewById(R.id.listOptions);
            listOptions.setAdapter(viewList);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}