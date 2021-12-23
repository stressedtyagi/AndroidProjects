/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 */
package com.example.android.justjava;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int q=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#00695C"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        if(q > 0) {
            String priceMessage = "Your bill is ₹ " + (q * 5) + "\nThank You for Shopping :) ";

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, "dduc@gmail.com");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order");
            intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }else {
            Toast.makeText(this,"You Better Buy Some Coffee before ordering .. -_- ..",Toast.LENGTH_LONG).show();
        }
    }

    /**
     * This method is called when the map button is clicked.
     */
    public void goMap(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:26.804254, 94.688615"));
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

    /**
     * This method is called to increment the value of order when clicked.
     */
    public void increment(View view) {
        q = q +1;
        display(q);
    }

    /**
     * This method is called to decrement the value of order when clicked.
     */
    public void decrement(View view) {
        q = q-1;

        if (q < 0){
            Toast.makeText(this,"Sorry there are no Negative amount of Coffee Here .. -_- ..",Toast.LENGTH_LONG).show();
            q = 0;
            display(q);
        }

        display(q);
    }

    public void reset(View view){
        q = 0;
        Toast.makeText(this,"Content Reseted",Toast.LENGTH_LONG).show();
        display(q);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

//    /**
//     * This method displays the given price on the screen.
//     */
//    private void displayPrice(int number) {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
//    }
//
//    /**
//     * This method displays the given text on the screen.
//     */
//    private void displayMessage(String message) {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(message);
//    }
}