package pl.elabo.gbooks.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;


public class Book {

	@SerializedName("id")
	String mIdentifier;

	@SerializedName("volumeInfo")
	VolumeInfo mVolumeInfo;

	public String getIdentifier() {
		return mIdentifier;
	}

	public String getTitle() {
		return mVolumeInfo.mTitle;
	}

	public String getDescription() {
		return mVolumeInfo.mDescription;
	}

	public HashMap<String, String> getImageLinks() {
		return mVolumeInfo.mImageLinks;
	}
}
