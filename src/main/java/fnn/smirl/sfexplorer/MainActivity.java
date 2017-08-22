package fnn.smirl.sfexplorer;

import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.support.v7.app.*;
import fnn.smirl.sfexplorer.utils.*;
import java.util.*;
import fnn.smirl.sfexplorer.adapters.*;
import java.io.*;
import android.widget.*;
import android.view.*;

public class MainActivity extends AppCompatActivity implements Constants {

	Toolbar toolbar;
	RecyclerView recyclerView;
	ActionBar actionBar;
	public AllFiles allFiles;
	FileRecyclerAdapter adapter;
	public File currDir;
	ImageButton backButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		init();
	}

	private void init() {
		currDir = ROOT_HOME;
		setupToolbar();
		setupRecyclerView();
		initOtherViews();
	}

	private void initOtherViews() {
		backButton = (ImageButton) findViewById(R.id.main_activityImageButton_back);
		backButton.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1) {
					File tmp = currDir.getParentFile();
					if(tmp != null){
						currDir = tmp;
					reloadList();
					}
				}
			});
	}

	private void setupToolbar() {
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		if (toolbar != null) {
			setSupportActionBar(toolbar);
			if (getSupportActionBar() != null) {
				actionBar =	getSupportActionBar();
			}
		}
	}

	private void setupRecyclerView() {
		recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
	LinearLayoutManager llm =	new LinearLayoutManager(this);
		llm.setOrientation(LinearLayoutManager.VERTICAL);
	recyclerView.setLayoutManager(llm);
		
		allFiles = new AllFiles(currDir);
		adapter = new FileRecyclerAdapter(this);
		recyclerView.setAdapter(adapter);
	}

	public void reloadList(){
		allFiles.setBaseDirectory(currDir);
		//adapter = new FileRecyclerAdapter(this, allFiles);
		adapter.notifyDataSetChanged();
		Toast.makeText(this, "reload list", Toast.LENGTH_SHORT).show();
	}
}
