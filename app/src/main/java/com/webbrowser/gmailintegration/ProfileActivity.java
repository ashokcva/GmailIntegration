package com.webbrowser.gmailintegration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    TextView gmail, username;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile );

        gmail = findViewById( R.id.gmail );
        username = findViewById( R.id.username );
        logout = findViewById( R.id.logout );

        final GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount( this );
        if(googleSignInAccount != null)
        {
            gmail.setText( googleSignInAccount.getEmail() );
            username.setText( googleSignInAccount.getDisplayName() );
        }

        logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        } );

    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        MainActivity.mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
// Google Sign In failed, update UI appropriately
                        Toast.makeText(getApplicationContext(),"Signed out of google",Toast.LENGTH_SHORT).show();
                    }
                });

        Intent intent = new Intent( getApplicationContext(),MainActivity.class );
        startActivity(intent);
    }
}