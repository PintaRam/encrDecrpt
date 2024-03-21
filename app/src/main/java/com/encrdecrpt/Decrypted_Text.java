package com.encrdecrpt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class Decrypted_Text extends AppCompatActivity {

    ImageView dcrimg;
    TextView tv;
    Button dcr_btn;
    private static final int img_Req_Code = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypted_text);

        dcr_btn = findViewById(R.id.decryptbtn);
        tv = findViewById(R.id.res);
        dcrimg = findViewById(R.id.encr_img);

        dcrimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent igallery = new Intent(Intent.ACTION_PICK);
                igallery.setType("image/*");
                startActivityForResult(igallery, img_Req_Code);
            }
        });

        dcr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bit_img = getBit(dcrimg);
                if (bit_img != null) {
                    String text = extractTextFromImage(bit_img);
                    tv.setText(text);
                    Toast.makeText(Decrypted_Text.this, "Decryption Successfull !!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Decrypted_Text.this, "Decryption Failed ..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private static Bitmap getBit(ImageView iv) {
        Drawable drawable = iv.getDrawable();
        if (drawable != null && drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else
            return null;
    }

    public static String extractTextFromImage(Bitmap modifiedBitmap) {
        StringBuilder extractedText = new StringBuilder();
        int width = modifiedBitmap.getWidth();
        int height = modifiedBitmap.getHeight();

        char extractedChar = 0;
        int bitIndex = 0;

        // Iterate over each pixel
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = modifiedBitmap.getPixel(x, y);

                // Extract RGB components
                int red = Color.red(pixel);
                int green = Color.green(pixel);
                int blue = Color.blue(pixel);

                // Extract the LSB of each color component
                extractedChar |= ((red & 0x01) << (bitIndex % 8));
                bitIndex++;
                extractedChar |= ((green & 0x01) << (bitIndex % 8));
                bitIndex++;
                extractedChar |= ((blue & 0x01) << (bitIndex % 8));
                bitIndex++;

                // If 8 bits are accumulated, append the character to the result
                if (bitIndex % 8 == 0) {
                    // Check for null terminator
                    if (extractedChar == '\0') {
                        return extractedText.toString();
                    }
                    extractedText.append(extractedChar);
                    extractedChar = 0;
                }
            }
        }

        return extractedText.toString();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == img_Req_Code) {

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                    dcrimg.setImageBitmap(bitmap);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}