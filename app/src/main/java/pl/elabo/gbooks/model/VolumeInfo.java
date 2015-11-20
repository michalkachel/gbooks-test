package pl.elabo.gbooks.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class VolumeInfo {

	@SerializedName("title")
	String mTitle;

	@SerializedName("description")
	String mDescription;

	@SerializedName("imageLinks")
	HashMap<String, String> mImageLinks;

}
