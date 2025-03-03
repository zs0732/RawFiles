/**
 * מחלקה ראשית של האפליקציה, המנהלת את ממשק המשתמש ואת קריאת הקובץ מ-res/raw.
 */
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

/**
 * {@code MainActivity} היא המחלקה הראשית המנהלת את פעילות האפליקציה.
 * היא מטפלת בקלט המשתמש, תפריטים, וקריאת קובץ ממקור raw.
 */
public class MainActivity extends AppCompatActivity {

    /** שם הקובץ לקריאה מהמשאבים. */
    private final String FILENAME = "rew.txt";

    /** שם הקובץ ללא סיומת. */
    String fileName = FILENAME.substring(0, FILENAME.length() - 4);

    /** שדה לקלט טקסט של המשתמש. */
    EditText eT;

    /** תווית להצגת תוכן הקובץ או קלט המשתמש. */
    TextView tV;

    /**
     * מתודת onCreate מפעילה את המסך הראשי ואת רכיבי הממשק.
     * @param savedInstanceState מצב שמור של הפעילות
     */
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        eT = findViewById(R.id.eT);
        tV = findViewById(R.id.tV);

        int resourceId = this.getResources().getIdentifier(fileName,
                "raw", this.getPackageName());
    }

    /**
     * מתודה לקריאת קובץ מ-res/raw והצגתו ב-TextView.
     * @param view כפתור הלחיצה שהפעיל את המתודה
     */
    public void ClickRaw(View view) {
        try {
            int resourcID = this.getResources().getIdentifier(fileName, "raw", this.getPackageName());
            InputStream is = this.getResources().openRawResource(resourcID);
            InputStreamReader iSR = new InputStreamReader(is);
            BufferedReader bR = new BufferedReader(iSR);
            StringBuilder sB = new StringBuilder();
            String line = bR.readLine();

            while (line != null) {
                sB.append(line + '\n');
                line = bR.readLine();
            }

            tV.setText(sB.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * מתודה המציגה את הטקסט שהוזן ב-EditText בתוך TextView.
     * @param view כפתור הלחיצה שהפעיל את המתודה
     */
    public void clickText(View view) {
        String name = eT.getText().toString();
        tV.setText(name);
    }

    /**
     * יוצר את תפריט הפעילות הראשית.
     * @param menu אובייקט התפריט
     * @return אמת אם התפריט נוצר בהצלחה
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * מטפל בפריטים שנבחרו בתפריט.
     * @param item הפריט שנבחר
     * @return אמת אם הפעולה בוצעה
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuCredits) {
            Intent gi = new Intent(this, MainActivity2.class);
            startActivity(gi);
        }

        return true;
    }
}
