package com.example.tabjeel.RendererRecyclerView;

import androidx.annotation.NonNull;

import io.reactivex.functions.Consumer;

import static com.example.tabjeel.RendererRecyclerView.MenuItemID.COMPOSITE_VIEW_RENDERER;
import static com.example.tabjeel.RendererRecyclerView.MenuItemID.DIFF_UTIL;
import static com.example.tabjeel.RendererRecyclerView.MenuItemID.DONE;
import static com.example.tabjeel.RendererRecyclerView.MenuItemID.LOAD_MORE;
import static com.example.tabjeel.RendererRecyclerView.MenuItemID.MAIN;
import static com.example.tabjeel.RendererRecyclerView.MenuItemID.PAYLOAD;
import static com.example.tabjeel.RendererRecyclerView.MenuItemID.VIEW_BINDER;
import static com.example.tabjeel.RendererRecyclerView.MenuItemID.VIEW_RENDERER;
import static com.example.tabjeel.RendererRecyclerView.MenuItemID.VIEW_STATE;

/**
 * Created by Vivchar Vitaly on 12/29/17.
 */

public class MainPresenter extends BasePresenter {

	@NonNull
	private final OptionsMenuController mMenuController;
	@NonNull
	private final UIRouter mUIRouter;

	public MainPresenter(@NonNull final OptionsMenuController menuController, @NonNull final UIRouter UIRouter, final boolean firstInit) {
		mMenuController = menuController;
		mUIRouter = UIRouter;
		mMenuController.hideMenuItem(DONE);
	}

	@Override
	public void viewShown() {
		addSubscription(mMenuController.getItemSelection().subscribe(new Consumer<Integer>() {
			@Override
			public void accept(Integer menuItemID) throws Exception {
				@MenuItemID final int menuID = menuItemID;
				switch (menuID) {
					case DONE:
						break;
					case DIFF_UTIL:
						mUIRouter.openDiffUtilPage();
						break;
					case PAYLOAD:
						mUIRouter.openPayloadPage();
						break;
					case VIEW_STATE:
						mUIRouter.openViewStatePage();
						break;
					case LOAD_MORE:
						mUIRouter.openLoadMorePage();
						break;
					case VIEW_BINDER:
						mUIRouter.openViewBinderPage();
						break;
					case COMPOSITE_VIEW_RENDERER:
						mUIRouter.openCompositeViewRendererPage();
						break;
					case VIEW_RENDERER:
						mUIRouter.openViewRendererPage();
						break;
				}
			}
		}));
	}
}
