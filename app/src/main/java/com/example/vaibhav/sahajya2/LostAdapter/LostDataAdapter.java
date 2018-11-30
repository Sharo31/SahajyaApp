package com.example.vaibhav.sahajya2.LostAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vaibhav.sahajya2.LostPersonData;
import com.example.vaibhav.sahajya2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

 public class LostDataAdapter extends RecyclerView.Adapter<LostViewHolder>  {

    private List<LostPersonData> mLostData;
    private Context mContext;

    public LostDataAdapter(List<LostPersonData> mLostData, Context mContext) {
        this.mLostData = mLostData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public LostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_model1,
                viewGroup, false);
        return new LostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LostViewHolder lostViewHolder, int i) {
        lostViewHolder.lname.setText(mLostData.get(i).getLostPersonName());
        lostViewHolder.ldesc.setText(mLostData.get(i).getLostPersonDesc());
        lostViewHolder.ltime.setText(mLostData.get(i).getmTime());
        lostViewHolder.lage.setText("Age: "+mLostData.get(i).getLostPersonAge());
        if(mLostData.get(i).getProfileURL()!=null)
            Picasso.get().load(mLostData.get(i).getProfileURL()).resize(170,170).centerCrop().into(lostViewHolder.lphoto);
    }

    @Override
    public int getItemCount() {
        return mLostData.size();
    }
}
class  LostViewHolder extends RecyclerView.ViewHolder{

    TextView lname;
    TextView ldesc;
    TextView ltime;
    ImageView lphoto;
    TextView lage;


    public LostViewHolder(@NonNull View itemView) {
        super(itemView);
        lname=itemView.findViewById(R.id.name);
        ldesc=itemView.findViewById(R.id.desc);
        ltime=itemView.findViewById(R.id.losttime);
        lage=itemView.findViewById(R.id.lostage);
        lphoto=itemView.findViewById(R.id.photolost);
    }
}

