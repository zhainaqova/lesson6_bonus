package database;

import android.app.TaskInfo;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StoreData extends SQLiteOpenHelper {

    public static final  String DATABASE_NAME    = "zhaiyen.db";
    private static final int    DATABASE_VERSION = 1;
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_NAME = "user_name" ;
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    public StoreData(Context context) {

        super ( context , DATABASE_NAME , null , DATABASE_VERSION );
        this context = context;
    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        db.execSQL ( "CREATE TABLE" + TABLE_USERS + "("+
                COLUMN_USER_NAME + "TEXT"+
                COLUMN_USER_EMAIL + "TEXT"+
                COLUMN_USER_PASSWORD + "TEXT)");

    }

    @Override

    public void onUpgrade(SQLiteDatabase db,int OldVersion,int NewVersion) {
        db.execSQL ( "DROP TABLE IF EXISTS" + TABLE_USERS );
        onCreate ( db );

    }

}