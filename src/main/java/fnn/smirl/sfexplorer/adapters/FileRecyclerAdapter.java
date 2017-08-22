package fnn.smirl.sfexplorer.adapters;
import android.support.v7.widget.*;
import android.widget.*;
import android.view.*;
import fnn.smirl.sfexplorer.*;
import fnn.smirl.sfexplorer.utils.*;
import java.util.*;
import android.graphics.*;
import android.content.*;
import java.io.*;
import android.webkit.*;
import com.squareup.picasso.*;
import fnn.smirl.fileopener.*;

public class FileRecyclerAdapter extends RecyclerView.Adapter<FileRecyclerAdapter.FileHolder> {
	private MainActivity activity;

	public FileRecyclerAdapter(MainActivity activity) {
		this.activity = activity;
	}

	@Override
	public FileRecyclerAdapter.FileHolder onCreateViewHolder(ViewGroup p1, int p2) {
		// TODO: Implement this method
		View v = LayoutInflater.from(p1.getContext()).inflate(R.layout.recycler_model, p1, false);
		return new FileHolder(v);
	}

	@Override
	public void onBindViewHolder(FileRecyclerAdapter.FileHolder holder, int p2) {
		// TODO: Implement this method
		FileWrapper fr = activity.allFiles.getFile(p2);
		try {
			holder.fileIcon.setImageResource(fr.getFileIcon());
		} catch (Exception ee) {
			holder.fileIcon.setImageResource(R.mipmap.default_file36px);
		}
		if (!fr.isDirectory())loadIconFromFile(holder.fileIcon, fr.getFile());
		holder.fileView.setText(fr.getFilename() + "(" + fr.getExtention() + ")");
		holder.dateView.setText(AppUtils.getDate(fr.getLast()));
		holder.sizeView.setText(AppUtils.getReadableSize(fr.getSize()));
	}

	@Override
	public int getItemCount() {
		// TODO: Implement this method
		return activity.allFiles.getSize();
	}

	private void loadIconFromFile(ImageView imgView, File file) {
		String	mime =	MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getSingleton().getFileExtensionFromUrl(file.getAbsolutePath()));
		if (file != null && mime != null && mime.contains("image")) {
			Picasso.with(activity)
				.load(file)
				.resize(48, 52)
				.centerInside()
				.into(imgView);
		}
	}


	public class FileHolder extends RecyclerView.ViewHolder {
		CardView cv;
		ImageView fileIcon;
		TextView fileView, dateView, sizeView;
		public FileHolder(View view) {
			super(view);
			cv = (CardView) view.findViewById(R.id.recycler_modelandroid_support_v7_widget_CardView);
			cv.setCardBackgroundColor(Color.GRAY);
			cv.setCardElevation(5);

			fileIcon = (ImageView) view.findViewById(R.id.recycler_modelImageView);
			fileView = (TextView) view.findViewById(R.id.recycler_modelTextView1);
			dateView = (TextView) view.findViewById(R.id.recycler_modelTextView2);
			sizeView = (TextView) view.findViewById(R.id.recycler_modelTextView3);

			view.setOnClickListener(new View.OnClickListener(){

					@Override
					public void onClick(View p1) {
						// TODO: Implement this method

						FileWrapper fw = activity.allFiles.getFile(getAdapterPosition());
						if (fw.getFile().isDirectory()) {
							activity.currDir = fw.getFile();
							activity.reloadList();
						} else {
							try {
								FileOpener.from(p1.getContext()).open(fw.getFile());
							} catch (Exception e) {}
						}
					}
				});
		}
	}
}
