package com.riverstream.futsalfield.data.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.riverstream.futsalfield.R;
import com.riverstream.futsalfield.data.model.CariLawan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ArieDZ_2 on 8/5/2018.
 */

public class CariLawanAdapter extends RecyclerView.Adapter<CariLawanAdapter.MyViewHolder> {
    public List<CariLawan> cariLawans;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView namaTeam, tempatTanding;
        public ImageView images;

        public MyViewHolder(View view) {
            super(view);
            namaTeam = (TextView) view.findViewById(R.id.namaTeam_id);
            tempatTanding = (TextView) view.findViewById(R.id.tempatTanding_id);
            images = (ImageView) view.findViewById(R.id.imagesRC);
        }
    }

    public CariLawanAdapter(List<CariLawan> cariLawans) {
        this.cariLawans = cariLawans;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_cari_lawan, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CariLawan cariLawan = cariLawans.get(position);
        holder.namaTeam.setText(cariLawan.getNamaTeamHome());
        holder.tempatTanding.setText(cariLawan.getTempatTanding());
        holder.images.setImageResource(cariLawan.getImage());
    }

    @Override
    public int getItemCount() {
        return cariLawans.size();
    }
}
