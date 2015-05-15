package com.mobile.meishang.ui.favorites;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;

import com.mobile.meishang.MFragment;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.FavoritesIntegralGoodsGviewAdapter;
import com.mobile.meishang.core.error.ExceptionHandler;
import com.mobile.meishang.core.request.FavoritesListRequest;
import com.mobile.meishang.model.LehuigoDetailData;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.utils.view.LoadingView.LoadEvent;
import com.umeng.analytics.MobclickAgent;

public class FavoritesIntegralGoodsFragment extends MFragment implements
		OnClickListener, ExceptionHandler, LoadEvent {
	private GridView gridview;
	private FrameLayout flayout_delete;
	private FavoritesIntegralGoodsGviewAdapter mAdapter;
	private List<LehuigoDetailData> lehuigoDetailDatas;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		lehuigoDetailDatas = new ArrayList<LehuigoDetailData>();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(
				R.layout.fragment_favorites_integral_goods, null);
		gridview = (GridView) view.findViewById(R.id.gridview);
		flayout_delete = (FrameLayout) view.findViewById(R.id.flayout_delete);
		flayout_delete.setOnClickListener(this);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mAdapter = new FavoritesIntegralGoodsGviewAdapter(getActivity());
		gridview.setAdapter(mAdapter);
		mAdapter.clear();
		mAdapter.addAll(lehuigoDetailDatas);
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

		switch (identity) {
		// case RequestDistribute.FAVORITES_LIST:
		// FavoritesList favoritesList = (FavoritesList) data;
		// mAdapter.clear();
		// mAdapter.addAll(favoritesList.getmList());
		// mAdapter.notifyDataSetChanged();
		// break;
		case RequestDistribute.FAVORITES_LIST_DELETE:
			break;

		default:
			break;
		}
	}

	@Override
	public void resetUI(int identity, Object data) {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.flayout_delete:
			StringBuffer collectionids = new StringBuffer();
			List<Integer> checkPositions = mAdapter.getCheckPositions();
			Collections.sort(checkPositions);
			for (int i = 0; i < checkPositions.size(); i++) {
				System.out.println("-----" + checkPositions.get(i));
				int position = checkPositions.get(i);
				collectionids.append(lehuigoDetailDatas.get(position)
						.getPurchasedid());
				collectionids.append(",");
			}
			showToast("删除");
			// Bundle bundle = new Bundle();
			// bundle.putString("type", "1");
			// bundle.putString("collectionids",
			// collectionids.substring(0, collectionids.length() - 1));
			// getActivity().getSupportLoaderManager().restartLoader(
			// RequestDistribute.FAVORITES_LIST_DELETE, bundle,
			// new FavoritesDeleteRequest(this));
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
		flayout_delete.setVisibility(View.GONE);
	}

	@Override
	public void retryAgain(View v) {

	}

	public void setLehuigoDetailDatas(List<LehuigoDetailData> lehuigoDetailDatas) {
		this.lehuigoDetailDatas = lehuigoDetailDatas;
	}

}
