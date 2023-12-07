package com.example.dialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    // 3- create a arraylist for store the data
    ArrayList<ProductModel> product = new ArrayList<>();
    RecyclerView recyclerView;


    // create a object of the toolbar;

    Toolbar toolbar;


    // work with floating button need to find the id
    FloatingActionButton fbActionFloatingbtn ;


    RecyclerProductAdaptor recyclerProductAdaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // attached the recycler view id give into the main activity
        recyclerView = findViewById(R.id.rvProduct);

        // then set the layout you want to show into recycler view
        recyclerView.setLayoutManager(new GridLayoutManager(this , 2));







        // setup the toolbar
        toolbar = findViewById(R.id.toolbar);

        //STEP 1
        setSupportActionBar(toolbar);


        //step 2 --> by default give the backbutotn

        if( getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setTitle("My App");
        toolbar.setSubtitle("Jawad Mughal");



        // need to create new Dialog box
        fbActionFloatingbtn = findViewById(R.id.fbActionFloatingbtn);
        fbActionFloatingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // 2- step
                Dialog dialog = new Dialog(MainActivity.this);

                // 3- attached the layout
                dialog.setContentView(R.layout.dialoglayoutforupdateproduct);


                //Find ids becuase we want to get data from
                TextView tvProductFormName = dialog.findViewById(R.id.tvProductFormName);
                EditText etProductName = dialog.findViewById(R.id.etProductName);
                EditText etProductPrice = dialog.findViewById(R.id.etProductPrice);
                Button btnSubmitProduct = dialog.findViewById(R.id.btnSubmitProduct);


                btnSubmitProduct.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = "" , price = "";

                        if(!etProductName.getText().toString().equals(""))
                            name = etProductName.getText().toString();
                        else
                            Toast.makeText(MainActivity.this,
                                    "Please Enter Product Name", Toast.LENGTH_SHORT).show();

                        if(!etProductPrice.getText().toString().equals(""))
                            price = etProductPrice.getText().toString();
                        else
                            Toast.makeText(MainActivity.this,
                                    "Please Enter Product Name", Toast.LENGTH_SHORT).show();


                        product.add(new ProductModel(name, price , R.drawable.e));
                        recyclerProductAdaptor.notifyItemInserted(product.size()-1);
                        recyclerView.scrollToPosition(product.size()-1);
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }


        });
        // every important step to create the show dialog



        // Create productModel instances with random data and different drawables
        product.add(new ProductModel(R.drawable.e , "Product 1" , "Rs 800", "Rs 300", 4.4F));
        product.add(new ProductModel(R.drawable.e , "Product 2" , "Rs 600", "Rs 250", 4.0F));
        product.add(new ProductModel(R.drawable.e , "Product 3" , "Rs 900", "Rs 400", 4.8F));
        product.add(new ProductModel(R.drawable.e , "Product 4" , "Rs 700", "Rs 350", 4.2F));
        product.add(new ProductModel(R.drawable.e , "Product 5" , "Rs 1000", "Rs 500", 4.5F));
        product.add(new ProductModel(R.drawable.e , "Product 6" , "Rs 1200", "Rs 600", 4.6F));
        product.add(new ProductModel(R.drawable.e , "Product 7" , "Rs 500", "Rs 200", 3.9F));
        product.add(new ProductModel(R.drawable.e , "Product 8" , "Rs 1100", "Rs 450", 4.7F));
        product.add(new ProductModel(R.drawable.e , "Product 9" , "Rs 850", "Rs 320", 4.3F));
        product.add(new ProductModel(R.drawable.e , "Product 10", "Rs 950", "Rs 380", 4.1F));

         recyclerProductAdaptor = new RecyclerProductAdaptor(this, product);
        recyclerView.setAdapter(recyclerProductAdaptor);

    }





    // this menu is the reference where we need to show the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.menu_opt, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.open)
            Toast.makeText(this, "Open Menu", Toast.LENGTH_SHORT).show();
        else if(itemId == R.id.close)
            Toast.makeText(this, "Close  Menu", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Other  Menu", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}