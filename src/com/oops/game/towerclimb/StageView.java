package com.oops.game.towerclimb;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class StageView extends SurfaceView implements SurfaceHolder.Callback {
	
	private StageThread stageThread;
	
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
//					mCanvas.drawColor(Color.TRANSPARENT, Mode.CLEAR);
					mCanvas.drawColor(Color.BLACK);
					
					// todo ..
					
					if (mCanvas != null) {
						sHolder.unlockCanvasAndPost(mCanvas);
					}
					
				}
			}
			
		}
		
	}
	
}
