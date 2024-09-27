package com.example.assignment1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CheckBox fazlee, langra, gopalbhog, khirsapat;
    private TextView mangoVarieties, quantityTextView, priceTextView, ratingTextView;
    private Button increment, decrement, orderBtn;
    private RatingBar ratingBar;

    private int quantity = 0;
    private static final int PRICE_PER_KG = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fazlee = findViewById(R.id.fazlee);
        langra = findViewById(R.id.langra);
        gopalbhog = findViewById(R.id.gopalbhog);
        khirsapat = findViewById(R.id.khirsapat);

        mangoVarieties = findViewById(R.id.mango_varieties);
        quantityTextView = findViewById(R.id.quantityTextView);
        priceTextView = findViewById(R.id.priceTextView);
        ratingTextView = findViewById(R.id.rating);

        increment = findViewById(R.id.increment);
        decrement = findViewById(R.id.decrement);
        orderBtn = findViewById(R.id.order_btn);

        ratingBar = findViewById(R.id.ratingBar);

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementQuantity();
            }
        });

        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementQuantity();
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingTextView.setText("Rating: " + rating);
            }
        });

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
            }
        });
    }

    private void incrementQuantity() {
        quantity++;
        displayQuantity();
        calculatePrice();
    }

    private void decrementQuantity() {
        if (quantity > 0) {
            quantity--;
            displayQuantity();
            calculatePrice();
        }
    }

    private void displayQuantity() {
        quantityTextView.setText(String.valueOf(quantity));
    }

    private void calculatePrice() {
        int totalPrice = quantity * PRICE_PER_KG;
        priceTextView.setText("BDT " + totalPrice);
    }

    private void placeOrder() {
        StringBuilder selectedMangoes = new StringBuilder("Selected Mangoes: ");
        if (fazlee.isChecked()) selectedMangoes.append("Fazlee ");
        if (langra.isChecked()) selectedMangoes.append("Langra ");
        if (gopalbhog.isChecked()) selectedMangoes.append("Gopalbhog ");
        if (khirsapat.isChecked()) selectedMangoes.append("Khirsapat ");

        if (selectedMangoes.toString().equals("Selected Mangoes: ")) {
            selectedMangoes.append("None");
        }

        mangoVarieties.setText(selectedMangoes.toString());

        String orderSummary = selectedMangoes.toString() + "\nQuantity: " + quantity + " kg\nTotal Price: BDT " + (quantity * PRICE_PER_KG);
        Toast.makeText(this, orderSummary, Toast.LENGTH_LONG).show();
        Toast.makeText(this, "Thank you for your order!", Toast.LENGTH_SHORT).show();
    }
}