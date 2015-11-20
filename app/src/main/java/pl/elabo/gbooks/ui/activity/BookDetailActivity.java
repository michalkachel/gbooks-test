package pl.elabo.gbooks.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import butterknife.Bind;
import pl.elabo.gbooks.AppConstants;
import pl.elabo.gbooks.R;
import pl.elabo.gbooks.model.Book;
import pl.elabo.gbooks.mvp.presenter.BookDetailPresenter;
import pl.elabo.gbooks.mvp.presenter.BookDetailPresenterImpl;
import pl.elabo.gbooks.mvp.view.BooksDetailView;
import pl.elabo.gbooks.ui.activity.base.BaseActivity;

public class BookDetailActivity extends BaseActivity implements BooksDetailView {

	BookDetailPresenter mBookDetailPresenter;

	private String mBookId;
	private String mBookName;

	@Bind(R.id.book_image)
	ImageView mBookImage;

	@Bind(R.id.title)
	TextView mTitle;

	@Bind(R.id.description)
	TextView mDescription;

	public static void start(Activity activity, String bookId, String bookName, ImageView transitionImageView) {
		Intent intent = new Intent(activity, BookDetailActivity.class);
		intent.putExtra(AppConstants.Argument.BOOK_ID, bookId);
		intent.putExtra(AppConstants.Argument.BOOK_NAME, bookName);

		ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImageView, activity.getString(R.string.transtition_image));
		ActivityCompat.startActivity(activity, intent, options.toBundle());
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_book_detail;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (mBookDetailPresenter == null) {
			mBookDetailPresenter = new BookDetailPresenterImpl(this, mBookId);
		}

		mBookDetailPresenter.onCreate();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mBookDetailPresenter.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mBookDetailPresenter.onPause();
	}

	@Override
	protected void handleArguments() {
		if (getIntent().hasExtra(AppConstants.Argument.BOOK_ID) && getIntent().hasExtra(AppConstants.Argument.BOOK_NAME)) {
			mBookId = getIntent().getExtras().getString(AppConstants.Argument.BOOK_ID);
			mBookName = getIntent().getExtras().getString(AppConstants.Argument.BOOK_NAME);
		}
	}

	@Override
	public void postponeTransition() {
		supportPostponeEnterTransition();
	}

	@Override
	public void setBookImage(String path) {
		Glide.with(this).load(path).crossFade().listener(new RequestListener<String, GlideDrawable>() {
			@Override
			public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
				mBookDetailPresenter.onBookImageFailedToLoad();
				return false;
			}

			@Override
			public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
				mBookDetailPresenter.onBookImageLoaded();
				return false;
			}
		}).into(mBookImage);
	}

	@Override
	public void setBookTitleAndDesc(Book book) {
		if (book.getTitle() != null) {
			if (getSupportActionBar() != null) {
				getSupportActionBar().setTitle(mBookName);
			}
			mTitle.setText(Html.fromHtml(book.getTitle()));
		}
		if (book.getDescription() != null) {
			mDescription.setText(Html.fromHtml(book.getDescription()));
		}
	}

	@Override
	public void startPostponedTransition() {
		supportStartPostponedEnterTransition();
	}
}
