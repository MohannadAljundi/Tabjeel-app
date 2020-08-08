package com.example.tabjeel.RendererRecyclerView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tabjeel.R;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;


/**
 * Created by Vivchar Vitaly on 12/29/17.
 */

public class OptionsMenuController {

	@NonNull
	private final AppCompatActivity mContext;

	@NonNull
	private final ArrayList<Integer> mInvisibleItems = new ArrayList<>();
	@NonNull
	private final Subject<Integer> mItemSelection = PublishSubject.create();

	public OptionsMenuController(@NonNull final AppCompatActivity context) {
		mContext = context;
	}

	public void onCreateOptionsMenu(@NonNull final Menu menu, @NonNull final MenuInflater inflater) {
		inflater.inflate(R.menu.menu_recicle, menu);
	}

	public void onPrepareOptionsMenu(@NonNull final Menu menu, @NonNull final MenuInflater menuInflater) {
		for (@MenuItemID final int menuItemID : MenuItemID.ALL) {
			menu.findItem(menuItemID).setVisible(!mInvisibleItems.contains(menuItemID));
		}
	}

	public void showMenuItem(@MenuItemID final int itemID) {
		if (mInvisibleItems.contains(itemID)) {
			mInvisibleItems.remove(Integer.valueOf(itemID));
			mContext.invalidateOptionsMenu();
		}
	}

	public void hideMenuItem(@MenuItemID final int itemID) {
		if (!mInvisibleItems.contains(itemID)) {
			mInvisibleItems.add(itemID);
			mContext.invalidateOptionsMenu();
		}
	}

	public void onOptionsItemSelected(@NonNull final MenuItem item) {
		mItemSelection.onNext(item.getItemId());
	}

	@NonNull
	public Observable<Integer> getItemSelection() {
		return mItemSelection.hide();
	}
}
