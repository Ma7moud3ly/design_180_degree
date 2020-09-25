package com.ma7moud3ly.a180degree.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ma7moud3ly.a180degree.R;
import com.ma7moud3ly.a180degree.MainActivity;
import com.ma7moud3ly.a180degree.databinding.FragmentProfileBinding;
import com.ma7moud3ly.a180degree.models.LoginViewModel;
import com.ma7moud3ly.a180degree.models.ProfileViewModel;
import com.ma7moud3ly.a180degree.network.ImageRequester;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private LoginViewModel loginViewModel;
    private ProfileViewModel profileViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        //attach loginViewModel and ProfileViewModel to the fragment
        loginViewModel = new ViewModelProvider(requireActivity(), ViewModelProvider.
                AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(LoginViewModel.class);
        profileViewModel = new ViewModelProvider(requireActivity(), ViewModelProvider.
                AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(ProfileViewModel.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //attach the liveData object isLoggedIn to the fragment to stay aware with user login state..
        loginViewModel.isLoggedIn.observe(getViewLifecycleOwner(), loggedIn -> {
            if (!loggedIn)//if user not logged in  navigate to login fragment
                ((MainActivity) getActivity()).navigateTo(new LoginFragment(), null, false, true);
        });
        //request user profile data form the server  if user loggedIn and token provided..
        loginViewModel.userToken.observe(getViewLifecycleOwner(), token -> {
            if (token != null && !token.isEmpty())//if token not null
                profileViewModel.getProfile(token);
        });

        //when userProfile liveData object receives profile updates..
        profileViewModel.userProfile.observe(getViewLifecycleOwner(), profile -> {
            if (profile != null) {
                //update user name
                binding.userName.setText(profile.data.name);
                //load and cache user avatar to imageView with volley imageLoader..
                ImageRequester.getInstance(getActivity().getApplicationContext()).
                        setImageFromUrl(binding.userAvatar, profile.data.avatar, R.drawable.avatar_temp);
            }
        });

        //change header title to PROFILE with view binding.
        binding.header.headerTitle.setText(getString(R.string.profile));

        //listen to home and logout buttons..
        binding.btnHome.setOnClickListener(v -> ((MainActivity) getActivity()).navigateTo(new HomeFragment(), null, true, true));
        binding.btnLogOut.setOnClickListener(v -> logout());

    }

    //clear login token in loginViewModel..
    private void logout() {
        loginViewModel.userToken.setValue("");
        loginViewModel.isLoggedIn.setValue(false);
    }
}
