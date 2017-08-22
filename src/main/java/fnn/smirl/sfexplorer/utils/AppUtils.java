package fnn.smirl.sfexplorer.utils;
import java.text.*;
import java.util.*;
import fnn.smirl.sfexplorer.*;
import android.content.*;

public class AppUtils
{

	private static HashMap<String, Integer> iconMap;
	
	private static void initIconMapping(){
		iconMap = new HashMap<>();
		iconMap.put("txt", R.mipmap.file36px);
		iconMap.put("zip", R.mipmap.zip36px);
		iconMap.put("rar", R.mipmap.zip36px);
		iconMap.put("doc", R.mipmap.word36px);
		iconMap.put("docx", R.mipmap.word36px);
		iconMap.put("xlsx", R.mipmap.excel36px);
		iconMap.put("xls", R.mipmap.excel36px);
		iconMap.put("pdf", R.mipmap.pdf36px);
		iconMap.put("html", R.mipmap.code36px);
		iconMap.put("css", R.mipmap.code36px);
		iconMap.put("js", R.mipmap.code36px);
		iconMap.put("xml", R.mipmap.code36px);
	}
	
	private static String dateFormat = "dd/MM/yyyy";
	public static String getDate(long date){
	SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
	return formatter.format(new Date(date));
	}
	
	public static String getReadableSize(long size){
		String[] units = {"B", "KB", "MB", "GB", "TB"};
		long l = size;
		int a = 0;
		
		do{
			l /= 1024;
		if(l > 0)	a++;
		}while(l > 0);
		
		double d;
		if(a > units.length-1)a = units.length-1;
		if(a == 0) d = size;
		else d = size / Math.pow(1024, a);
		return String.format("%.2f%3s", d, units[a]);
	}
	
	public static int getFileIcon(String extention){
		initIconMapping();
	return iconMap.get(extention);
	}
}
