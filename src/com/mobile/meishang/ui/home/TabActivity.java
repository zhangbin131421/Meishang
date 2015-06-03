package com.mobile.meishang.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.MApplication;
import com.mobile.meishang.R;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.ui.home.fragments.DiscoverFragment;
import com.mobile.meishang.ui.home.fragments.HomeFragment;
import com.mobile.meishang.ui.home.fragments.MeFragment;
import com.mobile.meishang.ui.home.fragments.SignInFragment;
import com.mobile.meishang.ui.login.LoginActivity;

public class TabActivity extends MActivity {
	// private LinearLayout mCityLayout;
	// private TextView mCityTextView;
	// private TextView mTitleTextView;
	private LinearLayout mTabLayoutA;
	private LinearLayout mTabLayoutB;
	private LinearLayout mTabLayoutC;
	private LinearLayout mTabLayoutD;
	private HomeFragment homeFragment;
	private DiscoverFragment discoverFragment;
	private SignInFragment signInFragment;
	private MeFragment meFragment;
	private FragmentManager mFragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);
		if (savedInstanceState == null) {
			// new BaiduLocation(this);
			// new VersionUpdate(this);
			// new LocationNetwork(this);
		}
		// mCityLayout = (LinearLayout) findViewById(R.id.llayout_city);
		// mCityTextView = (TextView) findViewById(R.id.tv_city_name);
		// mTitleTextView = (TextView) findViewById(R.id.tv_title);
		mTabLayoutA = (LinearLayout) findViewById(R.id.flayout_tab_a);
		mTabLayoutB = (LinearLayout) findViewById(R.id.flayout_tab_b);
		mTabLayoutC = (LinearLayout) findViewById(R.id.flayout_tab_c);
		mTabLayoutD = (LinearLayout) findViewById(R.id.flayout_tab_d);
		mFragmentManager = getSupportFragmentManager();
		// mTitleTextView.setText("首页");
		mTabLayoutA.setSelected(true);
		homeFragment = new HomeFragment();
		mAddFragment(homeFragment);
		mShowFragment(homeFragment);
		// mTitleTextView.setText("生活");
		// mTabLayoutC.setSelected(true);
		// mLifeFragment = new LifeFragment();
		// addFragment(mLifeFragment);
		// showFragment(mLifeFragment);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		System.out.println(requestCode + "===" + resultCode);
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case RequestDistribute.SIGNIN_FRAGMENT:
				mTabLayoutA.setSelected(false);
				mTabLayoutB.setSelected(false);
				mTabLayoutC.setSelected(true);
				mTabLayoutD.setSelected(false);
				if (signInFragment == null) {
					signInFragment = new SignInFragment();
					mAddFragment(signInFragment);
				}
				mShowFragment(signInFragment);
				break;
			case RequestDistribute.LOGIN:
				// mHomeLayout.setSelected(false);
				// mNearbyLayout.setSelected(false);
				// mMyLayout.setSelected(true);
				// if (myFragment == null) {
				// myFragment = new MyFragment();
				// addFragment(myFragment);
				// }
				// showFragment(myFragment);
				break;

			default:
				break;
			}

		}
	}

	public void mAddFragment(Fragment fragment) {
		mFragmentManager.beginTransaction()
				.add(R.id.flayout_fragment, fragment).commit();
	}

	public void mShowFragment(Fragment fragment) {
		hideAllFragment();
		mFragmentManager.beginTransaction().show(fragment).commit();
	}

	public void hideFragment(Fragment fragment) {
		mFragmentManager.beginTransaction().hide(fragment).commit();
	}

	public void hideAllFragment() {
		if (homeFragment != null) {
			hideFragment(homeFragment);
		}
		if (discoverFragment != null) {
			hideFragment(discoverFragment);
		}
		if (signInFragment != null) {
			hideFragment(signInFragment);
		}
		if (meFragment != null) {
			hideFragment(meFragment);
		}
	}

	public void showOrHideFragment(Fragment fragment) {
		mFragmentManager.beginTransaction()
				.replace(R.id.flayout_fragment, fragment).commit();
	}

	public void showHomeFragment() {
		this.onclick(mTabLayoutA);
	}

	@Override
	public void onclick(View v) {
		super.onclick(v);

		switch (v.getId()) {

		case R.id.flayout_tab_a:
			// mCityLayout.setVisibility(View.VISIBLE);
			// mTitleTextView.setText("One Day");
			mTabLayoutA.setSelected(true);
			mTabLayoutB.setSelected(false);
			mTabLayoutC.setSelected(false);
			mTabLayoutD.setSelected(false);
			mShowFragment(homeFragment);

			break;
		case R.id.flayout_tab_b:
			// mCityLayout.setVisibility(View.VISIBLE);
			// mTitleTextView.setText("限时抢");
			mTabLayoutA.setSelected(false);
			mTabLayoutB.setSelected(true);
			mTabLayoutC.setSelected(false);
			mTabLayoutD.setSelected(false);
			if (discoverFragment == null) {
				discoverFragment = new DiscoverFragment();
				mAddFragment(discoverFragment);
			}
			mShowFragment(discoverFragment);
			break;
		case R.id.flayout_tab_c:
			if (MApplication.getInstance().checkLogin()) {
				mTabLayoutA.setSelected(false);
				mTabLayoutB.setSelected(false);
				mTabLayoutC.setSelected(true);
				mTabLayoutD.setSelected(false);
				if (signInFragment == null) {
					signInFragment = new SignInFragment();
					mAddFragment(signInFragment);
				}
				mShowFragment(signInFragment);
			} else {
				goActivityForResult(LoginActivity.class, null,
						RequestDistribute.SIGNIN_FRAGMENT);
			}
			break;
		case R.id.flayout_tab_d:
			// if (MApplication.getInstance().checkLogin()) {
			// mCityLayout.setVisibility(View.INVISIBLE);
			// mTitleTextView.setText("我");
			mTabLayoutA.setSelected(false);
			mTabLayoutB.setSelected(false);
			mTabLayoutC.setSelected(false);
			mTabLayoutD.setSelected(true);
			if (meFragment == null) {
				meFragment = new MeFragment();
				mAddFragment(meFragment);
			}
			mShowFragment(meFragment);
			// } else {
			// goActivityForResult(LoginActivity.class, null,
			// RequestDistribute.LOGIN);
			// }
			break;
		case R.id.llayout_city:
			// goActivity(CityListActivity.class, null);
			break;
		case R.id.img_scan:
			// goActivity(CaptureActivity.class, null);
			break;
		case R.id.img_edit_pen:
			// goActivity(GoWhereActivity.class, null);
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitDialog();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
