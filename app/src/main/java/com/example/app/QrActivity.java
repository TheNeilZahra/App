package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class QrActivity extends AppCompatActivity {

    DBHelper DB;

    ImageButton homeButton, couponsButton, rewardButton;

    TextView pointsTextView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        DB = new DBHelper(this);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");

        int points = DB.getPoints(email);

        pointsTextView = findViewById(R.id.points);
        pointsTextView.setText("Available points: " + points);


        String emailAddress = email;

        ImageView qrCodeImageView = findViewById(R.id.qr_code_image_view);

        generateQRCode(emailAddress, qrCodeImageView);

        homeButton = findViewById(R.id.homeButton);
        couponsButton = findViewById(R.id.couponsButton);
        rewardButton = findViewById(R.id.rewardButton2);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }

        });

        couponsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CouponActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("email",email);
                startActivity(intent);
            }

        });

        rewardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RewardActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("email",email);
                startActivity(intent);
            }

        });
    }

    public void generateQRCode(String emailAddress, ImageView qrCodeImageView) {
        QRCodeWriter writer = new QRCodeWriter();
        try {
            // encode the email address as a QR code
            BitMatrix bitMatrix = writer.encode(emailAddress, com.google.zxing.BarcodeFormat.QR_CODE, 512, 512);

            // create a bitmap from the QR code
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }

            // display the QR code in the ImageView
            qrCodeImageView.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
