package com.example.julie.coffee;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
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
    int quantityIcedCoffee= 0;
    double coffeePrice= 2.5;
    double icedCoffeePrice= 2.85;
    double totalPrice= 0;

    boolean promoCodeUsed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.incCoffee).setOnClickListener(GlobalOnClick);
        findViewById(R.id.decCoffee).setOnClickListener(GlobalOnClick);
        findViewById(R.id.incIcedCoffee).setOnClickListener(GlobalOnClick);
        findViewById(R.id.decIcedCoffee).setOnClickListener(GlobalOnClick);
    }

    //Global on Click listener for all buttons
        final View.OnClickListener GlobalOnClick= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.incCoffee:
                    quantityCoffee++;
                    break;
                case R.id.decCoffee:
                    if (quantityCoffee >0) {
                    quantityCoffee--;
                } else
                    {makeToast ();}
                    break;
                case R.id.incIcedCoffee:
                    quantityIcedCoffee++;
                    break;
                case R.id.decIcedCoffee:
                    if (quantityIcedCoffee >0) {
                        quantityIcedCoffee--;
                    } else
                    {makeToast ();}
                    break;
            }

            totalPrice= quantityCoffee*coffeePrice + quantityIcedCoffee*icedCoffeePrice;
            display(quantityCoffee);
            displayIcedCoffee(quantityIcedCoffee);
            displayPrice(totalPrice);
        }
    };


    // displays toast for promo code
    public void displayToast (String string){
        Context context = getApplicationContext();
        CharSequence text = string;
        String promoCode= "Julie";
        int duration = Toast.LENGTH_SHORT;
        if (text.equals(promoCode)){
            text = "Discount applied";
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

    //makes toast for decrement method
    public void makeToast(){
        Toast.makeText(this, "You cannot have less than 0 cups!", Toast.LENGTH_SHORT).show();

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

    private void displayIcedCoffee(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view2);
        quantityTextView.setText("" + number);}

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice (double number){

        TextView priceTextView= (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));

    }


}