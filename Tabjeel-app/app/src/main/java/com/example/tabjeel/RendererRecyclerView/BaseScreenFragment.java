package com.example.tabjeel.RendererRecyclerView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tabjeel.MainActivity;

/**
 * Created by Vivchar Vitaly on 12/28/17.
 */

public class BaseScreenFragment extends Fragment {


	@NonNull
	public UIRouter getUIRouter() {
		return ((MainActivity)getActivity()).getUIRouter();
	}

	@NonNull
	public OptionsMenuController getMenuController() {
		return ((MainActivity)getActivity()).getMenuController();
	}
}