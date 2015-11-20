package pl.elabo.gbooks.ui.activity.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.elabo.gbooks.R;
import pl.elabo.gbooks.mvp.view.base.BaseView;
import pl.elabo.gbooks.util.NotificationManager;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

	@Nullable
	@Bind(R.id.toolbar)
	Toolbar mToolbar;

	@LayoutRes
	protected abstract int getLayoutId();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutId());
		handleArguments();
		ButterKnife.bind(this);

		if (mToolbar != null) {
			setSupportActionBar(mToolbar);
		}
	}

	protected void handleArguments() {
	}

	@Override
	public void showError(Throwable throwable) {
		NotificationManager.showMessage(this, "An error has occured: " + throwable.getMessage(), true);
	}
}
