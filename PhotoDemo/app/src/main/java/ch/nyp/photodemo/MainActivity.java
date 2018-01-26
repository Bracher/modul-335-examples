package ch.nyp.photodemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

public class MainActivity extends AppCompatActivity {

	static final int REQUEST_IMAGE_CAPTURE = 1;

	private Uri mCurrentPhotoUri;
	private ImageView mImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mImageView = findViewById(R.id.imageView);

		Button button =findViewById(R.id.button_take_photo);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

					File imageFile = null;
					try {
						imageFile = createImageFile();
					} catch (IOException ex) {
						// Error occurred while creating the File
					}

					if (imageFile != null) {
						String providerPath = "ch.nyp.photodemo.provider";
						Context context = getApplicationContext();
						mCurrentPhotoUri = FileProvider.getUriForFile(
								context, providerPath, imageFile);

						takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCurrentPhotoUri);
						takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
						startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
					}
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
			mImageView.setImageURI(mCurrentPhotoUri);
		}
	}

	private File createImageFile() throws IOException {
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String imageFileName = "JPEG_" + timeStamp + "_";
		File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
		File image = File.createTempFile(
				imageFileName,  /* prefix */
				".jpg",         /* suffix */
				storageDir      /* directory */
		);

		return image;
	}
}
