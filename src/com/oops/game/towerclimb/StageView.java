package com.oops.game.towerclimb;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.oops.game.towerclimb.common.Creature;

public class StageView extends SurfaceView implements SurfaceHolder.Callback {
	
	private boolean turnOff = false;
	private StageThread stageThread;
	
	private Creature a = new Creature();
	private Creature b = new Creature();
	
	public StageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	
	public StageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}
	
	public StageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}
	
	public void setTurnOff() {
		turnOff = true;
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.d("StageView", "surfaceCreated");
		stageThread = new StageThread(holder);
		stageThread.setThreadRunning(true);
		stageThread.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		Log.d("StageView", "surfaceChanged");
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.d("StageView", "surfaceDestroyed");
		if(stageThread != null && stageThread.isAlive()) {
			stageThread.setThreadRunning(false);
			stageThread.interrupt();
		}
	}

	public void init() {
//		getHolder().setFormat(PixelFormat.TRANSPARENT);
		getHolder().addCallback(this);
		
		a.currentActionPoint = 10;
		a.currentHeatPoint = 100;
		a.currentMagicPoint = 100;
		
		a.maxActionPoint = 10;
		a.maxHeatPoint = 100;
		a.maxManaPoint = 100;
		
		b.currentActionPoint = 30;
		b.currentHeatPoint = 100;
		b.currentMagicPoint = 100;
		
		b.maxActionPoint = 30;
		b.maxHeatPoint = 500;
		b.maxManaPoint = 500;
	}
	
	public class StageThread extends Thread {
		
		private SurfaceHolder sHolder;
		private boolean threadRunning = false;
		
		public StageThread ( SurfaceHolder holder ) {
			if(holder == null ) return;
			sHolder = holder;
		}
		
		public boolean isThreadRunning() {
			return threadRunning;
		}
		
		public void setThreadRunning(boolean runningState) {
			// When you need to destroy surfaceview. 
			this.threadRunning = runningState;
		}
		
		public void run() {
			super.run();
			
			while (threadRunning) {
				
				synchronized (sHolder) {
					
					// perform other operations
					Canvas mCanvas = sHolder.lockCanvas();
					if ( mCanvas == null ) return;
					mCanvas.drawColor(Color.TRANSPARENT, Mode.CLEAR);
			        
			        if ( turnOff == true ) 
			        {
			        	Log.d("YSK", "A : " + a.currentActionPoint);
			        	Log.d("YSK", "B : " + b.currentActionPoint);
			        	
			        	if ( a.currentActionPoint > 0 )
			        	{
			        		a.currentActionPoint--;
			        	}
			        	else 
			        	{
			        		a.currentActionPoint = a.maxActionPoint;
			        	}
			        	
			        	if ( b.currentActionPoint > 0 )
			        	{
			        		b.currentActionPoint--;
			        	}
			        	else 
			        	{
			        		b.currentActionPoint = b.maxActionPoint;
			        	}
			        	turnOff = false;
			        }
			        
			        Paint pnt = new Paint();
			        pnt.setShader(new LinearGradient(0, -50, 0, 50, Color.BLACK, Color.WHITE, Shader.TileMode.MIRROR));
			        mCanvas.drawRect(0, 0, 50, 50, pnt);
			        
					if (mCanvas != null) {
						sHolder.unlockCanvasAndPost(mCanvas);
					}
					
				}
				
			}
			
		}
		
	}
	
}
