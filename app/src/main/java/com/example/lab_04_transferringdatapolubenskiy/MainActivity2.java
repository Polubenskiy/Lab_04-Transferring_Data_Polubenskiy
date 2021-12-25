package com.example.lab_04_transferringdatapolubenskiy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity2 extends AppCompatActivity {

    // Polubenskiy Lab_04 - Transferring Data
    EditText editText_2;
    Switch switch_3;
    Switch switch_4;
    Boolean switchCheck_3, switchCheck_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText_2 = findViewById(R.id.editTextActivity_2);
        switch_3 = findViewById(R.id.switch3);
        switch_4 = findViewById(R.id.switch4);

        Intent intentFromActivity1 = getIntent();//Получаем передаваемый в эту Activity Intent

        String strActivity = intentFromActivity1.getStringExtra("transmittedString");//Вытаскиваем данные из посылки по имени
        editText_2.setText(strActivity);
        switchCheck_3 = intentFromActivity1.getBooleanExtra("switch1", false);
        switchCheck_4 = intentFromActivity1.getBooleanExtra("switch2", false);
        switch_3.setChecked(switchCheck_3);
        switch_4.setChecked(switchCheck_4);
    }

    public void OnOk_Click(View v)
    {
        Intent intent = new Intent(); //this - экземпляр данного класса MainActivity

        String s = editText_2.getText().toString();

        intent.putExtra("stringTo", s); //string - название передаваемой строки для дальнейшего извлекания, s - сами данные
        switchCheck_3 = switch_3.isChecked();
        switchCheck_4 = switch_4.isChecked();
        intent.putExtra("switch1", switchCheck_3);
        intent.putExtra("switch2",switchCheck_4);

        setResult(RESULT_OK, intent);//Вернуть в ответ новый intent с данными
        finish();
    }

    public void OnCancel_Click(View v)
    {
        setResult(RESULT_CANCELED);
        finish(); //Закрываем вторую Activity
    }

}