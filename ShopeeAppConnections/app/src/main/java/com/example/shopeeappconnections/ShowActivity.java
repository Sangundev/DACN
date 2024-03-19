
package com.example.shopeeappconnections;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopeeappconnections.Connection.DatabaseConnection;
import com.example.shopeeappconnections.Model.Cartitem;
import com.help5g.uddoktapaysdk.UddoktaPay;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowActivity extends AppCompatActivity {
    private List<Cartitem> listCarts;
    private String userId;


    Button btnCreateOrder, btnPay, momo;
    EditText txtAmount;
    TextView lblZpTransToken, txtToken;
    //    private String lblZpTransToken = "abc";
    LinearLayout layout;
    WebView webView;
    TextView gia;
    Button button;

    // Visa
    private static final String API_KEY = "982d381360a69d419689740d9f2e26ce36fb7a50";
    private static final String CHECKOUT_URL = "https://sandbox.uddoktapay.com/api/checkout-v2";
    private static final String VERIFY_PAYMENT_URL = "https://sandbox.uddoktapay.com/api/verify-payment";
    private static final String REDIRECT_URL = "https://uddoktapay.com";
    private static final String CANCEL_URL = "https://uddoktapay.com";

    // Instance variables to store payment information
    private String storedFullName;
    private String storedEmail;
    private String storedAmount;
    private String storedInvoiceId;
    private String storedPaymentMethod;
    private String storedSenderNumber;
    private String storedTransactionId;
    private String storedDate;
    private String storedFee;
    private String storedChargedAmount;

    private String storedMetaKey1;
    private String storedMetaValue1;

    private String storedMetaKey2;
    private String storedMetaValue2;

    private String storedMetaKey3;
    private String storedMetaValue3;

    //zalo
    private void IsLoading() {
        txtToken.setVisibility(View.INVISIBLE);
//        btnPay.setVisibility(View.INVISIBLE);
    }

    private void IsDone() {
        txtToken.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        ImageView quaylai = findViewById(R.id.imageView2);

        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        userId = getUserIdFromPreferences();
        TextView textView = findViewById(R.id.tv_Text);
        gia = findViewById(R.id.txtgia);

        Intent intent = getIntent();
        if (intent != null) {
            listCarts = (List<Cartitem>) intent.getSerializableExtra("data");
            StringBuilder stringBuilder = new StringBuilder();
            int totalAmount = intent.getIntExtra("totalAmount", 0);
            int sum = 0;
            for (Cartitem item : listCarts) {
                stringBuilder.append(item.getId() + " - " + item.getProductName() + " - " + (item.getPrice() * item.getQuantity())).append("\n");
                sum += item.getPrice() * item.getQuantity();
            }

            stringBuilder.append("Sum: " + sum);
            textView.setText(stringBuilder);
            gia.setText(String.valueOf(sum));

        }
        String enteredAmount = gia.getText().toString();

        //Visa
        webView = findViewById(R.id.webview);
        layout = findViewById(R.id.uilayoutweb);
        button = findViewById(R.id.btnPay);
        LinearLayout thanhtoan = findViewById(R.id.uithanhtoan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                webView.setVisibility(View.VISIBLE);
                layout.setVisibility(View.VISIBLE);
                thanhtoan.setVisibility(View.GONE);
                // Set your metadata values in the map
                // Set your metadata values in the map
                Map<String, String> metadataMap = new HashMap<>();
                metadataMap.put("CustomMetaData1", "Meta Value 1");
                metadataMap.put("CustomMetaData2", "Meta Value 2");
                metadataMap.put("CustomMetaData3", "Meta Value 3");

                UddoktaPay.PaymentCallback paymentCallback = new UddoktaPay.PaymentCallback() {
                    @Override
                    public void onPaymentStatus(String status, String fullName, String email, String amount, String invoiceId,
                                                String paymentMethod, String senderNumber, String transactionId,
                                                String date, Map<String, String> metadataValues, String fee, String chargeAmount) {
                        // Callback method triggered when the payment status is received from the payment gateway.
                        // It provides information about the payment transaction.
                        storedFullName = fullName;
                        storedEmail = email;
                        storedAmount = amount;
                        storedInvoiceId = invoiceId;
                        storedPaymentMethod = paymentMethod;
                        storedSenderNumber = senderNumber;
                        storedTransactionId = transactionId;
                        storedDate = date;
                        storedFee = fee;
                        storedChargedAmount = chargeAmount;

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Clear previous metadata values to avoid duplication
                                storedMetaKey1 = null;
                                storedMetaValue1 = null;
                                storedMetaKey2 = null;
                                storedMetaValue2 = null;
                                storedMetaKey3 = null;
                                storedMetaValue3 = null;

                                // Iterate through the metadata map and store the key-value pairs
                                for (Map.Entry<String, String> entry : metadataValues.entrySet()) {
                                    String metadataKey = entry.getKey();
                                    String metadataValue = entry.getValue();

                                    if ("CustomMetaData1".equals(metadataKey)) {
                                        storedMetaKey1 = metadataKey;
                                        storedMetaValue1 = metadataValue;
                                    } else if ("CustomMetaData2".equals(metadataKey)) {
                                        storedMetaKey2 = metadataKey;
                                        storedMetaValue2 = metadataValue;
                                    } else if ("CustomMetaData3".equals(metadataKey)) {
                                        storedMetaKey3 = metadataKey;
                                        storedMetaValue3 = metadataValue;
                                    }
                                }

                                // Update UI based on payment status
                                if ("COMPLETED".equals(status)) {
                                    webView.setVisibility(View.GONE);
                                    layout.setVisibility(View.VISIBLE);
                                    handlePayment(listCarts, userId);
                                } else if ("PENDING".equals(status)) {
                                    // Handle payment pending case
                                } else if ("ERROR".equals(status)) {
                                    // Handle payment error case
                                }
                            }
                        });
                    }
                };
                UddoktaPay uddoktapay = new UddoktaPay(webView, paymentCallback);
                uddoktapay.loadPaymentForm(API_KEY, "soannguyengoc", "Soan@gmail.com", enteredAmount, CHECKOUT_URL, VERIFY_PAYMENT_URL, REDIRECT_URL, CANCEL_URL, metadataMap);
            }
        });

    }

    private String getUserIdFromPreferences() {
        SharedPreferences preferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        return preferences.getString("USER_ID", null);
    }

    private void handlePayment(List<Cartitem> listCarts, String userId) {
        try {
            new DatabaseConnection().Paymentonl(listCarts, userId);
            new DatabaseConnection().removeItemsFromCart(listCarts, userId);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle SQLException, show a toast or log the error
        }
    }

    public void handlePayment(View view) {
        if (view.getId() == R.id.btnPayment) {
            try {
                new DatabaseConnection().Payment(listCarts, userId);
                // Remove purchased items from the cart
                new DatabaseConnection().removeItemsFromCart(listCarts, userId);

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}