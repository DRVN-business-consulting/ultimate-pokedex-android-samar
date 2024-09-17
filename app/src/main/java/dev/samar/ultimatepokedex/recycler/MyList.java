//package dev.samar.ultimatepokedex.recycler;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MyList extends AppCompatActivity implements MyAdapter.OnItemClickListener {
//
//    RecyclerView recyclerView;
//    MyAdapter myAdapter;
//    List<Pokemon> pokemonList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_list);
//
//        // Initialize the Pokémon list
//        pokemonList = new ArrayList<>();
//        pokemonList.add(new Pokemon(1, "Pikachu", R.drawable.pikachu, R.drawable.electric, "Pikachu are small, and cute mouse-like Pokémon. They are almost completely covered by yellow fur. They have long yellow ears that are tipped with black."));
//        pokemonList.add(new Pokemon(2, "Charizard", R.drawable.squirtle, R.drawable.fire, "Charmander is a bipedal, reptilian Pokémon. Most of its body is colored orange, while its underbelly is light yellow and it has blue eyes. It has a flame at the end of its tail, which is said to signify its health."));
//        pokemonList.add(new Pokemon(3, "Bulbasaur", R.drawable.bulbasaur, R.drawable.grass, "Bulbasaur are small, amphibian and plant Pokémon that move on all four legs. They have blue-green bodies with darker blue-green spots. The seed on a Bulbasaur's back is planted at birth and then sprouts and grows along with it. The bulb absorbs sunlight which allows it to grow."));
//
//        // Handle window insets for edge-to-edge display
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        // Set up the RecyclerView
//        recyclerView = findViewById(R.id.recyclerView);
//        myAdapter = new MyAdapter(pokemonList, this); // Pass the click listener here
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(myAdapter);
//    }
//
//
//    @Override
//    public void onItemClick(Pokemon pokemon) {
//        Intent intent = new Intent(MyList.this, Description.class);
//        intent.putExtra("pokemonId", pokemon.getId());
//        startActivity(intent);
//    }
//}
