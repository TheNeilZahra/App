package com.example.app;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RewardsFragment extends Fragment {

    private List<Reward> lRewards;
    private RewardAdapter lAdapter;
    private RecyclerView lRecyclerView;
    private TextView lEmptyView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rewards, container, false);

        // Set up the RecyclerView
        lRecyclerView = view.findViewById(R.id.reward_recycler_view);
        lRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        lEmptyView = view.findViewById(R.id.empty_view);

        // Get the rewards data
        lRewards = getRewards();

        // Set up the adapter
        lAdapter = new RewardAdapter(lRewards);
        lRecyclerView.setAdapter(lAdapter);

        // Show or hide the empty view depending on the number of Rewards
        if (lRewards.isEmpty()) {
            lRecyclerView.setVisibility(View.GONE);
            lEmptyView.setVisibility(View.VISIBLE);
        } else {
            lRecyclerView.setVisibility(View.VISIBLE);
            lEmptyView.setVisibility(View.GONE);
        }


        return view;
    }

    private List<Reward> getRewards() {
        // Get the reward names and descriptions from resources
        String[] rewardNames = getResources().getStringArray(R.array.reward_names);
        String[] rewardDescriptions = getResources().getStringArray(R.array.reward_descriptions);
        int[] rewardPoints = getResources().getIntArray(R.array.reward_points);

        // Get the reward icons from resources
        TypedArray rewardIcons = getResources().obtainTypedArray(R.array.reward_icons);

        // Create a list of Reward objects
        List<Reward> rewards = new ArrayList<>();
        for (int i = 0; i < rewardNames.length; i++) {
            Reward reward = new Reward(rewardNames[i], rewardDescriptions[i], rewardPoints[i], rewardIcons.getResourceId(i, 0));
            rewards.add(reward);
        }

        // Recycle the TypedArray
        rewardIcons.recycle();

        return rewards;
    }


}
