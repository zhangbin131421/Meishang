package com.mobile.meishang.ui.favorites;

import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.mobile.meishang.MFragment;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.FavoritesProjectListviewAdapter;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.FavoritesDeleteRequest;
import com.mobile.meishang.core.request.FavoritesListRequest;
import com.mobile.meishang.model.Discover;
import com.mobile.meishang.model.FavoritesList;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.Head;
import com.mobile.meishang.utils.view.LoadingView;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.umeng.analytics.MobclickAgent;

public class FavoritesProjectListFragment extends MFragment implements
		OnClickListener, ExceptionHandler, LoadEvent {
	private LoadingView mLoadingView;
	private ListView listview;
	private FavoritesProjectListviewAdapter mAdapter;
	private List<Discover> discovers;
	private FrameLayout flayout_delete;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_favorites_info_listview,
				null);
		mLoadingView = (LoadingView) view.findViewById(R.id.loading);
		mLoadingView.setLoadEvent(this);
		listview = (ListView) view.findViewById(R.id.listview);
		flayout_delete = (FrameLayout) view.findViewById(R.id.flayout_delete);
		flayout_delete.setOnClickListener(this);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mAdapter = new FavoritesProjectListviewAdapter(getActivity());
		listview.setAdapter(mAdapter);
		// mAdapter.clear();
		// mAdapter.addAll(discovers);
		// mAdapter.notifyDataSetChanged();
		net();

	}

	private void net() {
		// Bundle bundle = new Bundle();
		// bundle.putString("", "");
		getActivity().getSupportLoaderManager().restartLoader(
				RequestDistribute.FAVORITES_LIST, null,
				new FavoritesListRequest(this));
	}

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(mContext);
	}

	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(mContext);
	}

	@Override
	public void handleException(int identity, Exception e) {

	}

	@Override
	public void updateUI(int identity, Object data) {
		mLoadingView.setVisibility(View.GONE);
		switch (identity) {
		case RequestDistribute.FAVORITES_LIST:
			FavoritesList favoritesList = (FavoritesList) data;
			discovers = favoritesList.getDiscovers();
			mAdapter.clear();
			mAdapter.addAll(discovers);
			mAdapter.notifyDataSetChanged();
			break;
		case RequestDistribute.FAVORITES_LIST_DELETE:
			Head head = (Head) data;
			if (head.isSuccess()) {
				net();
			}
			showToast(head.getMessage());
			break;
		default:
			break;
		}
	}

	@Override
	public void resetUI(int identity, Object data) {

	}

	@Override
	public void retryAgain(View v) {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.flayout_delete:
			StringBuffer collectionids = new StringBuffer();
			List<Integer> checkPositions = mAdapter.getCheckPositions();
			Collections.sort(checkPositions);
			for (int i = 0; i < checkPositions.size(); i++) {
				int position = checkPositions.get(i);
				collectionids.append(discovers.get(position)
						.getProjectid());
				collectionids.append(",");
			}
			Bundle bundle = new Bundle();
			bundle.putString("type", "2");
			bundle.putString("collectionids",
					collectionids.substring(0, collectionids.length() - 1));
			getActivity().getSupportLoaderManager().restartLoader(
					RequestDistribute.FAVORITES_LIST_DELETE, bundle,
					new FavoritesDeleteRequest(this));
			break;
		default:
			break;
		}

	}

	public void showDelete() {
		mAdapter.setEdit(true);
		mAdapter.notifyDataSetChanged();
		flayout_delete.setVisibility(View.VISIBLE);
	}

	public void hideDelete() {
		mAdapter.setEdit(false);
		mAdapter.notifyDataSetChanged();
		flayout_delete.setVisibility(View.GONE);
	}
}
