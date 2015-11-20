package pl.elabo.gbooks.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import butterknife.Bind;
import pl.elabo.gbooks.R;
import pl.elabo.gbooks.model.Book;
import pl.elabo.gbooks.mvp.presenter.BooksListPresenter;
import pl.elabo.gbooks.mvp.presenter.BooksListPresenterImpl;
import pl.elabo.gbooks.mvp.view.BooksListView;
import pl.elabo.gbooks.ui.activity.base.BaseActivity;
import pl.elabo.gbooks.ui.adapter.BooksRecyclerViewAdapter;

public class BooksListActivity extends BaseActivity implements BooksListView, BooksRecyclerViewAdapter.OnItemClickListener {

	@Bind(R.id.books_recycler_view)
	RecyclerView mBooksRecyclerView;
	private BooksListPresenter mBooksListPresenter;
	private StaggeredGridLayoutManager mLayoutManager;
	private BooksRecyclerViewAdapter mBooksRecyclerViewAdapter;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_books_list;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (mBooksListPresenter == null) {
			mBooksListPresenter = new BooksListPresenterImpl(this);
		}

		mBooksListPresenter.onCreate();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mBooksListPresenter.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mBooksListPresenter.onDestroy();
	}

	@Override
	public void setupList() {
		mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
		mBooksRecyclerView.setLayoutManager(mLayoutManager);
		mBooksRecyclerViewAdapter = new BooksRecyclerViewAdapter();
		mBooksRecyclerViewAdapter.setOnItemClickListener(this);
		mBooksRecyclerView.setAdapter(mBooksRecyclerViewAdapter);
	}

	@Override
	public void enableScrollListener() {
		mBooksRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				int[] postionsTab = mLayoutManager.findLastVisibleItemPositions(null);
				if (postionsTab.length > 0 && (postionsTab[0] == mLayoutManager.getItemCount() - 1 || (postionsTab[postionsTab.length - 1] == mLayoutManager.getItemCount() - 1))) {
					mBooksListPresenter.onScrolledDown();
				}
			}
		});
	}

	@Override
	public void disableScrollListener() {
		mBooksRecyclerView.setOnScrollListener(null);
	}

	@Override
	public void addBooks(List<Book> books) {
		mBooksRecyclerViewAdapter.addBooks(books);
	}

	@Override
	public void onItemClick(View view, Book book) {
		BookDetailActivity.start(this, book.getIdentifier(), book.getTitle(), (ImageView) view.findViewById(R.id.image));
	}
}
