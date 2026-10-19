package com.example.petagram;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.petagram.adapter.PageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager = findViewById(R.id.viewPager);

        // ViewPager con los Fragments: lista de mascotas y perfil
        viewPager.setAdapter(new PageAdapter(this));

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 0) {
                tab.setIcon(R.drawable.ic_casa);
                tab.setText(R.string.tab_inicio);
            } else {
                tab.setIcon(R.drawable.ic_huella);
                tab.setText(R.string.tab_perfil);
            }
        }).attach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_favoritos) {
            startActivity(new Intent(this, FavoritosActivity.class));
            return true;
        } else if (id == R.id.action_contacto) {
            startActivity(new Intent(this, ContactoActivity.class));
            return true;
        } else if (id == R.id.action_acerca) {
            startActivity(new Intent(this, AcercaDeActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
