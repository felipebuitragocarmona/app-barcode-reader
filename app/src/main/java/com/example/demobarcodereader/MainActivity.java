package com.example.demobarcodereader;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import cafsoft.colombianidinfo.ColombianIDInfo;

public class MainActivity extends AppCompatActivity {
    private Button btnScan;
    private TextView txtCC;
    private ActivityResultLauncher<Intent> actResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        ActivityResultContracts.StartActivityForResult actForRes;
        actForRes=new ActivityResultContracts.StartActivityForResult();
        actResult=registerForActivityResult(actForRes,result -> {
            switch (result.getResultCode()){
                case RESULT_OK:
                    ColombianIDInfo info=(ColombianIDInfo)result.getData().getSerializableExtra("data");
                    this.txtCC.setText(info.getFullname());
            }
        });
        initEvents();

    }
    public void initViews(){
        this.btnScan=findViewById(R.id.btnScan);
        this.txtCC=findViewById(R.id.txtCC);
    }
    public void initEvents(){
        this.btnScan.setOnClickListener(view->{
            Intent intent=new Intent(this,BarcodeScannerActivity.class);
            actResult.launch(intent);
        });
    }

}