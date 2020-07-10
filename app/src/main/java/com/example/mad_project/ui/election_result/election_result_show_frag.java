package com.example.mad_project.ui.election_result;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.mad_project.R;
import com.example.mad_project.ui.faculty_access.register_candidate_adpter;
import com.example.mad_project.ui.live_voting.list_candi_for_election;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link election_result_show_frag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link election_result_show_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class election_result_show_frag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private  final String[] year = {"Select Year","FE", "SY", "TY","Btech"};
    private  final String[] department = {"Select Department","FE","SCET", "SMEC", "CHEMICAL","ETC"};
    private  final String[] block = {"Select Block","A", "B", "C","D","E","F","G","H"};
    private  final String[] position = {"Select Position","CR", "President", "WIse President"};
    private register_candidate_adpter adpater;
    Spinner spinner_year,spinner_dept,spinner_block,spinner_pos;
    Button button_show_candi;
    String year_candi,dept_candi,block_candi,pos_candi;

    public election_result_show_frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment election_result_show_frag.
     */
    // TODO: Rename and change types and number of parameters
    public static election_result_show_frag newInstance(String param1, String param2) {
        election_result_show_frag fragment = new election_result_show_frag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_election_result_show_frag, container, false);

        spinner_year = (Spinner)view.findViewById(R.id.drop_down_year);
        spinner_dept = (Spinner)view.findViewById(R.id.drop_down_dept);
        spinner_block = (Spinner)view.findViewById(R.id.drop_down_block);
        spinner_pos = (Spinner)view.findViewById(R.id.drop_down_position);

        ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,year);
        ArrayAdapter<String> adapter_dept = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,department);
        ArrayAdapter<String> adapter_block = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,block);
        ArrayAdapter<String> adapter_post = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,position);

        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_year.setAdapter(adapter_year);

        adapter_dept.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_dept.setAdapter(adapter_dept);

        adapter_block.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_block.setAdapter(adapter_block);

        adapter_post.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_pos.setAdapter(adapter_post);

        button_show_candi=view.findViewById(R.id.button_show_candi);
        button_show_candi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year_candi = spinner_year.getSelectedItem().toString();
                dept_candi = spinner_dept.getSelectedItem().toString();
                block_candi = spinner_block.getSelectedItem().toString();
                pos_candi = spinner_pos.getSelectedItem().toString();
                Bundle bundle=new Bundle();
                final Fragment frag=new elction_result_graph();
                bundle.putString("year", year_candi);
                bundle.putString("dept", dept_candi);
                bundle.putString("block", block_candi);
                bundle.putString("post", pos_candi);
                frag.setArguments(bundle);
                Fragment f1=getParentFragmentManager().findFragmentById(R.id.nav_host_fragment);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.nav_host_fragment, frag);
                transaction.addToBackStack(null);
                transaction.remove(f1);
                transaction.commit();
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
//        else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
