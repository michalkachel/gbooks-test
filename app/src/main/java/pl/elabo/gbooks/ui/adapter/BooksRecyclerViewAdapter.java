package pl.elabo.gbooks.ui.adapter;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.elabo.gbooks.ApiConstants;
import pl.elabo.gbooks.R;
import pl.elabo.gbooks.model.Book;

public class BooksRecyclerViewAdapter extends RecyclerView.Adapter<BooksRecyclerViewAdapter.BookViewHolder> implements View.OnClickListener {

	public static final int DELAY_FOR_RIPPLE_EFFECT = 200;

	private List<Book> mBooks = new ArrayList<>();
	private OnItemClickListener mOnItemClickListener;

	public BooksRecyclerViewAdapter() {
	}

	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.mOnItemClickListener = onItemClickListener;
	}

	@Override
	public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
		v.setOnClickListener(this);
		return new BookViewHolder(v);
	}

	@Override
	public void onBindViewHolder(BookViewHolder holder, int position) {
		Book book = mBooks.get(position);
		holder.mTitle.setText(book.getTitle());
		Glide.clear(holder.mImage);
		if (book.getImageLinks() != null && book.getImageLinks().containsKey(ApiConstants.THUMBNAIL_IMAGE_KEY)) {
			Glide.with(holder.mImage.getContext()).load(book.getImageLinks().get(ApiConstants.THUMBNAIL_IMAGE_KEY)).into(holder.mImage);
		}

		holder.itemView.setTag(book);

	}

	@Override
	public int getItemCount() {
		return mBooks.size();
	}

	@Override
	public void onClick(final View v) {
		if (mOnItemClickListener != null) {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					mOnItemClickListener.onItemClick(v, ((Book) v.getTag()));
				}
			}, DELAY_FOR_RIPPLE_EFFECT);
		}
	}

	public void addBooks(List<Book> books) {
		int positionStart = getItemCount();
		mBooks.addAll(books);
		notifyItemRangeChanged(positionStart, books.size());
	}

	public interface OnItemClickListener {
		void onItemClick(View view, Book book);
	}

	protected static class BookViewHolder extends RecyclerView.ViewHolder {
		@Bind(R.id.image)
		public ImageView mImage;
		@Bind(R.id.title)
		public TextView mTitle;

		public BookViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}
}
