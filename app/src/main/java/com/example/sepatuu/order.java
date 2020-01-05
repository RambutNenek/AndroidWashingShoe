package com.example.sepatuu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class order extends AppCompatActivity {
    int jumlah = 0;
    TextView quantity_text, totalharga;
    ProgressBar progressBar;
    EditText editTextAlamat;
    RadioGroup radioGroupListChose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        progressBar = findViewById(R.id.progressBar);
        quantity_text = findViewById(R.id.quantity_text);
        totalharga = findViewById(R.id.totalharga);
        editTextAlamat = findViewById(R.id.editTextAlamat);
        radioGroupListChose = findViewById(R.id.radioListChose);


        findViewById(R.id.buttonView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if user pressed on button register
                //here we will register the user to server
                belanja();
            }
        });

    }
    public void increment (View view){
        jumlah = jumlah + 1;
        display(jumlah);
    }

    public void decrement (View view){
        jumlah = jumlah - 1;
        display(jumlah);
    }

    public void submit (View view){
        displayPrice(jumlah * 2);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.totalharga);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    private void belanja() {
        final String jumlah = quantity_text.getText().toString().trim();
        final String total = totalharga.getText().toString().trim();
        final String alamat = editTextAlamat.getText().toString().trim();

        final String pilihan = ((RadioButton) findViewById(radioGroupListChose.getCheckedRadioButtonId())).getText().toString();

        //first we will do the validations
        if (TextUtils.isEmpty(jumlah)) {
            quantity_text.setError("Please set to 1");
            quantity_text.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(total)) {
            totalharga.setError("Error");
            totalharga.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(alamat)){
            editTextAlamat.setError("Error");
            editTextAlamat.requestFocus();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url.URL_SHOP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);

                        Log.i("tagconvertstr", "["+response+"]");

                        try {
                            //json convert
                            JSONObject obj = new JSONObject(response);

                            //error
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                JSONObject dodolanJson = obj.getJSONObject("dodolan");

                                dodolan dodolan  = new dodolan(
                                        dodolanJson.getInt("id_belanja"),
                                        dodolanJson.getString("alamat"),
                                        dodolanJson.getInt("jumlah"),
                                        dodolanJson.getString("total"),
                                        dodolanJson.getString("pilihan")
                                );

                                SharePrefManager.getInstance(getApplicationContext()).dodolan();
                                finish();
                                startActivity(new Intent(getApplicationContext(), list_pesanan.class));
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("alamat", alamat);
                params.put("jumlah", jumlah);
                params.put("total", total);
                params.put("pilihan", pilihan);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        }
    }