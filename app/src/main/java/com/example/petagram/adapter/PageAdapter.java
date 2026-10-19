package com.example.petagram.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.petagram.fragment.PerfilFragment;
import com.example.petagram.fragment.RecyclerViewFragment;

/**
 * Adaptador del ViewPager: entrega el Fragment de cada pestaña.
 */
public class PageAdapter extends FragmentStateAdapter {

    public PageAdapter(@NonNull FragmentActivity activity) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new RecyclerViewFragment();
        }
        return new PerfilFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
