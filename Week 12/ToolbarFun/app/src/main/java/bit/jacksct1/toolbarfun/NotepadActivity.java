package bit.jacksct1.toolbarfun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class NotepadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_layout, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String itemTitle = (String) item.getTitle();
        TextView tvNotepad = (TextView) findViewById(R.id.tvNotepad);


        switch (itemTitle)
        {
            case "Write":

                break;
            case "Delete":
                    tvNotepad.setText("");
                break;
            case "Settings":

                break;
        }

        return true;


    }
}
