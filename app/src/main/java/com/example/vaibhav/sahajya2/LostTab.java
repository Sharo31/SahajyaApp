package com.example.vaibhav.sahajya2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vaibhav.sahajya2.LostAdapter.LostDataAdapter;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Nullable;

public class LostTab extends Fragment {
    private static final String TAG ="FireLog" ;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<LostPersonData> listItems;
    private FloatingActionButton fab;
    private FirebaseFirestore mFirestore;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.losttab, container, false);
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());


        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        listItems=new ArrayList<>();
        adapter=new LostDataAdapter(listItems,this.getContext());
        recyclerView.setAdapter(adapter);

        mFirestore=FirebaseFirestore.getInstance();
        mFirestore.collection("MissingPersonData").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e!=null){
                    Log.d(TAG,"Error: "+e.getMessage());
                }
                for(DocumentChange doc:queryDocumentSnapshots.getDocumentChanges()){
                    if (doc != null) {

                        if (doc.getType() == DocumentChange.Type.ADDED) {

                            String name=doc.getDocument().getString("name");
                            String age=doc.getDocument().getString("age");
                            String desc=doc.getDocument().getString("desc");
                            String time=doc.getDocument().getString("time");
                            String url=doc.getDocument().getString("purl");
                            LostPersonData lostPersonData = new LostPersonData(name,age,desc,url,time);
                            listItems.add(lostPersonData);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
        return rootView;
    }

}
