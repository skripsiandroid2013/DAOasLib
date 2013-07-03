package id.ac.itats.skripsi.arga.orm.api;

import java.io.IOException;
import java.util.List;

import id.ac.itats.skripsi.arga.orm.dao.DaoMaster;
import id.ac.itats.skripsi.arga.orm.dao.DaoSession;
import id.ac.itats.skripsi.arga.orm.dao.tb_edgeDao;
import id.ac.itats.skripsi.arga.orm.dao.tb_nodeDao;
import id.ac.itats.skripsi.arga.orm.entity.tb_node;
import id.ac.itats.skripsi.arga.orm.util.DatabaseHelper;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Graph {
	
	protected static final String TAG = "Graph Data"; 
	 
    private final Context context; 
    private SQLiteDatabase db; 
    private DatabaseHelper dbHelper; 
    private DaoMaster daoMaster;
	private DaoSession daoSession;
	private tb_nodeDao nodeDao;
	private tb_edgeDao edgeDao;
	
    public Graph(Context context){
    	this.context = context;
    	dbHelper = new DatabaseHelper(context);
    }
    
    public Graph createDatabase() throws SQLException  
    { 
        try  
        { 
        	dbHelper.createDataBase(); 
        }  
        catch (IOException mIOException)  
        { 
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase"); 
            throw new Error("UnableToCreateDatabase"); 
        } 
        return this; 
    } 
 
    public Graph open() throws SQLException  
    { 
        try  
        { 
        	dbHelper.openDataBase(); 
        	dbHelper.close(); 
        	db = dbHelper.getReadableDatabase();
        	daoMaster = new DaoMaster(db);
    		daoSession = daoMaster.newSession();
    		edgeDao = daoSession.getTb_edgeDao();
    		nodeDao = daoSession.getTb_nodeDao();
        	
        }  
        catch (SQLException mSQLException)  
        { 
            Log.e(TAG, "open >>"+ mSQLException.toString()); 
            throw mSQLException; 
        } 
        return this; 
    } 
 
    public void close()  
    { 
    	dbHelper.close(); 
    }
    
    //QUERY-QUERY
    public tb_node getNode(String nodeId){
    	List<tb_node> nodes = nodeDao.queryBuilder().where(tb_nodeDao.Properties.Id_node.eq(nodeId)).list();
    	return nodes.get(0);
    }
}
