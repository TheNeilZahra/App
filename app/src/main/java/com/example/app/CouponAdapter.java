package com.example.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CouponAdapter extends RecyclerView.Adapter<CouponItemViewHolder> {
    private List<Coupon> coupons;

    public CouponAdapter(List<Coupon> coupons) {
        this.coupons = coupons;
    }


    //Inflater to populate coupons Layout
    @NonNull
    @Override
    public CouponItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.coupon_item, parent, false);
        return new CouponItemViewHolder(view);
    }

    //Binding the coupons
    @Override
    public void onBindViewHolder(@NonNull CouponItemViewHolder holder, int position) {
        Coupon coupon = coupons.get(position);
        holder.bind(coupon);
    }

    @Override
    public int getItemCount() {
        return coupons.size();
    }
}

