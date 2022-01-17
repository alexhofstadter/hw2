package com.example.alexcars;


import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CarAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(manager);



        List<Cars> cars = new ArrayList<Cars>();
        cars.add(new Cars(R.drawable.audia8, "Audi", "A8", "2020", "$95,900"));
        cars.add(new Cars(R.drawable.audietron, "Audi", "E-Tron", "2021","$65,900"));
        cars.add(new Cars(R.drawable.audiq8, "Audi", "Q8", "2021","$96,200"));
        cars.add(new Cars(R.drawable.bmw7series, "BMW", "7 Series", "2020","$95,900"));
        cars.add(new Cars(R.drawable.bmwx7, "BMW", "X7", "2022","$74,900"));
        cars.add(new Cars(R.drawable.mercedesbenz, "Mercedez", "Benz S Class", "2021","$109,800"));
        cars.add(new Cars(R.drawable.mercedescls, "Mercedez", "C LS", "2020","$70,300"));
        cars.add(new Cars(R.drawable.rangerrover, "Land Rover", "Range Rover", "2021","$100,000"));

        adapter = new CarAdapter(cars);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper helper = new ItemTouchHelper(new SwipeToDelete(adapter));
        helper.attachToRecyclerView(recyclerView);

        FloatingActionButton btn = findViewById(R.id.fbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),AddCar.class);
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) v.getContext(),
                                btn,
                                "bg"
                        );
                startActivityForResult(i,1,options.toBundle());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==1) {
            Bundle b = data.getExtras();
            Cars car = (Cars) b.getSerializable("cars");
            adapter.AddCar(car);
        }

    }

}