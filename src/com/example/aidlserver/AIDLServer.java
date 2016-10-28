package com.example.aidlserver;

import java.util.Timer;
import java.util.TimerTask;

import com.example.aidlserver.Song.Stub;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AIDLServer extends Service {
	private String[] names= new String[]{"老男孩","春天里","在路上"};
	private String[] authors= new String[]{"筷子兄弟","汪峰","刘欢"};
	private String name ,author;
	private SongBinder songBinder;
	private Timer timer=new Timer();
	public class SongBinder extends Stub{		
	
		public String getAuthor()throws RemoteException{
			return author;
		}
		@Override
		public String getName() throws RemoteException {
			// TODO Auto-generated method stub
			return name;
		
		}
	}
	public IBinder onBinder(Intent intent){
		return songBinder;
		
	}
	public void onCreate(){
		super.onCreate();
		songBinder=new SongBinder();
		timer.schedule(new TimerTask(){
			public void run(){
				int rand=(int)(Math.random()*3);
				name=names[rand];
				author=authors[rand];
			}
		},1000);
	}
     public void onDestroy(){
    	  super.onDestroy();
    	     timer.cancel();
     }
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
   
}

