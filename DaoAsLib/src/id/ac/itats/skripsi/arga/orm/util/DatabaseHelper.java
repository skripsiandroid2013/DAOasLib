package id.ac.itats.skripsi.arga.orm.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static String TAG = "DataBaseHelper";
	private static String DB_PATH = "";
	private static String DB_NAME = "dbskrip";
	private SQLiteDatabase database;
	private final Context context;

	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, 1);
		DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
		this.context = context;
	}

	public void createDataBase() throws IOException {

		boolean dataBaseExist = checkDataBase();
		if (!dataBaseExist) {
			this.getReadableDatabase();
			this.close();
			try {

				copyDataBase();
				Log.d(TAG, "createDatabase database created");
			} catch (IOException ioException) {
				throw new Error("ErrorCopyingDataBase");
			}
		}
	}

	private boolean checkDataBase() {
		File dbFile = new File(DB_PATH + DB_NAME);
		return dbFile.exists();
	}

	private void copyDataBase() throws IOException {
		InputStream input = context.getAssets().open(DB_NAME);
		String outFileName = DB_PATH + DB_NAME;
		OutputStream output = new FileOutputStream(outFileName);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = input.read(buffer)) > 0) {
			output.write(buffer, 0, length);
		}
		output.flush();
		output.close();
		input.close();
	}

	public boolean openDataBase() throws SQLException {
		String path = DB_PATH + DB_NAME;
		database = SQLiteDatabase.openDatabase(path, null,
				SQLiteDatabase.CREATE_IF_NECESSARY);
		return database != null;
	}

	@Override
	public synchronized void close() {
		if (database != null)
			database.close();
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
