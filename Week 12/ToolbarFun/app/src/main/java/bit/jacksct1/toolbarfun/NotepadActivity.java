package bit.jacksct1.toolbarfun;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class NotepadActivity extends AppCompatActivity {

    final Context context = this;
    public TextView tvNotepad;


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
        EditText etEnter = (EditText) findViewById(R.id.edEnter);


        switch (itemTitle)
        {
            case "Write":
                    etEnter.setEnabled(true);
                    etEnter.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                break;
            case "Delete":
                    tvNotepad.setText("");
                break;
            case "Add":
                    tvNotepad.setText(etEnter.getText());
                break;
        }

        return true;


    }

    private void enterText()
    {



    }





}
