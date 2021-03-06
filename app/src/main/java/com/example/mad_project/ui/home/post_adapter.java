package com.example.mad_project.ui.home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mad_project.R;
import com.example.mad_project.ui.faculty_access.register_candi_form;
import com.example.mad_project.ui.user_authentication.user;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class post_adapter extends BaseAdapter {
    private Context context;
    private ArrayList<post_obj> list_data;
    static public Button register_button;
    FragmentManager manager;

    TextView register_button_text_bool,post_title,post_content,user_name,user_post,post_date,post_time;
    public post_adapter(Context context, ArrayList list_data,FragmentManager manager) {
        this.context=context;
        this.list_data=list_data;
        this.manager=manager;

    }


    @Override
    public int getCount() {
        return list_data.size();
    }

    @Override
    public Object getItem(int position) {
        return list_data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view=View.inflate(context, R.layout.post_costumize_view,null);
        post_title=view.findViewById(R.id.post_title);
        post_content=view.findViewById(R.id.post_Content);
        user_name=view.findViewById(R.id.user_name);
        user_post=view.findViewById(R.id.user_post);
        post_date=view.findViewById(R.id.post_date);
        post_time=view.findViewById(R.id.post_time);
        register_button_text_bool=view.findViewById(R.id.register_button_text_bool);

        register_button=view.findViewById(R.id.register_button);
        post_title.setText(list_data.get(position).post_title);
        post_content.setText(list_data.get(position).post_content);
        post_date.setText(list_data.get(position).post_date);
        post_time.setText(list_data.get(position).post_time);
        user_name.setText(list_data.get(position).user_name);
        user_post.setText(list_data.get(position).user_access+" of "+list_data.get(position).user_dept);


        if (!list_data.get(position).register_button.equals("true")){
            register_button.setVisibility(View.GONE);
            register_button_text_bool.setVisibility(View.GONE);
        }
        else{
            register_button_text_bool.setVisibility(View.GONE);
            register_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle=new Bundle();
                    bundle.putString("post_id_upadte",list_data.get(position).post_candidatepost);
                    Fragment frag=new register_candi_form();
                    frag.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = manager.beginTransaction();
                    fragmentTransaction.replace(R.id.nav_host_fragment,frag);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }
        return view;

    }
}
