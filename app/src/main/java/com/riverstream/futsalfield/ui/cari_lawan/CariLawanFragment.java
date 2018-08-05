package com.riverstream.futsalfield.ui.cari_lawan;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.riverstream.futsalfield.R;
import com.riverstream.futsalfield.data.adapter.CariLawanAdapter;
import com.riverstream.futsalfield.data.model.CariLawan;
import com.riverstream.futsalfield.utils.MyDividerItemDecoration;
import com.riverstream.futsalfield.utils.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;


public class CariLawanFragment extends Fragment {
    private List<CariLawan> cariLawans = new ArrayList<>();
    private RecyclerView recyclerView;
    private CariLawanAdapter mAdapter;
    private FloatingActionButton fab;

    private OnFragmentInteractionListener mListener;

    public CariLawanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.recycle_cari_lawan, container, false);
        fab             = rootView.findViewById(R.id.fab);
        recyclerView    = rootView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        cariLawans = new ArrayList<>();
        cariLawans.add(new CariLawan("Team1",null,"Lapang Tegalega"));
        cariLawans.add(new CariLawan("Team2",null,"Lapang Lapang2"));
        cariLawans.add(new CariLawan("Team3",null,"Lapang Tegalega3"));
        mAdapter = new CariLawanAdapter(cariLawans);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new MyDividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);


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

        fab.setOnClickListener((View view)->{
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });
        return rootView;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
