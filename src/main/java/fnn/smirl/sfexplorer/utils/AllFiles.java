package fnn.smirl.sfexplorer.utils;
import java.util.*;
import java.io.*;

public class AllFiles {
	private ArrayList<FileWrapper> list;
	private File baseDir;

	public AllFiles(File baseDir) {
		this.list = new ArrayList<FileWrapper>();
		this.baseDir = baseDir;
		generateList();
	}

	public void generateList() {
		if(list != null)list.clear();
		if (baseDir.exists() && baseDir.isDirectory()) {
			File[] files = baseDir.listFiles();
			ArrayList<FileWrapper> lis = new ArrayList<FileWrapper>();
			for (File f : files) {
				if (f.isDirectory())list.add(new FileWrapper(f));
				else if(f.isFile()) lis.add(new FileWrapper(f));
			}
			Collections.sort(list);
			Collections.sort(lis);
			list.addAll(lis);
		}
	}

	public void setBaseDirectory(File file) {
		this.baseDir = file;
		generateList();
	}
	
	public int getSize() {
		return list.size();
	}

	public FileWrapper getFile(int position) {
		return list.get(position);
	}

	public ArrayList<FileWrapper> getList() {
		return list;
	}

	public void setList(ArrayList<FileWrapper> list) {
		this.list = list;
	}

}
