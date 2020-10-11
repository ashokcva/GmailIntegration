package com.webbrowser.gmailintegration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SendProductActivity extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private CollectionReference productref;

    EditText productname, productprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_product);

        firebaseFirestore = FirebaseFirestore.getInstance();
        productref = firebaseFirestore.collection("products");

        productname = findViewById(R.id.productname);
        productprice = findViewById(R.id.productprice);
    }

    public void sendProduct(View view) {
        String name = productname.getText().toString();
        String price = productprice.getText().toString();

        ProductModel productModel = new ProductModel(name,Long.parseLong(price));
        productref.add(productModel).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(SendProductActivity.this, documentReference.getId(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void navToListPage(View view) {
        Intent intent = new Intent( getApplicationContext(), FireStoreListingActivity.class );
        startActivity(intent);
    }

    public void navToProfile(View view) {
        Intent intent = new Intent( getApplicationContext(), ProfileActivity.class );
        startActivity(intent);
    }
}