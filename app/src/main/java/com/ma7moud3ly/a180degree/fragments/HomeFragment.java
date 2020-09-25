package com.ma7moud3ly.a180degree.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ma7moud3ly.a180degree.R;
import com.ma7moud3ly.a180degree.MainActivity;
import com.ma7moud3ly.a180degree.databinding.FragmentHomeBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflate fragment view with data binding
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //change header title to HOME with view binding.
        binding.header.headerTitle.setText(getString(R.string.home));
        //update products and sold items with data binding
        binding.setProducts(1000);
        binding.setSoldItems(300);
        //listen to profile button to navigate to profile page
        binding.footer.profile.setOnClickListener(v -> ((MainActivity) getActivity()).navigateTo(new ProfileFragment(), null, true, true));

    }
}
