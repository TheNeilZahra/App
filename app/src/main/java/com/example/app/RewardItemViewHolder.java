package com.example.app;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import android.graphics.Paint;


public class RewardItemViewHolder extends RecyclerView.ViewHolder {
    private ImageView iconImageView;
    private TextView nameTextView;
    private TextView descriptionTextView;

    private TextView pointsTextView;
    private Button redeemButton;

    DBHelper DB;

    public RewardItemViewHolder(View itemView) {
        super(itemView);
        iconImageView = itemView.findViewById(R.id.reward_image);
        nameTextView = itemView.findViewById(R.id.reward_title);
        descriptionTextView = itemView.findViewById(R.id.reward_description);
        pointsTextView = itemView.findViewById(R.id.reward_points);
        redeemButton = itemView.findViewById(R.id.redeem_button);
        DB = new DBHelper(itemView.getContext());
    }

    @SuppressLint("SetTextI18n")
    public void bind(Reward reward) {

        iconImageView.setImageResource(R.drawable.rewardimg);
        nameTextView.setText(reward.getName());
        descriptionTextView.setText(reward.getDescription());
        pointsTextView.setText("Reqiured points: " + reward.getRequiredPoints());

        int userPoints = DB.getPoints(GlobalVariables.email);

        if (reward.getRequiredPoints() > userPoints) {
            // Reward requires more points than the user has
            redeemButton.setEnabled(false);
            redeemButton.setText("Redeem");
            redeemButton.setPaintFlags(redeemButton.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            // Reward can be redeemed
            redeemButton.setEnabled(true);
            redeemButton.setText("Redeem");
        }

        redeemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rewardName = reward.getName();
                String rewardDescription = reward.getDescription();
                // Call the method to generate QR code and pass the reward name
                generateQRCode(rewardName, rewardDescription);
            }
        });
    }

    public void generateQRCode(String data, String rewardName) {
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

            // Set the reward name as the dialog title
            TextView titleTextView = dialogView.findViewById(R.id.qr_title);
            titleTextView.setText(rewardName);

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


