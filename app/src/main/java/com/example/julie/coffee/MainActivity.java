package com.example.julie.coffee;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantityCoffee= 0;
    double coffeePrice= 2.5;
    double totalPrice= 0;
    boolean promoCodeUsed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    // displays toast when checkbox is clicked
    public void displayToast (String string){
        Context context = getApplicationContext();
        CharSequence text = string;
        String promoCode= "Julie";
        int duration = Toast.LENGTH_SHORT;
        if (text.equals(promoCode)){
            text = "correct; discount applied";
        }else{
            text= "Incorrect promo code";

        }
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.BOTTOM| Gravity.LEFT, 123, 0);
        toast.show();
        /*final EditText simpleText= (EditText) findViewById(R.id.editText);
        String strValue = simpleText.getText().toString();
        Toast toast2 = Toast.makeText(context, strValue, duration);
        toast2.setGravity(Gravity.BOTTOM| Gravity.RIGHT, 0, 0);
        toast2.show();*/
    }


    public void incrementCoffee (View view){
        quantityCoffee++;
        totalPrice =quantityCoffee*coffeePrice;
        display(quantityCoffee);
        displayPrice(totalPrice);
    }

    public void decrementCoffee (View view){
        if (quantityCoffee==0)

        {
            Toast.makeText(this, "You cannot have less than 0 cups!", Toast.LENGTH_SHORT).show();
            return;
        }
        quantityCoffee--;
        totalPrice =quantityCoffee*coffeePrice;
        display(quantityCoffee);
        displayPrice(totalPrice);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText text= (EditText) findViewById(R.id.editText);

        String value= text.getText().toString();
        if (!value.equals("") && !promoCodeUsed){
            displayToast(value);
        }
        if (value.equals("Julie") && !promoCodeUsed){
            totalPrice= totalPrice*.75;
            promoCodeUsed= true;
        }else if (promoCodeUsed && !value.equals("")  ){
            Toast.makeText(this, "This promo code is already used. ", Toast.LENGTH_SHORT).show();

        }
        String priceMessage="Amount due: $" + (String.format("%.2f", totalPrice));
        displayMessage (priceMessage);
        CheckBox whippedCream= (CheckBox) findViewById(R.id.hasWhippedCream);

        if (whippedCream.isChecked()) {
            Toast.makeText(this, "You wanted whipped cream.", Toast.LENGTH_SHORT).show();;
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayMessage(String message){
        TextView priceTextView= (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);


    }
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice (double number){

        TextView priceTextView= (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));

    }


}