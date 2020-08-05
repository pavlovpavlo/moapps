package com.pavlov.moappstest.screen.applist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.pavlov.moappstest.R;
import com.pavlov.moappstest.adapters.ApplicationListAdapter;
import com.pavlov.moappstest.pojo.Application;
import com.pavlov.moappstest.pojo.ApplicationResponse;
import com.pavlov.moappstest.screen.login.LoginActivity;
import com.pavlov.moappstest.screen.login.LoginPresenter;

import java.util.ArrayList;

public class ApplicationListActivity extends AppCompatActivity implements ApplicationView{

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView appList;
    private ApplicationListAdapter adapter;
    private ApplicationPresenter presenter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.previewer_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itemMain:
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("userToken");
                editor.apply();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_list);
        getSupportActionBar().setTitle("Previewer");
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.color.actionBar));

        presenter = new ApplicationPresenter(this);
        refreshLayout = findViewById(R.id.refreshLayout);
        appList = findViewById(R.id.appList);
        adapter = new ApplicationListAdapter();
        appList.setLayoutManager(new LinearLayoutManager(this));
        appList.setAdapter(adapter);
        adapter.setApplications(new ArrayList<Application>());
        presenter.getApplicationList(getIntent().getStringExtra("userToken"));

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getApplicationList(getIntent().getStringExtra("userToken"));
            }
        });
    }

    @Override
    public void getApplicationList(ApplicationResponse appList) {
        adapter.setApplications(appList.getData());
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.disposeDisposable();
    }

    @Override
    public void onBackPressed() {
    }
}
