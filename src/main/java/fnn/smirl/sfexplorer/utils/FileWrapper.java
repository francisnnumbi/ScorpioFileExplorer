package fnn.smirl.sfexplorer.utils;
import java.io.*;
import fnn.smirl.sfexplorer.*;
import android.webkit.*;

public class FileWrapper implements Comparable<FileWrapper> {

private File file;
//	private String filename;
//	private long size, last;

	public FileWrapper(File file) {
		this.file = file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public File getFile() {
		return file;
	}

	public int getFileIcon(){
		if(file.isDirectory())return R.mipmap.folder36px;
		else{
			return AppUtils.getFileIcon(getExtention());
		}
	}
	
	public String getExtention(){
		if(isDirectory())return null;
		return file.getName().substring(file.getName().lastIndexOf(".")+1, file.getName().length());
		//return MimeTypeMap.getFileExtensionFromUrl(file.getAbsolutePath());
	}

	
	public String getFilename() {
		return file.getName();
	}

	
	public long getSize() {
		return file.length();
	}

	
	public long getLast() {
		try{
		return file.lastModified();
		}catch(Exception e){
			return 0;
		}
	}

	public boolean isDirectory() {
		return file.isDirectory();
	}
	
	@Override
	public int compareTo(FileWrapper f) {
		// TODO: Implement this method
		return file.getName().compareToIgnoreCase(f.getFilename());
	}

	@Override
	public boolean equals(Object o) {
		// TODO: Implement this method
		return file.getName().compareToIgnoreCase(((FileWrapper)o).getFilename()) == 0;
	}
	
	

}
