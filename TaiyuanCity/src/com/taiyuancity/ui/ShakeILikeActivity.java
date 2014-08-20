package com.taiyuancity.ui;

import android.app.Activity;
import android.app.Service;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.taiyuancity.R;
import com.taiyuancity.util.ShakeListenerUtils;

/**
 * "ҡ��ϲ��"������. ���𶯻�.
 * 
 * @author neng
 * @date
 */
public class ShakeILikeActivity extends Activity
{

	private ShakeListenerUtils shakeUtils;
	private SensorManager mSensorManager; //����sensor������, ע���������

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shake_i_like);

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
				//����SENSOR_DELAY_UI��SENSOR_DELAY_FASTEST��SENSOR_DELAY_GAME�ȣ�  
				//���ݲ�ͬӦ�ã���Ҫ�ķ�Ӧ���ʲ�ͬ���������ʵ������趨  
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause()
	{
		mSensorManager.unregisterListener(shakeUtils);
		this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);//����ʱ��һ��
		super.onPause();
	}

}
