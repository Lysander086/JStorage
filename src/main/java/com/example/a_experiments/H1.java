package com.example.a_experiments;

public class H1 extends HummerModel {
 
	/***
	 * 要按下喇叭
	 */
	private boolean alarmFlag = true;
 
	@Override
	protected void start() {
		System.out.println("悍马H1发动");
	}
 
	@Override
	protected void stop() {
		System.out.println("悍马H1停车");
	}
 
	@Override
	protected void alarm() {
		System.out.println("悍马H1鸣笛");
 
	}
 
	@Override
	protected void engineBoom() {
		System.out.println("悍马H1引擎声音是这样在。。。");
 
	}
 
	protected boolean isAlarm() {
		return this.alarmFlag;
 
	}
 
	public void setAlarm(boolean isAlarm) {
		this.alarmFlag = isAlarm;
	}
 
}