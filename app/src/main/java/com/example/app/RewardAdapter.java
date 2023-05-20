package com.example.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RewardAdapter extends RecyclerView.Adapter<RewardItemViewHolder> {
    private List<Reward> rewards;
    private String userEmail;
    public RewardAdapter(List<Reward> rewards) {
        this.rewards = rewards;
    }

    @NonNull
    @Override
    public RewardItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.reward_item, parent, false);
        return new RewardItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RewardItemViewHolder holder, int position) {
        Reward reward = rewards.get(position);
        holder.bind(reward);
    }

    @Override
    public int getItemCount() {
        return rewards.size();
    }

}

