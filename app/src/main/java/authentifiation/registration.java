package authentifiation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
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

public class registration extends AppCompatActivity implements View.OnClickListener {
    private StoreData Storedb;
    private SQLiteDatabase sqdb;

    EditText et_name;
    EditText et_email;
    EditText et_password;
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView ( R.layout.activity_registration );

        Storedb = new StoreData (this);
        sqdb = Storedb.getWritableDatabase ();

        et_name =findViewById ( R.id.et_username );
        et_email = findViewById ( R.id.et_email );
        et_password = findViewById ( R.id.et_password );
        btn_submit = findViewById ( R.id.btn_submit );
        btn_submit.setOnClickListener ( this );
    }

    @Override
    public void onClick(View v) {
        if(TextUtils.isEmpty ( et_name.getText () )){
            et_name.setError ( "Fill again" );
            return;
        }
        if(TextUtils.isEmpty ( et_email.getText () )){
            et_email.setError ( "Fill again" );
            return;
        }
        if(TextUtils.isEmpty ( et_password.getText () )){
            et_password.setError ( "Fill again" );
            return;
        }
        ContentValues versionValues = new ContentValues ();
        versionValues.put ( COLUMN_USER_NAME,et_name.getText ().toString () );
        versionValues.put ( COLUMN_USER_EMAIL,et_email.getText ().toString () );
        versionValues.put ( COLUMN_USER_PASSWORD,et_password.getText ().toString () );
        sqdb.insert ( TABLE_USERS,null,versionValues );
        Toast.makeText ( this , "Successfully entered" , Toast.LENGTH_SHORT ).show ();
        Intent intent = new Intent (this,Login.class);
        startActivity ( intent );
        break;

    }
}