package com.example.lab_04_transferringdatapolubenskiy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Polubenskiy Lab_04 - Transferring Data
    EditText editText;
    Switch switch_1;
    Switch switch_2;
    Boolean switchCheck_1, switchCheck_2;

    AlertDialog.Builder alertDialog;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//Создание экземпляра класса Activity

        editText = findViewById(R.id.editTextActivity_1);
        switch_1=findViewById(R.id.switch1);
        switch_2=findViewById(R.id.switch2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        Log.e("TEST", " Method onActivity result initializing");
        if (requestCode == 1) {
            if (data !=null) {
                //stringTo - название передаваемой строки для дальнейшего извлекания, s - сами данные
                String s = data.getStringExtra("stringTo");
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
                editText.setText(s);
                switchCheck_1 = data.getBooleanExtra("switch1", false);
                switchCheck_2 = data.getBooleanExtra("switch2", false);
                switch_1.setChecked(switchCheck_1);
                switch_2.setChecked(switchCheck_2);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onDialog_Click(View v)
    {
        String s = editText.getText().toString(); // получаем текст из поля editTextActivity_1

        intent = new Intent(this, MainActivity2.class);
        //this - экземпляр данного класса MainActivity
        intent.putExtra("transmittedString",s);
        // transmittedString - название передаваемой строки для дальнейшего извлекания,
        // s - сами данные, передаваемые во второю Activity

        switchCheck_1 = switch_1.isChecked();
        switchCheck_2 = switch_2.isChecked();

        intent.putExtra("switch1", switchCheck_1);
        intent.putExtra("switch2",switchCheck_2);

        startActivityForResult(intent, 1); // передаем второй activity данные и ждем от нее код 1
    }

    public void OnExit_Click(View v)  //Создание AlertDialog при попытке выйти из основной деятельности
    {
        alertDialog = new AlertDialog.Builder(this);

        LayoutInflater myLayout = LayoutInflater.from(this);

        alertDialog.setTitle("Exit");
        alertDialog.setCancelable(true);
        alertDialog.setMessage("Are you sure you want to exit the app?");
        alertDialog.setIcon(R.drawable.confirmation);

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();// Закрываем Activity
                    }
                }
        );
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        return;
                    }
                }
        );


        alertDialog.create().show();
    }

    public void OnDialogBox_Click(View v) //Creating dialog window
    {
        alertDialog = new AlertDialog.Builder(this);
        alertDialog.setCancelable(true);

        EditText editTextForDialog = new EditText(this);
        editTextForDialog.setMinWidth(700);

        LinearLayout layoutMain = new LinearLayout(this);
        layoutMain.setOrientation(LinearLayout.VERTICAL);

        LinearLayout layout_1 = new LinearLayout(this);
        layout_1.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout layout_2 = new LinearLayout(this);
        layout_2.setOrientation(LinearLayout.HORIZONTAL);

        layout_1.addView(editTextForDialog);

        Button button_1 = new Button(this);
        button_1.setText("OK");

        Button button_2 = new Button(this);
        button_2.setText("Cancel");

        AlertDialog ad = alertDialog.create();

        button_1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String s = editTextForDialog.getText().toString();
                editText.setText(s);
                ad.cancel();
                return;
            }
        });

        button_2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                ad.cancel();
            }
        });

        layout_2.addView(button_1);
        layout_2.addView(button_2);
        layoutMain.addView(layout_1);
        layoutMain.addView(layout_2);
        ad.setView(layoutMain);
        ad.show();
    }
}