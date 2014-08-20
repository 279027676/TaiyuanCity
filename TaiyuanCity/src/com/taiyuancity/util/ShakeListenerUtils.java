package com.taiyuancity.util;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import com.taiyuancity.R;
import com.taiyuancity.ui.OpenDoorActivity;

/**
 * ������ҡ�μ��, ��Ч������
 * 
 * @author neng
 * @date 2013-2-15
 */
public class ShakeListenerUtils implements SensorEventListener
{
	private Activity context;

	public ShakeListenerUtils(Activity context)
	{
		super();
		this.context = context;
	}

	@Override
	public void onSensorChanged(SensorEvent event)
	{
		int sensorType = event.sensor.getType();
		//values[0]:X�ᣬvalues[1]��Y�ᣬvalues[2]��Z��  
		float[] values = event.values;

		if (sensorType == Sensor.TYPE_ACCELEROMETER)
		{

			/*��������£���������ֵ������9.8~10֮�䣬ֻ����ͻȻҡ���ֻ� 
			  ��ʱ��˲ʱ���ٶȲŻ�ͻȻ�������١�   ������һ��ļ��ٶȴ���17����
			*/
			if ((Math.abs(values[0]) > 17 || Math.abs(values[1]) > 17 || Math
					.abs(values[2]) > 17))
			{
				context.overridePendingTransition(R.anim.zoom_out_enter,
						R.anim.zoom_out_exit);
				//��⵽�ζ�������OpenDoorЧ��
				Intent intent = new Intent(context, OpenDoorActivity.class);
				context.startActivity(intent);
				context.finish();
			}
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
		//�����������ȸı�ʱ�ص��÷�����Do nothing. 
	}

}
