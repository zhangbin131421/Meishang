package com.mobile.meishang.ui.favorites;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mobile.meishang.MFragment;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.FavoritesProjectListviewAdapter;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.FavoritesListRequest;
import com.mobile.meishang.model.Discover;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.umeng.analytics.MobclickAgent;

public class FavoritesProjectListFragment extends MFragment implements
		ExceptionHandler, LoadEvent {
	private ListView listview;
	private FavoritesProjectListviewAdapter mAdapter;
	private List<Discover> discovers;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		discovers = new ArrayList<Discover>();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_favorites_info_listview,
				null);
		listview = (ListView) view.findViewById(R.id.listview);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mAdapter = new FavoritesProjectListviewAdapter(getActivity());
		listview.setAdapter(mAdapter);
		mAdapter.clear();
		mAdapter.addAll(discovers);
		mAdapter.notifyDataSetChanged();
		// net();

	}

	private void net() {
		Bundle bundle = new Bundle();
		bundle.putString("", "");
		getActivity().getSupportLoaderManager().restartLoader(
				RequestDistribute.FAVORITES_LIST, bundle,
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

		// switch (identity) {
		// case RequestDistribute.FAVORITES_LIST:
		// FavoritesList favoritesList = (FavoritesList) data;
		// mAdapter.clear();
		// mAdapter.addAll(favoritesList.getmList());
		// mAdapter.notifyDataSetChanged();
		// break;
		//
		// default:
		// break;
		// }
	}

	@Override
	public void resetUI(int identity, Object data) {

	}

	@Override
	public void retryAgain(View v) {

	}

	public void setDiscovers(List<Discover> discovers) {
		this.discovers = discovers;
	}

}
