package com.example.dmitriyk_project1;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dmitriyk_project1.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RegistrationActivity extends AppCompatActivity {
    private EditText edtN;
    private EditText edtSN;
    private EditText edtEm;
    private EditText edtP;
    private EditText edtAGE;
    private EditText edtVES;

    private Button btnAcReg, btBack;

    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;
    private String user = "User";

    @Override
    protected void onCreate(Bundle savedIntanceState) {
        super.onCreate(savedIntanceState);
        setContentView(R.layout.activity_registration);
        //привязываем кнопки
        edtAGE = findViewById(R.id.edtAGE);
        edtVES = findViewById(R.id.edtVES);
        mAuth = FirebaseAuth.getInstance();
        edtN = findViewById(R.id.edtN);

        edtSN = findViewById(R.id.edtSN);
        edtEm = findViewById(R.id.edtEm);
        edtP = findViewById(R.id.edtP);
        mDataBase = FirebaseDatabase.getInstance().getReference(user);
        btnAcReg = findViewById(R.id.btnAcReg);
        btBack = findViewById(R.id. btBack);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
              startActivity(intent);

            }
        });

        btnAcReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = mDataBase.push().getKey();
                String Name = edtN.getText().toString();
                String SecName = edtSN.getText().toString();
                String AGE = edtAGE.getText().toString();
                String VES = edtVES.getText().toString();
                String Email = edtEm.getText().toString();

                // тестовый список
                ArrayList<com.example.dmitriyk_project1.Models.Task> tasks = new ArrayList<>();
                tasks.add(new com.example.dmitriyk_project1.Models.Task("example text 1", 10, 4));
                tasks.add(new com.example.dmitriyk_project1.Models.Task("example text 2", 20, 5));
                tasks.add(new com.example.dmitriyk_project1.Models.Task("example text 3", 30, 0));


                User newUser = new User(id, Name, SecName, AGE, VES, Email, tasks);
                if (!TextUtils.isEmpty(Name) && !TextUtils.isEmpty(SecName) && !TextUtils.isEmpty(SecName) && !TextUtils.isEmpty(AGE) && !TextUtils.isEmpty(VES) && !TextUtils.isEmpty(Email)) {
                    mDataBase = FirebaseDatabase.getInstance().getReference(user);

                    mAuth.createUserWithEmailAndPassword(edtEm.getText().toString(), edtP.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                mDataBase.child(newUser.id).setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                        }
                                    }
                                });
                            }
                            //если программе не удаётс зарегистрировать пользователя
                            else {
                                Toast.makeText(RegistrationActivity.this, "Ошибка при регистрации", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(RegistrationActivity.this, "Введите все данные", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

                /*if (edtN.getText().toString().isEmpty() && edtSN.getText().toString().isEmpty() && edtEm.getText().toString().isEmpty() && edtP.getText().toString().isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Заполните все поля!", Toast.LENGTH_SHORT).show();
                    mDataBase = FirebaseDatabase.getInstance().getReference(user);
                    mDataBase.push().setValue(newUser);
                }
                //если пользователь успешно зарегистрировался
                else {
                    mAuth.createUserWithEmailAndPassword(edtEm.getText().toString(), edtP.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                        startActivity(intent);

                                    }
                                    //если программе не удаётс зарегистрировать пользователя
                                    else {
                                        Toast.makeText(RegistrationActivity.this, "Ошибка при регистрации", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }*/


/*
//добавление пользователя в realtime database
       mDataBase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren()) {
                    User u = s.getValue(User.class);
                    assert u != null;
                    if (u.Email.equals(getIntent().getStringExtra("email"))){
                    }
                }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
  /*  private void init(){
        edtN = findViewById(R.id.edtN);
        edtSN = findViewById(R.id.edtSN);
        edtAGE = findViewById(R.id.edtAGE);
        edtVES= findViewById(R.id.edtVES);
        edtEm = findViewById(R.id.edtEm);
        edtP = findViewById(R.id.edtP);


    }
       public void onClickSave(View view) {
           //сохранение данных в базу данных
           String id = mDataBase.getKey();
           String Name = edtN.getText().toString();
           String SecName = edtSN.getText().toString();
           String AGE = edtAGE.getText().toString();
           String VES = edtVES.getText().toString();
           String Email = edtEm.getText().toString();
           String Pass = edtP.getText().toString();
           User newUser = new User(id, Name, SecName, AGE, VES, Email, Pass);
           //если поля пустые то, вылезит уведомление "Заполните все поля"
           if (!TextUtils.isEmpty(Name) && !TextUtils.isEmpty(SecName) && !TextUtils.isEmpty(SecName) && !TextUtils.isEmpty(AGE) && !TextUtils.isEmpty(VES) && !TextUtils.isEmpty(Email) && !TextUtils.isEmpty(Pass)) {
               mDataBase = FirebaseDatabase.getInstance().getReference(user);
               mDataBase.push().setValue(newUser);


           } else {
               Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
           }
       }
           public void onClickRead (View view){

    }*/


//}