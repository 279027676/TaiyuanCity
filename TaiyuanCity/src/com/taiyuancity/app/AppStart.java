package com.taiyuancity.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.taiyuancity.R;
import com.taiyuancity.ui.AboutActivity;
import com.taiyuancity.ui.MainActivity;

/**
 * Ӧ�ó�������
 * 
 * @author neng
 * @date
 */
public class AppStart extends Activity
{
	private boolean firstTime = false;
	private SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app_start);

		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		firstTime = prefs.getBoolean("first_time", true);

		if (firstTime)
		{
			// ��һ����������
			firstStartAction();
		} else
		{
			//2000ms������������
			pauseTheActivity(AppConfig.startPicturePauseTime);
			// ������������
			playStartAnimation();
		}

	}

	/**
	 * ֹͣActivity
	 * 
	 * @param displayTime
	 */
	private void pauseTheActivity(int displayTime)
	{
		//displayTime������������
		new Handler().postDelayed(new Runnable()
		{
			public void run()
			{
				Intent mainIntent = new Intent(AppStart.this,
						MainActivity.class);
				AppStart.this.startActivity(mainIntent);
				AppStart.this.finish();
			}
		}, displayTime);
	}

	private void playStartAnimation()
	{
		//����Ķ���
		ImageView start_title = (ImageView) findViewById(R.id.start_title);
		AlphaAnimation alpha = new AlphaAnimation(0, 1);
		//RotateAnimation rotate1 = new RotateAnimation(0, 360,
		//		Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
		//		0.5f);
		TranslateAnimation translate = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
				Animation.RELATIVE_TO_SELF, 0.8f, Animation.RELATIVE_TO_SELF,
				0f);
		AnimationSet animationSet = new AnimationSet(true);
		animationSet.addAnimation(alpha);
		animationSet.addAnimation(translate);
		//animationSet.addAnimation(rotate1);
		animationSet.setDuration(1300);
		animationSet.setStartOffset(100);
		start_title.startAnimation(animationSet);
	}

	private void firstStartAction()
	{
		//����һ��������������Ϊfalse
		Editor pEdit = prefs.edit();
		pEdit.putBoolean("first_time", false);
		pEdit.commit();
		//�������ڰ���
		this.finish();//������Ż���
		Intent aboutIntent = new Intent(this, AboutActivity.class);
		startActivity(aboutIntent);

	}

}
