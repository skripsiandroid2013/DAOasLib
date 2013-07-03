package id.ac.itats.skripsi.arga.daoaslibtest;

import id.ac.itats.skripsi.arga.orm.api.Graph;
import id.ac.itats.skripsi.arga.orm.entity.tb_node;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
	private static final String TAG = "DAO API TEST";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		
		Graph graph = new Graph(this);
		graph.createDatabase();
		graph.open();
		tb_node node = graph.getNode("1719948235");
		Log.d(TAG, ""+node.getId_node() + " "+node.getLat_node() + " , " + node.getLon_node());
		graph.close();


		
	
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
