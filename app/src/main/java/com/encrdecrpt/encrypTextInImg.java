package com.encrdecrpt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


import java.io.FileNotFoundException;
import java.io.IOException;

public class encrypTextInImg extends AppCompatActivity {
    private final int img_Req_Code = 1000;
    ImageView imggal,img2;
    Bitmap modifiedBitmap;
    EditText encredit;
    Button encrbtn, encrybtn2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryp_text_in_img);
        encrbtn = findViewById(R.id.encrbtn);
        encredit = findViewById(R.id.encredit);
        imggal = findViewById(R.id.imggal);
        img2 = findViewById(R.id.imggal1);
        encrybtn2 = findViewById(R.id.encrbtn2);
        encrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent igallery = new Intent(Intent.ACTION_PICK);
                igallery.setType("image/*");
                startActivityForResult(igallery, img_Req_Code);

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == img_Req_Code) {
//                try {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                     imggal.setImageBitmap(bitmap);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
                // Embed text into the image pixels
                    modifiedBitmap = getPixelData(bitmap);
                    img2.setImageBitmap(modifiedBitmap); // Set the modified image to the ImageView

                    // Pass the modified image to the next activity
//
                } catch (IOException e) {
                    e.printStackTrace();
                }
                encrybtn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent i = new Intent(encrypTextInImg.this, embedImgSaved.class);
////                        i.putExtra("modifiedbitmap", modifiedBitmap);
//                        startActivity(i);
                        saveImage(modifiedBitmap);
                    }
                });
            }
        }
    }
    private void saveImage(Bitmap bitmap) {
        // Define the directory where the image will be saved
        String directoryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        File directory = new File(directoryPath);

        // Create the directory if it doesn't exist
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Create a unique filename for the image
        String fileName = "saved_image_" + System.currentTimeMillis() + ".jpg";

        // Create the file object
        File file = new File(directory, fileName);

        try {
            // Write the bitmap to the file
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();

            // Show a toast message indicating the image has been saved
            Toast.makeText(this, "Image saved successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save image", Toast.LENGTH_SHORT).show();
        }
    }


    private Bitmap getPixelData(Bitmap bitmap) {
        String textToEmbed = encredit.getText().toString();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int charIndex = 0; // Index to keep track of the character being embedded

        // Create a new bitmap to hold the modified pixel data
        Bitmap modifiedBitmap = bitmap.copy(bitmap.getConfig(), true);

        // Iterate over each pixel
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (charIndex < textToEmbed.length()) {
                    int pixel = bitmap.getPixel(x, y);

                    // Extracting RGB components
                    int red = Color.red(pixel);
                    int green = Color.green(pixel);
                    int blue = Color.blue(pixel);

                    // Embedding text into the LSBs of RGB components
                    red = embedCharInColor(red, textToEmbed.charAt(charIndex++));
                    if (charIndex < textToEmbed.length()) {
                        green = embedCharInColor(green, textToEmbed.charAt(charIndex++));
                    }
                    if (charIndex < textToEmbed.length()) {
                        blue = embedCharInColor(blue, textToEmbed.charAt(charIndex++));
                    }

                    // Create the modified pixel
                    int modifiedPixel = Color.rgb(red, green, blue);
                    modifiedBitmap.setPixel(x, y, modifiedPixel);
                } else {
                    // If there are no more characters to embed, return the modified bitmap
                    return modifiedBitmap;
                }
            }
        }

        return modifiedBitmap;
    }

    private int embedCharInColor(int colorComponent, char charToEmbed) {
        // Embed a character into the least significant bits of the color component
        return (colorComponent & 0xFE) | (charToEmbed & 0x01);
    }
            }
      //  }
    //}}
