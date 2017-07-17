package com.example.sravan.sampleflexibleadaptertry;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.flexibleadapter.items.IHeader;

/**
 * A placeholder fragmudaent containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    RecyclerView mRecyclerView;
    List<IFlexible> myItems = new ArrayList<>();
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = rootView.findViewById(R.id.recycler_view);
        // Optional but strongly recommended: Compose the initial list
        createHeadersSectionsDatabase(400,100);

        // Initialize the Adapter
        FlexibleAdapter<IFlexible> adapter = new FlexibleAdapter<>(myItems);
        // Initialize the RecyclerView and attach the Adapter to it as usual
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new SmoothScrollLinearLayoutManager(getContext()));
        adapter.setDisplayHeadersAtStartUp(true);
        adapter.setSwipeEnabled(true);
        adapter.setStickyHeaders(true);
        return rootView;
    }

    /*public List<IFlexible> getDatabaseList() {
        List<IFlexible> list = new ArrayList<>();
        list.add(new MyItem("1", "Hello"));
        list.add(new MyItem("2", "World"));
        list.add(new MyItem("3", "Hello"));
        list.add(new MyItem("4", "World"));
        list.add(new MyItem("5", "Hello"));
        list.add(new MyItem("6", "World"));
        list.add(new MyItem("7", "Hello"));
        list.add(new MyItem("8", "World"));
        list.add(new MyItem("9", "Hello"));
        list.add(new MyItem("10", "World"));
        list.add(new MyItem("11", "Hello"));
        list.add(new MyItem("12", "World"));
        list.add(new MyItem("13", "Hello"));
        list.add(new MyItem("14", "World"));
        list.add(new MyItem("15", "Hello"));
        list.add(new MyItem("16", "World"));
        list.add(new MyItem("17", "Hello"));
        list.add(new MyItem("18", "World"));

        return list;
    }
*/

    public void createHeadersSectionsDatabase(int size, int headers) {
        HeaderItem header = null;
        if (myItems.size()>0){
            myItems.clear();
        }
        int lastHeaderId = 0;
        for (int i = 0; i < size; i++) {
            header = i % Math.round(size / headers) == 0 ? newHeader(++lastHeaderId) : header;
            myItems.add(newSimpleItem(i + 1, header));
        }
    }

    public static HeaderItem newHeader(int i) {
        HeaderItem header = new HeaderItem("H" + i);
        header.setTitle("Header " + i);
        //header is hidden and un-selectable by default!
        return header;
    }

    /*
	 * Creates a normal item with a Header linked.
	 */
    public static SimpleItem newSimpleItem(int i, IHeader header) {
        SimpleItem item = new SimpleItem("I" + i, (HeaderItem) header);
        item.setTitle("Simple Item " + i);
        return item;
    }
}
