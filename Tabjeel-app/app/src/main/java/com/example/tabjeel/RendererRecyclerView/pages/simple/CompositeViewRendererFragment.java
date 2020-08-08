package com.example.tabjeel.RendererRecyclerView.pages.simple;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tabjeel.R;
import com.example.tabjeel.RendererRecyclerView.BaseScreenFragment;
import com.example.tabjeel.RendererRecyclerView.widgets.BetweenSpacesItemDecoration;
import com.github.vivchar.rendererrecyclerviewadapter.DefaultCompositeViewModel;
import com.github.vivchar.rendererrecyclerviewadapter.RendererRecyclerViewAdapter;
import com.github.vivchar.rendererrecyclerviewadapter.ViewRenderer;
import com.github.vivchar.rendererrecyclerviewadapter.binder.CompositeViewBinder;
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder;

import java.util.Collections;

/**
 * Created by Vivchar Vitaly on 12/28/17.
 */
public class CompositeViewRendererFragment extends BaseScreenFragment {

	private YourDataProvider mYourDataProvider = new YourDataProvider();

	@Nullable
	@Override
	public View onCreateView(final LayoutInflater inflater,
	                         @Nullable final ViewGroup container,
	                         @Nullable final Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.fragment_list, container, false);

		final RendererRecyclerViewAdapter adapter = new RendererRecyclerViewAdapter();

		adapter.registerRenderer(
				new CompositeViewBinder<>(
						R.layout.item_simple_composite,
						R.id.recycler_view,
						DefaultCompositeViewModel.class,
						Collections.singletonList(new BetweenSpacesItemDecoration(10, 10))
				).registerRenderer(getAnyViewRenderer())
		);
//		adapter.registerRenderer(...);
//		adapter.registerRenderer(...);

		adapter.setItems(mYourDataProvider.getCompositeSimpleItems());

		final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
		recyclerView.setAdapter(adapter);
		recyclerView.addItemDecoration(new BetweenSpacesItemDecoration(10, 10));

		return view;
	}

	private ViewRenderer getAnyViewRenderer() {
		return new ViewBinder<>(
				R.layout.item_simple_square,
				DiffUtilFragment.DiffViewModel.class,
				(model, finder, payloads) -> finder.setText(R.id.text, model.getText())
		);
	}
}