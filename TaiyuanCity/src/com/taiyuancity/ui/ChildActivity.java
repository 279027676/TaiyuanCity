package com.taiyuancity.ui;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.taiyuancity.R;
import com.taiyuancity.bean.MyFavourite;
import com.taiyuancity.dao.MyFavouriteDAO;
import com.taiyuancity.util.ConstantUtils;
import com.taiyuancity.util.ShakeListenerUtils;

/**
 * ��ɶ�����ʾҳ����Զ�����.
 * 
 * @author neng
 * @date 2013-2-16
 */
public class ChildActivity extends Activity
{
	private int layoutId;
	private Intent intent;
	private ImageButton back;
	private ImageButton favourite;
	private MyFavourite myFavourite;//��ʱ�洢
	private TextView title;
	private ImageView pic;
	private TextView content;

	private SensorManager mSensorManager; //����sensor������, ע���������
	private ShakeListenerUtils shakeUtils;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		//����MainActivity���ݹ�����id
		intent = getIntent();
		layoutId = intent.getIntExtra("id", R.layout.activity_child_lcty1);//��һ����Ĭ��ֵ

		setContentView(layoutId);
		myFavourite = ConstantUtils.getMyFavourite(layoutId);


		//���ñ���.(�����ǲ���Ĳ���)
		title = (TextView) findViewById(R.id.child_title);
		title.setText(myFavourite.getTitle());

		//����ͼƬ.
		pic = (ImageView) findViewById(R.id.child_pic);
		pic.setBackgroundResource(myFavourite.getImageId());

		//��������
		content = (TextView) findViewById(R.id.child_text);
		content.setText(myFavourite.getContent());

		//���ð�ť�¼�
		back = (ImageButton) findViewById(R.id.child_head_back);
		favourite = (ImageButton) findViewById(R.id.child_head_favorite);

		back.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				ChildActivity.this.finish();
			}
		});

		favourite.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				MyFavouriteDAO myFavouriteDAO = new MyFavouriteDAO(
						ChildActivity.this);
				if (myFavouriteDAO.find(layoutId) == layoutId)
				{
					Toast.makeText(ChildActivity.this,
							getString(R.string.db_my_favourite_tip),
							Toast.LENGTH_LONG).show();
				} else
				{
					myFavouriteDAO.add(layoutId);
					Toast.makeText(ChildActivity.this,
							getString(R.string.db_my_favourite_add),
							Toast.LENGTH_LONG).show();
				}

			}
		});

		//ҡ������
		shakeUtils = new ShakeListenerUtils(this);
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		//��ȡ������������� 
		mSensorManager = (SensorManager) this
				.getSystemService(Service.SENSOR_SERVICE);
		//���ٶȴ�����  
		mSensorManager.registerListener(shakeUtils,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onStop()
	{
		mSensorManager.unregisterListener(shakeUtils);
		super.onStop();
	}

	@Override
	protected void onPause()
	{
		mSensorManager.unregisterListener(shakeUtils);
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_child, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case R.id.menu_my_collection:
			Intent intent = new Intent(this, MyFavoriteActivity.class);
			this.startActivity(intent);
			break;

		case R.id.menu_about:
			Intent aboutIntent = new Intent(this, AboutActivity.class);
			startActivity(aboutIntent);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
