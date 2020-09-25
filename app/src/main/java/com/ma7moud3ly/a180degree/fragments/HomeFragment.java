package com.ma7moud3ly.a180degree.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ma7moud3ly.a180degree.R;
import com.ma7moud3ly.a180degree.MainActivity;
import com.ma7moud3ly.a180degree.databinding.FragmentHomeBinding;
import com.ma7moud3ly.a180degree.models.HomeViewModel;
import com.ma7moud3ly.a180degree.models.LoginViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private LoginViewModel loginViewModel;
    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflate fragment view with data binding
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        //attach loginViewModel and HomeViewModel to the fragment
        loginViewModel = new ViewModelProvider(requireActivity(), ViewModelProvider.
                AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(LoginViewModel.class);
        homeViewModel = new ViewModelProvider(requireActivity(), ViewModelProvider.
                AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(HomeViewModel.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //observe liveData object isLoggedIn to the fragment to stay aware with user login state..
        loginViewModel.isLoggedIn.observe(getViewLifecycleOwner(), loggedIn -> {
            if (!loggedIn)//if user not logged in  navigate to login fragment
                ((MainActivity) getActivity()).navigateTo(new LoginFragment(), null, false, true);
        });
        //request sold items and products form the server if user logged in an token provided..
        loginViewModel.userToken.observe(getViewLifecycleOwner(), token -> {
            if (token != null && !token.isEmpty()) {//if token not null
                homeViewModel.getSoldItems(token);
                homeViewModel.getProducts(token);
            }
        });

        //observe the requested soldItems to update the ui.
        homeViewModel.soldItems.observe(getViewLifecycleOwner(), soldItems -> {
            if (soldItems != null)
                binding.setSoldItems(soldItems);
        });

        //observe the requested products to update the ui.
        homeViewModel.products.observe(getViewLifecycleOwner(), products -> {
            if (products != null)
                binding.setProducts(products);
        });

        //change header title to (HOME) with view binding.
        binding.header.headerTitle.setText(getString(R.string.home));
        //listen to profile button to navigate to profile fragment
        binding.footer.profile.setOnClickListener(v -> {
            ((MainActivity) getActivity()).navigateTo(new ProfileFragment(), null, true, true);
        });

    }
}
