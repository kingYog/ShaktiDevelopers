package com.yogeshborhade.shaktidevelopers;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.support.v4.print.PrintHelper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ReadPDF extends AppCompatActivity {
    public static final int requestcode = 1;
    String path;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_pdf);
        button = (Button) findViewById(R.id.printPDf);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*              Intent intent = new Intent(Intent.ACTION_GET_CONTENT);*/
//                intent.setType("*/*");
                /*                startActivityForResult(intent, requestcode);*/



            }
        });
    }

    public static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }


    @Override
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


                    if (uriString.endsWith("jpg")) {
                        loadImageFromStorage(displayName);

                    } else {
                        Toast.makeText(ReadPDF.this, "File Not Found", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
        }
    }

    private void doPhotoPrint() {
        PrintHelper photoPrinter = new PrintHelper(ReadPDF.this);
        photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.date_picker);
        photoPrinter.printBitmap("droids.jpg - test print", bitmap);
    }

    private void loadImageFromStorage(String filename) {

        PrintHelper photoPrinter = new PrintHelper(ReadPDF.this);
        photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);

        try {
            final File file = new File(Environment.getExternalStorageDirectory()
                    .getAbsolutePath(), filename);
            //  FileInputStream myInput = new FileInputStream(file);

            //  File f=new File(path, "profile.jpg");
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            photoPrinter.printBitmap("droids.jpg - test print", bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
