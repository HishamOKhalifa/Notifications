package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseUser fuser;
    DatabaseReference reference;
    Button apply_btn;
    TextView info;
    List<model> model;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apply_btn = findViewById(R.id.apply_btn);
        info = findViewById(R.id.user_info);

        intent = getIntent();

        final String  userid = intent.getStringExtra("userid");
        fuser = FirebaseAuth.getInstance().getCurrentUser();

        apply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_info = info.getText().toString();
                sendNotify (fuser.getUid(), userid, user_info);

            }
        });

    }

    private void sendNotify(String student, String company, String user_info) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("student", student);
        hashMap.put("company", company);
        hashMap.put("Notify", user_info);


        reference.child("Notification").push().setValue(hashMap);
    }
}