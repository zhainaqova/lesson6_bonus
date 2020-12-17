package authentifiation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sql.R;

import database.StoreData;

import static database.StoreData.COLUMN_USER_EMAIL;
import static database.StoreData.COLUMN_USER_NAME;
import static database.StoreData.COLUMN_USER_PASSWORD;
import static database.StoreData.TABLE_USERS;

public
class Login extends AppCompatActivity implements View.OnClickListener{
    private StoreData      Storedb;
    private  SQLiteDatabase sqdb;
    EditText et_email;
    EditText et_password;
    Button   btn_submit;

    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );
        Storedb = new StoreData (this);
        sqdb = Storedb.getWritableDatabase ();

        et_email = findViewById ( R.id.et_email );
        et_password = findViewById ( R.id.et_password );
        btn_submit = findViewById ( R.id.btn_submit );
        btn_submit.setOnClickListener ( this );
    }

    @Override
    public void onClick(View v) {
        if(TextUtils.isEmpty ( et_email.getText () )){
            et_email.setError ( "Fill again" );
            return;
        }
        if(TextUtils.isEmpty ( et_password.getText () )){
            et_password.setError ( "Fill again" );
            return;
        }
        Cursor userCursor = sqdb.rawQuery ( "SELECT * FROM "+ TABLE_USERS +" WHERE "+COLUMN_USER_EMAIL+
                                                    " =? AND " + COLUMN_USER_PASSWORD+"=?",
                                            new String[]{et_email.getText ().toString (),
                                            et_password.getText ().toString ()} );
        if(((userCursor != null)&&(userCursor.getCount ()>0)));{
        Toast.makeText ( this , "User found" , Toast.LENGTH_SHORT ).show ();
        String userName = StoreData.getStrFromColumn(userCursor,COLUMN_USER_EMAIL)
        }
        else{
            Toast.makeText ( this , "Not found" , Toast.LENGTH_SHORT ).show ();
        }

    }
}