package com.example.sepatuu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class list_pesanan extends AppCompatActivity {

    TextView textViewalamat,textViewjumlah,textViewjenislayanan,textViewtotal;
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(SharePrefManager.getInstance(this).isLoggedIn()){
            textViewalamat = findViewById(R.id.textViewalamat);
            textViewjumlah = findViewById(R.id.textViewjumlah);
            textViewjenislayanan = findViewById(R.id.textViewjenislayanan);
            textViewtotal = findViewById(R.id.textViewtotal);
            btnLogout = findViewById(R.id.buttonLogout);
            dodolan dodolan = SharePrefManager.getInstance(this).getdodol();

            textViewalamat.setText(String.valueOf(dodolan.getAlamat()));
            textViewjumlah.setText(dodolan.getJumlah());
            textViewjenislayanan.setText(dodolan.getPilihan());
            textViewtotal.setText(dodolan.getTotal());

            findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    startActivity(new Intent(getApplicationContext(), menu.class));
                }
            });

        }
        else{
            Intent intent = new Intent(list_pesanan.this,menu.class);
            startActivity(intent);
            finish();
        }
    }
}
