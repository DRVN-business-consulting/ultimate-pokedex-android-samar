package dev.samar.ultimatepokedex;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BotNav extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botnav);

        // Initialize the BottomNavigationView and set the listener
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

        // Set default fragment when activity is first created
        if (savedInstanceState == null) {
            // Show PokemonListFragment by default
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new PokemonListFragment())
                    .commit();
        }
    }

    private boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;

        // Use if-else to determine the selected fragment
        if (item.getItemId() == R.id.nav_pokemon_list) {
            selectedFragment = new PokemonListFragment();
        } else if (item.getItemId() == R.id.nav_favorites) {
            selectedFragment = new FavoritesFragment();
        } else if (item.getItemId() == R.id.nav_trainer) {
            selectedFragment = new TrainerFragment();
        }

        // Replace the fragment with the selected fragment
        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit();
        }
        return true;
    }
}
