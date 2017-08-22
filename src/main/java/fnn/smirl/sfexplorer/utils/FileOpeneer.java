package fnn.smirl.sfexplorer.utils;
import android.content.*;
import java.io.*;
import android.net.*;
import android.webkit.*;

public class FileOpeneer {
	private static Context context;
	private static FileOpeneer fop;
	private FileOpeneer(Context context) {
		this.context = context;
	}

	public static FileOpeneer from(Context context) {
		if (fop == null)fop = new FileOpeneer(context);
		return fop;
	}

	public static void open(File file) throws Exception{
		open(file, true);
	}
	public static void open(File file, boolean withDefaultApp)throws Exception {
		Uri uri = Uri.fromFile(file);
		//String ext = uri.getLastPathSegment().substring(uri.getLastPathSegment().lastIndexOf("."), uri.getLastPathSegment().length());
		Intent intent = new Intent(Intent.ACTION_VIEW);
	String mime = "*/*";
	if(withDefaultApp) mime =	MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getSingleton().getFileExtensionFromUrl(file.getAbsolutePath()));
		intent.setDataAndType(uri, mime);


		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		fop.context.startActivity(intent);
	}
}
