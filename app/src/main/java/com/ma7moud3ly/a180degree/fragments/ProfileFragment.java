package com.ma7moud3ly.a180degree.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ma7moud3ly.a180degree.R;
import com.ma7moud3ly.a180degree.MainActivity;
import com.ma7moud3ly.a180degree.databinding.FragmentProfileBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //change header title to PROFILE with view binding.
        binding.header.headerTitle.setText(getString(R.string.profile));
        //listen to home and logout buttons..
        binding.btnHome.setOnClickListener(v -> ((MainActivity) getActivity()).navigateTo(new HomeFragment(), null, true, true));
        binding.btnLogOut.setOnClickListener(v -> ((MainActivity) getActivity()).navigateTo(new LoginFragment(), null, true, true));

    }
}
