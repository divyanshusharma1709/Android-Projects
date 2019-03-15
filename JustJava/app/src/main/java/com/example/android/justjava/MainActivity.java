/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */
package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    CheckBox whippedCreamCheckbox;
    CheckBox chocolateCheckBox;
    String finalOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public String createOrderSummary(int price) {
      whippedCreamCheckbox = (CheckBox) findViewById(R.id.whippedCream);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();
        chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        EditText name = (EditText) findViewById(R.id.name_editText_view);
        if(hasWhippedCream==true)
            price += 66 * quantity;
        if(hasChocolate==true)
            price += 132 * quantity;
        String customerName = name.getText().toString();
        return "\nName: " + customerName + "\nQuantity: " + quantity + "\nWhippedCream? " + hasWhippedCream + "\nChocolate? " + hasChocolate + "\nTotal: \u20B9" + price + "\nThank You!";

    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        finalOrder = createOrderSummary(price);
        EditText name = (EditText) findViewById(R.id.name_editText_view);
        String Custname = name.getText().toString();
        String Subject = "JustJava Order for " + Custname;
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_TEXT, finalOrder);
        intent.putExtra(Intent.EXTRA_SUBJECT, Subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    /*
        * Calculates the price of the order.
            *
            * @param quantity is the number of cups of coffee ordered
    */
    private int calculatePrice() {
        int price = quantity * 328;
        return price;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */

    public void increment(View view) {
        if(quantity==100)
        {
            Toast.makeText(this, "You cannot have more than 100 cups of coffee!", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1)
        {
            Toast.makeText(this, "You cannot have less than 1 cup of coffee", Toast.LENGTH_SHORT).show();
            return;
        }
            quantity--;
        displayQuantity(quantity);
    }
}