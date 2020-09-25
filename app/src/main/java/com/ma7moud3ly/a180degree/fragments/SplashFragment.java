package com.ma7moud3ly.a180degree.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ma7moud3ly.a180degree.MainActivity;
import com.ma7moud3ly.a180degree.databinding.FragmentSplashBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SplashFragment extends Fragment {
    private FragmentSplashBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSplashBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //navigate to login fragment after 2 seconds
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            ((MainActivity) getActivity()).navigateTo(new LoginFragment(), null, false, true);
        }, 2000);
    }
}
