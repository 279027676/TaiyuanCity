package com.taiyuancity.ui;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.taiyuancity.R;
import com.taiyuancity.R.color;
import com.taiyuancity.util.RandomUtils;

/**
 * "ҡ��ϲ��"������. ���𶯻�.
 * 
 * @author neng
 * @date
 */
public class OpenDoorActivity extends Activity
{
	private RelativeLayout mainRLayout;
	private LinearLayout leftLayout;
	private LinearLayout rightLayout;
	private LinearLayout animLayout;

	private Vibrator vibrator; //��  
	private MediaPlayer mediaPlayer; //��������

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_open_door);
		//music
		mediaPlayer = new MediaPlayer();
		mediaPlayer = MediaPlayer.create(this, R.raw.open_door);
		//��  
		vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
		//findView
		findViews();
		//���Ŷ���
		playAnimationOpenDoor();
	}

	private void findViews()
	{
		animLayout = (LinearLayout) findViewById(R.id.animLayout);
		leftLayout = (LinearLayout) findViewById(R.id.leftLayout);
		rightLayout = (LinearLayout) findViewById(R.id.rightLayout);
		mainRLayout = (RelativeLayout) findViewById(R.id.mainRLayout);

	}

	public void playAnimationOpenDoor()
	{
		animLayout.setVisibility(View.VISIBLE);
		mainRLayout.setBackgroundResource(R.drawable.whatsnew_bg);
		Animation leftOutAnimation = AnimationUtils.loadAnimation(
				getApplicationContext(), R.anim.translate_left);
		Animation rightOutAnimation = AnimationUtils.loadAnimation(
				getApplicationContext(), R.anim.translate_right);
		leftLayout.setAnimation(leftOutAnimation);
		rightLayout.setAnimation(rightOutAnimation);
		leftOutAnimation.setAnimationListener(new AnimationListener()
		{
			@Override
			public void onAnimationStart(Animation animation)
			{
				mainRLayout.setBackgroundColor(color.bgColor);
			}

			@Override
			public void onAnimationRepeat(Animation animation)
			{
			}

			@Override
			public void onAnimationEnd(Animation animation)
			{
				leftLayout.setVisibility(View.GONE);
				rightLayout.setVisibility(View.GONE);
				//������activity
				Intent intent = new Intent(OpenDoorActivity.this,
						ChildActivity.class);
				intent.putExtra("id", RandomUtils.randomLayout());//�������layout
				OpenDoorActivity.this.startActivity(intent);
				OpenDoorActivity.this.finish();
				//����Ч��
				overridePendingTransition(R.anim.zoom_out_enter,
						R.anim.zoom_out_exit);
			}
		});
		//����ʾ
		vibrator.vibrate(200);
		//��������
		mediaPlayer.start();
	}

}
