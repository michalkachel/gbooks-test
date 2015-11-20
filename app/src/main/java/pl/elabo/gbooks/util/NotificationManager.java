package pl.elabo.gbooks.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;

public class NotificationManager {

	public static void showMessage(@NonNull Context context, @NonNull String message, boolean longDuration) {
		Toast.makeText(context, message, longDuration ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
	}

	public static void showMessage(@NonNull Context context, @StringRes int stringRes, boolean longDuration) {
		showMessage(context, context.getString(stringRes), longDuration);
	}
}
