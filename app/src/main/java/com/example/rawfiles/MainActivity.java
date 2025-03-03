package com.example.rawfiles;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private final String FILENAME = "rew.txt";
    String fileName = FILENAME.substring (0, FILENAME.length () - 4) ;
    EditText eT;
    TextView tV;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        eT = findViewById(R.id.eT);
        tV = findViewById(R.id.tV);
        int resourceId = this.getResources ().getIdentifier (fileName,
                "raw", this.getPackageName ()) ;
    }
    public void ClickRaw(View view) {
        try {
            int resourcID = this.getResources().getIdentifier(fileName,"raw",this.getPackageName());
            InputStream is = this.getResources().openRawResource(resourcID);
            InputStreamReader iSR = new InputStreamReader(is);
            BufferedReader bR = new BufferedReader(iSR);
            StringBuilder sB = new StringBuilder();
            String line = bR.readLine();
            while (line!=null){
                sB.append(line+'\n');
                line = bR.readLine();
            }
            tV.setText(sB.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void clickText(View view) {
        String name = eT.getText().toString();
        tV.setText(name);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuCredits) {
            Intent gi = new Intent(this, MainActivity2.class);
            startActivity(gi);
        }
        return true;
    }

}