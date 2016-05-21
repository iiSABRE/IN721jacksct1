package com.example.jacksct1.photomosaic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    String mPhotoFileName;
    File mPhotoFile;
    Uri mPhotoFileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPhoto = (Button)findViewById(R.id.btnPhoto);
        btnPhoto.setOnClickListener(new TakePhotoBtn());


    }

    private class TakePhotoBtn implements View.OnClickListener {
        @Override
        public void onClick(View v) {

        mPhotoFile = createTimeStampFile();
        mPhotoFileUri = Uri.fromFile(mPhotoFile);

            Intent imageCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoFileUri);
            startActivityForResult(imageCaptureIntent, 1);

        }
    }

    public File createTimeStampFile()
    {
        File imageRootPAth = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File imageStorageDirectory = new File(imageRootPAth, "CameraDemo1");
        if(!imageStorageDirectory.exists())
        {
            imageStorageDirectory.mkdirs();
        }

        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date currentTime = new Date();
        String timeStamp = timeStampFormat.format(currentTime);

        mPhotoFileName = "IMG_" + timeStamp + ".jpg";

        File photoFile = new File(imageStorageDirectory.getPath() + File.separator + mPhotoFileName);

        return photoFile;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == 1)
        {
            if(resultCode == RESULT_OK)
            {
                String realFilePAth = mPhotoFile.getPath();
                Bitmap userPhotoBitmap = BitmapFactory.decodeFile(realFilePAth);

                ImageView iv1 = (ImageView) findViewById(R.id.iv1);
                ImageView iv2 = (ImageView) findViewById(R.id.iv2);
                ImageView iv3 = (ImageView) findViewById(R.id.iv3);
                ImageView iv4 = (ImageView) findViewById(R.id.iv4);

                iv1.setImageBitmap(userPhotoBitmap);
                iv2.setImageBitmap(userPhotoBitmap);
                iv3.setImageBitmap(userPhotoBitmap);
                iv4.setImageBitmap(userPhotoBitmap);


            }
            else
            {
                Toast.makeText(this, "No photo saved!", Toast.LENGTH_LONG).show();
            }

        }
    }
}
