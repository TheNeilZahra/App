package com.example.app;

import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;


public class CouponItemViewHolder extends RecyclerView.ViewHolder {
    private ImageView iconImageView;
    private TextView nameTextView;
    private TextView descriptionTextView;

    private Button redeemButton;

    public CouponItemViewHolder(View itemView) {
        super(itemView);
        iconImageView = itemView.findViewById(R.id.coupon_image);
        nameTextView = itemView.findViewById(R.id.coupon_title);
        descriptionTextView = itemView.findViewById(R.id.coupon_description);
        redeemButton = itemView.findViewById(R.id.redeem_button);
    }

    public void bind(Coupon coupon) {
        iconImageView.setImageResource(R.drawable.coupon);
        nameTextView.setText(coupon.getName());
        descriptionTextView.setText(coupon.getDescription());

        redeemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String couponName = coupon.getName();
                String couponDescription = coupon.getDescription();
                // Call the method to generate QR code and pass the coupon name
                generateQRCode(couponName, couponDescription);
            }
        });
    }

    public void generateQRCode(String data, String couponName) {
        // Create a QR code writer
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        try {
            // Encode the data into a QR code
            BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 400, 400);

            // Convert the BitMatrix into a Bitmap
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }

            // Show the QR code in a dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());

            // Set the custom view
            View dialogView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.qr_dialog, null);
            builder.setView(dialogView);

            // Get the views from the dialog view
            ImageView qrCodeImageView = dialogView.findViewById(R.id.qrCodeImageView);
            Button closeButton = dialogView.findViewById(R.id.closeButton);

            // Set the coupon name as the dialog title
            TextView titleTextView = dialogView.findViewById(R.id.qr_title);
            titleTextView.setText(couponName);

            // Set the generated QR code bitmap to the image view
            qrCodeImageView.setImageBitmap(bitmap);

            // Create the dialog
            AlertDialog dialog = builder.create();

            // Set the close button click listener
            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            // Show the dialog
            dialog.show();

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }


}


