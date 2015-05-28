package com.mobile.meishang.ui.shopping;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mobile.meishang.MActivity;
import com.mobile.meishang.R;
import com.mobile.meishang.core.request.AddressAddRequest;
import com.mobile.meishang.model.CityModel;
import com.mobile.meishang.model.DistrictModel;
import com.mobile.meishang.model.ProvinceModel;
import com.mobile.meishang.model.RequestDistribute;
import com.mobile.meishang.model.bean.Head;
import com.mobile.meishang.ui.widget.wheel.OnWheelChangedListener;
import com.mobile.meishang.ui.widget.wheel.WheelView;
import com.mobile.meishang.ui.widget.wheel.adapters.ArrayWheelAdapter;

public class ShippingAddressActivity extends MActivity implements
		OnWheelChangedListener {
	private EditText etv_name;
	private EditText etv_phone;
	private EditText etv_post;
	private TextView tv_province_city;
	private EditText etv_address;
	private WheelView mViewProvince;
	private WheelView mViewCity;
	private WheelView mViewDistrict;
	Bundle bundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shipping_address);
		TextView title = (TextView) findViewById(R.id.top_name);
		title.setText("收货地址");
		TextView tv_top_right = (TextView) findViewById(R.id.tv_top_right);
		tv_top_right.setText("确认");
		tv_top_right.setVisibility(View.VISIBLE);
		etv_name = (EditText) findViewById(R.id.etv_name);
		etv_phone = (EditText) findViewById(R.id.etv_phone);
		etv_post = (EditText) findViewById(R.id.etv_post);
		tv_province_city = (TextView) findViewById(R.id.tv_province_city);
		etv_address = (EditText) findViewById(R.id.etv_address);
		setUpViews();
		setUpListener();
		setUpData();
		etv_post.setText(mCurrentZipCode);
		// tv_province_city.setText(mCurrentProviceName + "," + mCurrentCityName
		// + "," + mCurrentDistrictName + "," + mCurrentZipCode);
		tv_province_city.setText(mCurrentProviceName + "," + mCurrentCityName
				+ "," + mCurrentDistrictName);
	}

	private void setUpViews() {
		mViewProvince = (WheelView) findViewById(R.id.id_province);
		mViewCity = (WheelView) findViewById(R.id.id_city);
		mViewDistrict = (WheelView) findViewById(R.id.id_district);
	}

	private void setUpListener() {
		// 添加change事件
		mViewProvince.addChangingListener(this);
		// 添加change事件
		mViewCity.addChangingListener(this);
		// 添加change事件
		mViewDistrict.addChangingListener(this);
		// 添加onclick事件
	}

	private void setUpData() {
		initProvinceDatas();
		mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(this,
				mProvinceDatas));
		// 设置可见条目数量
		mViewProvince.setVisibleItems(7);
		mViewCity.setVisibleItems(7);
		mViewDistrict.setVisibleItems(7);
		updateCities();
		updateAreas();
	}

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		if (wheel == mViewProvince) {
			updateCities();
		} else if (wheel == mViewCity) {
			updateAreas();
		} else if (wheel == mViewDistrict) {
			mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
			mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
		}
		etv_post.setText(mCurrentZipCode);
		tv_province_city.setText(mCurrentProviceName + "," + mCurrentCityName
				+ "," + mCurrentDistrictName);
		// showToast("当前选中:" + mCurrentProviceName + "," + mCurrentCityName +
		// ","
		// + mCurrentDistrictName + "," + mCurrentZipCode);
	}

	/**
	 * 根据当前的市，更新区WheelView的信息
	 */
	private void updateAreas() {
		int pCurrent = mViewCity.getCurrentItem();
		mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
		String[] areas = mDistrictDatasMap.get(mCurrentCityName);

		if (areas == null) {
			areas = new String[] { "" };
		}
		mViewDistrict
				.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
		mViewDistrict.setCurrentItem(0);
		
		mCurrentZipCode = mZipcodeDatasMap.get(areas[0]);
		mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[0];
		
	}

	/**
	 * 根据当前的省，更新市WheelView的信息
	 */
	private void updateCities() {
		int pCurrent = mViewProvince.getCurrentItem();
		mCurrentProviceName = mProvinceDatas[pCurrent];
		String[] cities = mCitisDatasMap.get(mCurrentProviceName);
		if (cities == null) {
			cities = new String[] { "" };
		}
		mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
		mViewCity.setCurrentItem(0);
		updateAreas();
	}

	@Override
	public void onclick(View v) {
		super.onclick(v);
		switch (v.getId()) {
		case R.id.tv_top_right:
			net();
			break;
		default:
			break;
		}
	}

	private void net() {
		// name：收货人姓名phone：收货人手机号码post：邮编address：地址addresss：详细地址userid：用户编号
		String name = etv_name.getText().toString();
		String phone = etv_phone.getText().toString();
		String post = etv_post.getText().toString();
		String address = tv_province_city.getText().toString();
		String addresss = etv_address.getText().toString();
		if (TextUtils.isEmpty(name)) {
			showToast("请输入收货人姓名");
			return;
		}
		if (TextUtils.isEmpty(phone)) {
			showToast("请输入收货人手机号码");
			return;
		}
		if (TextUtils.isEmpty(post)) {
			showToast("请输入邮编");
			return;
		}
		// if (TextUtils.isEmpty(address)) {
		// showToast("请输入收货人姓名");
		// return;
		// }
		if (TextUtils.isEmpty(addresss)) {
			showToast("详细地址");
			return;
		}

		bundle = new Bundle();
		bundle.putString("name", name);
		bundle.putString("phone", phone);
		bundle.putString("post", post);
		bundle.putString("address", address);
		bundle.putString("addresss", addresss);
		getSupportLoaderManager().restartLoader(
				RequestDistribute.SHIPPING_ADDRESS, bundle,
				new AddressAddRequest(this));
	}

	@Override
	public void updateUI(int identity, Object data) {
		// super.updateUI(identity, data);
		switch (identity) {
		case RequestDistribute.SHIPPING_ADDRESS:
			Head h = (Head) data;
			if (h.isSuccess()) {
				Intent intent = new Intent();
				intent.putExtras(bundle);
				setResult(RESULT_OK, intent);
				finish();

			} else {
				showToast(h.getMessage());
			}
			break;

		default:
			break;
		}
	}

	/**
	 * 所有省
	 */
	protected String[] mProvinceDatas;
	/**
	 * key - 省 value - 市
	 */
	protected Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
	/**
	 * key - 市 values - 区
	 */
	protected Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();

	/**
	 * key - 区 values - 邮编
	 */
	protected Map<String, String> mZipcodeDatasMap = new HashMap<String, String>();

	/**
	 * 当前省的名称
	 */
	protected String mCurrentProviceName;
	/**
	 * 当前市的名称
	 */
	protected String mCurrentCityName;
	/**
	 * 当前区的名称
	 */
	protected String mCurrentDistrictName = "";

	/**
	 * 当前区的邮政编码
	 */
	protected String mCurrentZipCode = "";

	/**
	 * 解析省市区的XML数据
	 */

	protected void initProvinceDatas() {
		List<ProvinceModel> provinceList = null;
		AssetManager asset = getAssets();
		try {
			InputStream input = asset.open("province_data.xml");
			// 创建一个解析xml的工厂对象
			SAXParserFactory spf = SAXParserFactory.newInstance();
			// 解析xml
			SAXParser parser = spf.newSAXParser();
			XmlParserHandler handler = new XmlParserHandler();
			parser.parse(input, handler);
			input.close();
			// 获取解析出来的数据
			provinceList = handler.getDataList();
			// */ 初始化默认选中的省、市、区
			if (provinceList != null && !provinceList.isEmpty()) {
				mCurrentProviceName = provinceList.get(0).getName();
				List<CityModel> cityList = provinceList.get(0).getCityList();
				if (cityList != null && !cityList.isEmpty()) {
					mCurrentCityName = cityList.get(0).getName();
					List<DistrictModel> districtList = cityList.get(0)
							.getDistrictList();
					mCurrentDistrictName = districtList.get(0).getName();
					mCurrentZipCode = districtList.get(0).getZipcode();
				}
			}
			// */
			mProvinceDatas = new String[provinceList.size()];
			for (int i = 0; i < provinceList.size(); i++) {
				// 遍历所有省的数据
				mProvinceDatas[i] = provinceList.get(i).getName();
				List<CityModel> cityList = provinceList.get(i).getCityList();
				String[] cityNames = new String[cityList.size()];
				for (int j = 0; j < cityList.size(); j++) {
					// 遍历省下面的所有市的数据
					cityNames[j] = cityList.get(j).getName();
					List<DistrictModel> districtList = cityList.get(j)
							.getDistrictList();
					String[] distrinctNameArray = new String[districtList
							.size()];
					DistrictModel[] distrinctArray = new DistrictModel[districtList
							.size()];
					for (int k = 0; k < districtList.size(); k++) {
						// 遍历市下面所有区/县的数据
						DistrictModel districtModel = new DistrictModel(
								districtList.get(k).getName(), districtList
										.get(k).getZipcode());
						// 区/县对于的邮编，保存到mZipcodeDatasMap
						mZipcodeDatasMap.put(districtList.get(k).getName(),
								districtList.get(k).getZipcode());
						distrinctArray[k] = districtModel;
						distrinctNameArray[k] = districtModel.getName();
					}
					// 市-区/县的数据，保存到mDistrictDatasMap
					mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
				}
				// 省-市的数据，保存到mCitisDatasMap
				mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {

		}
	}
}
