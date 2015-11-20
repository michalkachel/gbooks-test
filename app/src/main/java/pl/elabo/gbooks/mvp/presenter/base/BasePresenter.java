package pl.elabo.gbooks.mvp.presenter.base;


import pl.elabo.gbooks.mvp.view.base.BaseView;

public abstract class BasePresenter<T extends BaseView> {
	private T mView;

	public BasePresenter(T view) {
		mView = view;
	}

	protected T getView() {
		return mView;
	}

}
