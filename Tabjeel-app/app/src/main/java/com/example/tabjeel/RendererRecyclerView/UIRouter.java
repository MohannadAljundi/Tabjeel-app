package com.example.tabjeel.RendererRecyclerView;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tabjeel.R;
import com.example.tabjeel.RendererRecyclerView.pages.simple.CompositeViewRendererFragment;
import com.example.tabjeel.RendererRecyclerView.pages.simple.DiffUtilFragment;
import com.example.tabjeel.RendererRecyclerView.pages.simple.LoadMoreFragment;
import com.example.tabjeel.RendererRecyclerView.pages.simple.PayloadFragment;
import com.example.tabjeel.RendererRecyclerView.pages.simple.ViewBinderFragment;
import com.example.tabjeel.RendererRecyclerView.pages.simple.ViewRendererFragment;
import com.example.tabjeel.RendererRecyclerView.pages.simple.ViewStateFragment;


/**
 * Created by Vivchar Vitaly on 12/28/17.
 */

public class UIRouter {

	@NonNull
	private final Activity mContext;
	@NonNull
	private final FragmentManager mFragmentManager;

	public UIRouter(@NonNull final AppCompatActivity activity) {
		mContext = activity;
		mFragmentManager = activity.getSupportFragmentManager();
	}

	@NonNull
	public Activity getContext() {
		return mContext;
	}

	@NonNull
	public FragmentManager getFragmentManager() {
		return mFragmentManager;
	}



	private void showFragment(@NonNull final BaseScreenFragment fragment) {
		try {
			getFragmentManager().beginTransaction()
					.replace(R.id.screen_container, fragment, fragment.getClass().getName())
					.commitAllowingStateLoss();
		} catch (IllegalStateException ignored) {
		}
	}

	public void openViewRendererPage() {
		showFragment(new ViewRendererFragment());
	}

	public void openViewBinderPage() {
		showFragment(new ViewBinderFragment());
	}

	public void openCompositeViewRendererPage() {
		showFragment(new CompositeViewRendererFragment());
	}

	public void openViewStatePage() {
		showFragment(new ViewStateFragment());
	}

	public void openDiffUtilPage() {
		showFragment(new DiffUtilFragment());
	}

	public void openPayloadPage() {
		showFragment(new PayloadFragment());
	}
	public void openLoadMorePage() {
		showFragment(new LoadMoreFragment());
	}
}
