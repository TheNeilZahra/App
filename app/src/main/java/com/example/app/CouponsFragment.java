package com.example.app;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CouponsFragment extends Fragment {

    private List<Coupon> mCoupons;
    private CouponAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private TextView mEmptyView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coupons, container, false);

        // Set up the RecyclerView
        mRecyclerView = view.findViewById(R.id.coupons_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mEmptyView = view.findViewById(R.id.empty_view);

        // Get the coupons data
        mCoupons = getCoupons();

        // Set up the adapter
        mAdapter = new CouponAdapter(mCoupons);
        mRecyclerView.setAdapter(mAdapter);

        // Show or hide the empty view depending on the number of coupons
        if (mCoupons.isEmpty()) {
            mRecyclerView.setVisibility(View.GONE);
            mEmptyView.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            mEmptyView.setVisibility(View.GONE);
        }


        return view;
    }

    private List<Coupon> getCoupons() {
        // Get the coupon names and descriptions from resources
        String[] couponNames = getResources().getStringArray(R.array.coupon_names);
        String[] couponDescriptions = getResources().getStringArray(R.array.coupon_descriptions);

        // Get the coupon icons from resources
        TypedArray couponIcons = getResources().obtainTypedArray(R.array.coupon_icons);

        // Create a list of Coupon objects
        List<Coupon> coupons = new ArrayList<>();
        for (int i = 0; i < couponNames.length; i++) {
            Coupon coupon = new Coupon(couponNames[i], couponDescriptions[i], couponIcons.getResourceId(i, 0));
            coupons.add(coupon);
        }

        // Recycle the TypedArray
        couponIcons.recycle();

        return coupons;
    }


}
