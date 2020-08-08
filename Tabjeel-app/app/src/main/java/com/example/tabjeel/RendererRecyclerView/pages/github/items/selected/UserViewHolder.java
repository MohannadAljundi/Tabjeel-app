package com.example.tabjeel.RendererRecyclerView.pages.github.items.selected;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tabjeel.R;
import com.github.vivchar.rendererrecyclerviewadapter.ViewHolder;

/**
 * Created by Vivchar Vitaly on 1/10/17.
 */
public class UserViewHolder extends ViewHolder {

	public final TextView name;
	public final ImageView avatar;

	public UserViewHolder(final View itemView) {
		super(itemView);
		avatar = (ImageView) itemView.findViewById(R.id.avatar);
		name = (TextView) itemView.findViewById(R.id.name);
	}
}
