package com.example.OnlineStationeryStore.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.OnlineStationeryStore.R;
import com.example.OnlineStationeryStore.adapter.ProductAdapter;
import com.example.OnlineStationeryStore.model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    //to display the items in a list view
    private List<Product> products = new ArrayList<>();
    //presents the data in a structured manner
    private ProductAdapter mAdapter;
    private RecyclerView recyclerView;
    //database reference is given to fetch data from the database
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        //set the activity title
        setTitle("Products");

        //initialize firebase reference
        //to fetch the items from the database
        databaseReference = FirebaseDatabase.getInstance().getReference("Product");

        //initializing the floating action button
        FloatingActionButton fab = findViewById(R.id.fabProduct);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProductAddActivity.class));
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        initAdapter();
        fetchProducts();

    }


    /**
     * initialize the adapter to display product details
     */
    private void initAdapter() {
        //using an adapter we can display the items in an array to a list view
        mAdapter = new ProductAdapter(ProductListActivity.this, products, new ProductAdapter.ProductItemListener() {
            @Override
            public void onProductItemClick(Product product) {
                Intent intent = new Intent(ProductListActivity.this, ProductEditActivity.class);
                intent.putExtra("productId", product.getId());
                startActivity(intent);
            }
        });
        //layout manager is used to position the items in a recyclerView
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }


    /**
     * Retrieve the product details from firebase
     */
    private void fetchProducts() {
        //attaching value event listener
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            // onDataChange() to read data frm the database
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                products.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    Product item = postSnapshot.getValue(Product.class);
                    //adding artist to the list-----when the product details are fetched from the database it will be displayed in the list
                    products.add(item);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
