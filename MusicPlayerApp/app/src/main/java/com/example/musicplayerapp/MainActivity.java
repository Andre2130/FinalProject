package com.example.musicplayerapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private static final int EXTERNAL_STORAGE_PERMISSION_REQUEST = 1;
    List<Song> songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        executeRuntimePermission(); // Don't try to access files from here below

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container, MainFragment.newInstance())
//                    .commitNow();
//        }
    }

    private void executeRuntimePermission() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // Needs to ask permission runtime after M
            if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestStoragePermission();
            } else {
                setRecyclerView();
                Toast.makeText(this, "Permission all went through!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void requestStoragePermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.permission_title)
                    .setMessage(R.string.permission_info)
                    .setPositiveButton(R.string.allow, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_REQUEST);
                        }
                    })
                    .setNegativeButton(R.string.deny, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == EXTERNAL_STORAGE_PERMISSION_REQUEST) {
            if(grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                // Permission denied
                Toast.makeText(this, R.string.exit_app, Toast.LENGTH_SHORT).show();
                finish();
            } else {
                setRecyclerView();
                Toast.makeText(this, "Permission all went through!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void setRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        songList =  MediaHelper.getMusicFromStorage(this);
        SongListAdapter adapter = new SongListAdapter(this, songList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
