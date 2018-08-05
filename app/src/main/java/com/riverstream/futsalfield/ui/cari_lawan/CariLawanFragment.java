package com.riverstream.futsalfield.ui.cari_lawan;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.riverstream.futsalfield.R;
import com.riverstream.futsalfield.data.model.CariLawan;
import com.riverstream.futsalfield.utils.MyDividerItemDecoration;
import com.riverstream.futsalfield.utils.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;


public class CariLawanFragment extends Fragment {
    private List<CariLawan> cariLawans = new ArrayList<>();
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private DatabaseReference database;
    private DatabaseReference ref;
    //public RecyclerView.LayoutManager mLayoutManager;
    public LinearLayoutManager mLayoutManager;

    FirebaseRecyclerOptions<CariLawan> options;
    FirebaseRecyclerAdapter<CariLawan, CariLawanViewHolder> adapter;

    public CariLawanFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.recycle_cari_lawan, container, false);
        database = FirebaseDatabase.getInstance().getReference();
        ref = database.child("CariLawan");
        recyclerView = rootView.findViewById(R.id.recycler_view);
        fab = rootView.findViewById(R.id.fab);
        //recyclerView.setHasFixedSize(true);
        cariLawans = new ArrayList<>();
        //mAdapter = new CariLawanAdapter(getActivity(),new FirebaseHelper(ref).retrieve());
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        /*cariLawans.add(new CariLawan("Team1",null,"Lapang Tegalega"));
        cariLawans.add(new CariLawan("Team2",null,"Lapang Lapang2"));
        cariLawans.add(new CariLawan("Team3",null,"Lapang Tegalega3"));*/
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, 16));
        //recyclerView.setAdapter(mAdapter);
        displayCariLawan();
        fab.setOnClickListener((View view) -> {
            Dialog d = new Dialog(getActivity());
            d.setTitle("Save To Firebase");
            d.setContentView(R.layout.input_dialog);

            Log.v("CariLawanFragment.class", "FAB clicked");
            EditText nameEditTxt            = (EditText) d.findViewById(R.id.nameEditText);
            EditText tempatTandingEditTxt   = (EditText) d.findViewById(R.id.tempatTandingEditText);
            EditText noHpTxt                = (EditText) d.findViewById(R.id.noHpEditText);
            Button saveBtn = (Button) d.findViewById(R.id.saveBtn);

            //SAVE
            saveBtn.setOnClickListener((View v) -> {
                //GET DATA
                String namaTeamHome     = nameEditTxt.getText().toString();
                String tempatTanding    = tempatTandingEditTxt.getText().toString();
                String noHp             = noHpTxt.getText().toString();

                //SET DATA
                CariLawan cl = new CariLawan(namaTeamHome, tempatTanding,noHp);

                Log.v("CariLawanFragment.class", "namaTeamHome " + cl.getNamaTeamHome()
                        + "tempatTanding " + cl.getTempatTanding());
                //VALIDATE
                if (namaTeamHome.length() > 0 && namaTeamHome != null) {
                    try {
                        String key = ref.push().getKey();
                        ref.child(key).setValue(cl).addOnSuccessListener((Void aVoid) -> {
                            Log.v("CariLawanFragment.class", "Berhasil dipush");
                        });
                    } catch (DatabaseException e) {
                        Log.e("CariLawanFragment.class", "Error " + e);
                    }

                } else {
                    Toast.makeText(getActivity(), "Name Cannot Be Empty", Toast.LENGTH_SHORT).show();
                }
                d.dismiss();
            });

            d.show();
        });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {
                CariLawan cariLawan = cariLawans.get(position);
                Toast.makeText(getActivity().getApplicationContext(), cariLawan.getNamaTeamHome() + " is selected!", Toast.LENGTH_SHORT).show();
                //DetailActivity.navigate(getActivity(), view.findViewById(R.id.), sayurandanBuah);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return rootView;
    }

    public void displayCariLawan() {
        Query query = ref;

        options =
                new FirebaseRecyclerOptions.Builder<CariLawan>()
                        .setQuery(ref, CariLawan.class)
                        .setLifecycleOwner(this)
                        .build();

        adapter = new FirebaseRecyclerAdapter<CariLawan, CariLawanViewHolder>(options) {
            @NonNull
            @Override
            public CariLawanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_cari_lawan, parent, false);

                return new CariLawanViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull CariLawanViewHolder holder, int position, @NonNull CariLawan cl) {
                holder.namaTeam.setText(cl.getNamaTeamHome());
                holder.namaTeam.setTextColor(getResources().getColor(R.color.black));
                holder.tempatTanding.setText(cl.getTempatTanding());
                holder.tempatTanding.setTextColor(getResources().getColor(R.color.black));
                holder.noHpTxt.setText(cl.getNoHp());
                holder.imageHolder.setImageResource(R.drawable.ic_account_circle_black_48dp);
                Log.v("CariLawanFragment.class", "namaTeamHome " + cl.getNamaTeamHome()
                        + "tempatTanding " + cl.getTempatTanding());
                //holder.images.setImageResource(cariLawan.getImage());
            }


        };

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int friendlyMessageCount = adapter.getItemCount();
                int lastVisiblePosition = mLayoutManager.findLastCompletelyVisibleItemPosition();
                // If the recycler view is initially being loaded or the
                // user is at the bottom of the list, scroll to the bottom
                // of the list to show the newly added message.
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (friendlyMessageCount - 1) &&
                                lastVisiblePosition == (positionStart - 1))) {
                    recyclerView.scrollToPosition(positionStart);
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

    public static class CariLawanViewHolder extends RecyclerView.ViewHolder {

        View mView;
        public TextView namaTeam, tempatTanding,noHpTxt;
        public ImageView imageHolder;
        public CariLawanViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            namaTeam        = itemView.findViewById(R.id.namaTeam_id);
            tempatTanding   = itemView.findViewById(R.id.tempatTanding_id);
            noHpTxt         = itemView.findViewById(R.id.noHp_id);
            imageHolder     = itemView.findViewById(R.id.imagesRC);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}

