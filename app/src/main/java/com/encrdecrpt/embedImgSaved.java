package com.encrdecrpt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Environment;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class embedImgSaved extends AppCompatActivity {
    ImageView imageView;
    Button btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_embed_img_saved);
//        Bitmap bitmap = getIntent().getParcelableExtra("modifiedbitmap");
//        imageView = findViewById(R.id.imggalem);
//        imageView.setImageBitmap(bitmap);
//
//        btn = findViewById(R.id.button);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveImage(bitmap);
//            }
//        });
//    }
//    private void saveImage(Bitmap bitmap) {
//        // Define the directory where the image will be saved
//        String directoryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
//        File directory = new File(directoryPath);
//
//        // Create the directory if it doesn't exist
//        if (!directory.exists()) {
//            directory.mkdirs();
//        }
//
//        // Create a unique filename for the image
//        String fileName = "saved_image_" + System.currentTimeMillis() + ".jpg";
//
//        // Create the file object
//        File file = new File(directory, fileName);
//
//        try {
//            // Write the bitmap to the file
//            FileOutputStream outputStream = new FileOutputStream(file);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
//            outputStream.flush();
//            outputStream.close();
//
//            // Show a toast message indicating the image has been saved
//            Toast.makeText(this, "Image saved successfully", Toast.LENGTH_SHORT).show();
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "Failed to save image", Toast.LENGTH_SHORT).show();
//        }
//    }


    }
}