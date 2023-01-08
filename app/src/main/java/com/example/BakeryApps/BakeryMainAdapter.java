package com.example.BakeryApps;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class BakeryMainAdapter extends FragmentStateAdapter {

    public BakeryMainAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new OfferFragment();
            case 1 :
                return  new BuyFragment();

            default:
                return new OfferFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
