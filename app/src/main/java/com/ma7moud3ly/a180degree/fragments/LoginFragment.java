package com.ma7moud3ly.a180degree.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.ma7moud3ly.a180degree.MainActivity;
import com.ma7moud3ly.a180degree.R;
import com.ma7moud3ly.a180degree.databinding.FragmentLoginBinding;
import com.ma7moud3ly.a180degree.models.LoginViewModel;
import com.ma7moud3ly.a180degree.network.LoginService;
import com.ma7moud3ly.a180degree.network.VolleySingelton;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private LoginViewModel loginViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        //attach loginViewModel to the fragment..
        loginViewModel = new ViewModelProvider(requireActivity(), ViewModelProvider.
                AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(LoginViewModel.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //listen to login button to send login request with user name and password..
        binding.btnLogin.setOnClickListener(v -> {
            loginViewModel.login(binding.name.getText().toString(), binding.password.getText().toString());
        });
        //attach the liveData object isLoggedIn to the fragment to stay aware with user login state..
        loginViewModel.isLoggedIn.observe(getViewLifecycleOwner(), loggedIn -> {
            //when loggedIn becomes true, navigates to HomeFragment..
            if (loggedIn)
                ((MainActivity) getActivity()).navigateTo(new HomeFragment(), null, false, false);
        });
    }


}
