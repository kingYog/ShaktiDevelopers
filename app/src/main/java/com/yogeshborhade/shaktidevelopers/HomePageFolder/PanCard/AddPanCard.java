package com.yogeshborhade.shaktidevelopers.HomePageFolder.PanCard;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.yogeshborhade.shaktidevelopers.Database.DatabaseHelper;
import com.yogeshborhade.shaktidevelopers.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static com.yogeshborhade.shaktidevelopers.Database.Constants.CUSTOMER_PAN_CARD_IMAGE_PATH;

public class AddPanCard extends AppCompatActivity {
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private Button btnSelect, btnSaveImageToDatabase, backButton;
    private ImageView ivImage;
    private String userChoosenTask;

    String ImageName = "panCardOne";
    String customerID, customerMobileNumber, customerPanCardImagePath;

    File destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pan_card);


        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();


        btnSelect = (Button) findViewById(R.id.btnSelectPhoto);
        btnSaveImageToDatabase = (Button) findViewById(R.id.mbuttonSaveImage);
        ivImage = (ImageView) findViewById(R.id.ivImage);
        backButton = (Button) findViewById(R.id.mbackButton);

        // get Values From Intent
        Bundle bundle = getIntent().getExtras();
        customerID = bundle.getString("customerID", "0");
        customerMobileNumber = bundle.getString("customerMob", "0");
        customerPanCardImagePath = bundle.getString("customerPanCardImagePath", "0");

        Log.e(customerMobileNumber, customerID + customerPanCardImagePath);

        ImageName = customerMobileNumber;

        if (customerPanCardImagePath.equals("NA") || customerPanCardImagePath.equals("")) {
            Toast.makeText(this, "Pan Card Not Found Please Add Pan Card Here", Toast.LENGTH_SHORT).show();
        } else {
            Uri imgUri = Uri.parse(String.valueOf(customerPanCardImagePath));
            ivImage.setImageURI(imgUri);
        }


        // Create Directory
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + CUSTOMER_PAN_CARD_IMAGE_PATH);
        if (myDir.exists()) {
            //  Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show();
        } else {
            myDir.mkdirs();
        }


        btnSelect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                selectImage();
            }
        });


        btnSaveImageToDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("Destination", String.valueOf(destination));
                if (String.valueOf(destination).equals("null")) {
                    Toast.makeText(AddPanCard.this, "Please Select Image", Toast.LENGTH_SHORT).show();
                } else {
                    Uri imgUri = Uri.parse(String.valueOf(destination));
                    ivImage.setImageURI(imgUri);

                    DatabaseHelper databaseHelper = new DatabaseHelper(AddPanCard.this);
                    // Update Customer Value Here
                    long id = databaseHelper.CustomerUpdatePanCard(customerID, String.valueOf(destination));


                    if (id == 0) {
                        Toast.makeText(AddPanCard.this, "There is Some Errors", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddPanCard.this, "Updated Succefully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case UtilityPanCard.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(AddPanCard.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = UtilityPanCard.checkPermission(AddPanCard.this);

                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result)
                        cameraIntent();

                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        galleryIntent();

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent() {
        Intent intent = new Intent();

        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent() {
        /*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);*/


        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);

        destination = new File(Environment.getExternalStorageDirectory().toString()
                + CUSTOMER_PAN_CARD_IMAGE_PATH + "/" + ImageName + ".jpg");
        i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(destination));

        startActivityForResult(i, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    // OLD Code After Fetching Image

/*
    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        destination = new File(Environment.getExternalStorageDirectory().toString()
                + CUSTOMER_PAN_CARD_IMAGE_PATH + "/" + ImageName + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ivImage.setImageBitmap(thumbnail);
    }
*/

    // New Code After Fetching Image
    private void onCaptureImageResult(Intent data) {

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.fromFile(destination), "image/jpeg");
        ivImage.setImageURI(Uri.fromFile(destination));


    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ivImage.setImageBitmap(bm);

        Bitmap bitmap = bm;

        ContextWrapper wrapper = new ContextWrapper(getApplicationContext());

        destination = new File(Environment.getExternalStorageDirectory().toString()
                + CUSTOMER_PAN_CARD_IMAGE_PATH + "/" + ImageName + ".jpg");
        try {
            OutputStream stream = null;
            stream = new FileOutputStream(destination);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            stream.flush();
            stream.close();

        } catch (IOException e) // Catch the exception
        {
            e.printStackTrace();
        }

        // Parse the gallery image url to uri
        Uri savedImageURI = Uri.parse(destination.getAbsolutePath());
        Log.e("Saved File", String.valueOf(savedImageURI));

    }


}
