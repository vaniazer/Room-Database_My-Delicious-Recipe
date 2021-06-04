package com.example.mydeliciousrecipe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydeliciousrecipe.adapter.ResepAdapter;
import com.example.mydeliciousrecipe.database.ResepDao;
import com.example.mydeliciousrecipe.database.ResepDatabase;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment implements View.OnClickListener{

    Button Create;
    private RecyclerView recyclerView;
    private ArrayList<Resep> reseps = new ArrayList<>();
    private ResepAdapter resepAdapter;
    private Context context;
    private ResepDao resepDao;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        resepDao = ResepDatabase.getInstance(getContext()).resepDao();

        resepAdapter = new ResepAdapter((Activity) getContext());

        recyclerView = view.findViewById(R.id.rv_resep_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(resepAdapter);
        loadData();

        Create = view.findViewById(R.id.create);

        Create.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_menu_fragment, container, false);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), InputActivity.class);
        startActivityForResult(intent, InputActivity.REQUEST_ADD);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == InputActivity.REQUEST_ADD) {
            if (resultCode == InputActivity.RESULT_ADD) {
                loadData();
                showSnackbar("Data Added Successfully");
            }
        }
        else if (requestCode == DetailActivity.RESULT_DELETE) {
            if (resultCode == DetailActivity.RESULT_DELETE) {
                loadData();
                showSnackbar("Data Deleted Successfully");
            }
        }
        else if (requestCode == EditanActivity.REQUEST_EDIT) {
            if (resultCode == EditanActivity.RESULT_EDIT) {
                loadData();
                showSnackbar("Data Edited Successfully");
            }
        }
    }

    void loadData() {
        List<Resep> data = resepDao.getAllData();
        reseps.clear();
        reseps.addAll(data);
        resepAdapter.setReseps(reseps);
        if (data.size() == 0) {
            showSnackbar("No Data");
        }
    }

    private void showSnackbar(String message) {
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_LONG).show();
    }


}