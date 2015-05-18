package com.mobile.meishang.ui.bid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.adapter.BidDetailGridviewAdapter;
import com.mobile.meishang.core.request.BidDetailRequest;
import com.mobile.meishang.model.Bid;
import com.mobile.meishang.model.BidMyPublish;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.ui.widget.GridViewWithHeaderAndFooter;
import com.umeng.analytics.MobclickAgent;

public class BidDetailActivity extends MActivity implements OnClickListener {
	private GridViewWithHeaderAndFooter mGridView;
	private BidDetailGridviewAdapter mAdapter;
	private View headView;
	private Bundle mBundle;
	TextView tv_title;
	TextView tv_item;
	TextView tv_tel;
	TextView tv_proaddress;
	TextView tv_prodesc;
	TextView tv_count;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBundle = getIntent().getBundleExtra("bundle");
		setContentView(R.layout.activity_bid_detail);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("竞标详情");
		mGridView = (GridViewWithHeaderAndFooter) findViewById(R.id.gridview);
		headView = LayoutInflater.from(this).inflate(
				R.layout.layout_bid_detail_hview, null);
		tv_title = (TextView) headView.findViewById(R.id.tv_title);
		tv_item = (TextView) headView.findViewById(R.id.tv_item);
		tv_tel = (TextView) headView.findViewById(R.id.tv_tel);
		tv_proaddress = (TextView) headView.findViewById(R.id.tv_proaddress);
		tv_prodesc = (TextView) headView.findViewById(R.id.tv_prodesc);
		tv_count = (TextView) headView.findViewById(R.id.tv_count);
		mGridView.addHeaderView(headView, null, false);
		mAdapter = new BidDetailGridviewAdapter(this);
		mGridView.setAdapter(mAdapter);
		getSupportLoaderManager().restartLoader(RequestDistribute.BID_DETAIL,
				mBundle, new BidDetailRequest(this));
	}

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	@Override
	public void handleException(final int identity, final Exception e) {
		// super.handleException(identity, e);
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (identity == RequestDistribute.BID_DETAIL) {
					// mLoadingView.showRetryBtn(true);
					showToast(e.getMessage());
				}
			}
		});
	}

	@Override
	public void updateUI(int identity, Object data) {
		if (data != null) {
			switch (identity) {
			case RequestDistribute.BID_DETAIL:
				BidMyPublish bidMyPublish = (BidMyPublish) data;
				Bid bidding = bidMyPublish.getBidding();
				tv_title.setText(bidding.getTitle());
				tv_item.setText(bidding.getItem());
//				tv_tel.setText(bidding.getp);
				tv_proaddress.setText(bidding.getProaddress());
				tv_prodesc.setText(bidding.getProdesc());
				tv_count.setText("参与竞标用户" + bidMyPublish.getCount() + "人");
				break;

			default:
				break;
			}
		} else {
			// showToast("数据对象空");
		}
	}

	@Override
	public void resetUI(int identity, Object data) {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// case R.id.llayout_gowhere:
		// // goActivity(GoWhereActivity.class, null);
		// break;
		// case R.id.llayout_write_notes:
		// goActivity(MyTravelNotesListActivity.class, null);
		// break;
		// case R.id.llayout_active:
		// // goActivity(InitiateActivityActivity.class, null);
		// goActivity(TravelNotesDetailActivity.class, null);
		// break;
		// case R.id.llayout_near:
		// break;

		default:
			break;
		}
	}

}
