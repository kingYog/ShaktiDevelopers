package com.yogeshborhade.shaktidevelopers.SaveDatabase;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yogeshborhade.shaktidevelopers.Database.Constants;
import com.yogeshborhade.shaktidevelopers.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import static com.yogeshborhade.shaktidevelopers.Database.Constants.SAVED_DATABASEPATH;

public class SaveDatabase extends AppCompatActivity {

    public static final int requestcode = 1;
    Button buttonSaveDatabase, buttonImportDatabse;
    String databaseName;
    public static String BackUpPath;
    Context context;
    public static String importingDatabaseFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_database);

        context = SaveDatabase.this;
        buttonSaveDatabase = (Button) findViewById(R.id.mSaveDatabase);
        buttonImportDatabse = (Button) findViewById(R.id.ImportDatabase);


        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + SAVED_DATABASEPATH);
        if (myDir.exists()) {
            //  Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show();
        } else {
            myDir.mkdirs();
        }

        buttonSaveDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exportDB(Constants.DATABASE_NAME_SHAKTI_DEVELOPER + Constants.getDate());
            }
        });

        buttonImportDatabse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, requestcode);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case 1:
                if (resultCode == RESULT_OK) {

                    Uri uri = data.getData();
                    String uriString = uri.toString();
                    File myFile = new File(uriString);
                    String path = myFile.getAbsolutePath();
                    String displayName = null;

                    if (uriString.startsWith("content://")) {
                        Cursor cursor = null;
                        try {
                            cursor = getContentResolver().query(uri, null, null, null, null);
                            if (cursor != null && cursor.moveToFirst()) {
                                displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                                Log.e(" If", displayName);
                            }
                        } finally {
                            cursor.close();
                        }

                    } else if (uriString.startsWith("file://")) {
                        displayName = myFile.getName();
                        Log.e("Else If", displayName);
                    }

                    importDB(displayName);

                } else {
                    Toast.makeText(SaveDatabase.this, "File Not Found", Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }


    private void exportDB(String db_name) {


        File sd = new File(Environment.getExternalStorageDirectory().toString()
                + SAVED_DATABASEPATH + "/");

        boolean success = true;
        if (!sd.exists()) {
            success = sd.mkdir();
        }
        if (success) {

            File data = Environment.getDataDirectory();
            FileChannel source = null;
            FileChannel destination = null;
            String currentDBPath = "/data/" + context.getPackageName() + "/databases/" + Constants.DATABASE_NAME_SHAKTI_DEVELOPER;
            String backupDBPath = db_name;
            File currentDB = new File(data, currentDBPath);
            File backupDB = new File(sd, backupDBPath);
            try {

                source = new FileInputStream(currentDB).getChannel();
                destination = new FileOutputStream(backupDB).getChannel();
                destination.transferFrom(source, 0, source.size());
                source.close();
                destination.close();
                Toast.makeText(this, "Please wait", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void importDB(String db_name) {
        File sd = new File(Environment.getExternalStorageDirectory().toString()
                + SAVED_DATABASEPATH + "/");

        File data = Environment.getDataDirectory();
        FileChannel source = null;
        FileChannel destination = null;
        String backupDBPath = "/data/" + context.getPackageName() + "/databases/" + Constants.DATABASE_NAME_SHAKTI_DEVELOPER;
        String currentDBPath = db_name;
        File currentDB = new File(sd, currentDBPath);
        File backupDB = new File(data, backupDBPath);
        try {
            source = new FileInputStream(currentDB).getChannel();
            destination = new FileOutputStream(backupDB).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();
            Toast.makeText(this, "Please wait", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
