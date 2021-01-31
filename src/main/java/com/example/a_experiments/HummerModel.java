package com.example.a_experiments;

// java设计模式: 模板模式。钩子方法
public abstract class HummerModel {
 
	protected abstract void start();
 
	protected abstract void stop();
 
	/***
	 * 喇叭会出声音，是滴滴叫，还是哔哔叫
	 */
	protected abstract void alarm();
 
	/***
	 * 引擎会轰隆隆的响
	 */
	protected abstract void engineBoom();
 
	final public void run() {
		this.start();
		this.engineBoom();
		if (this.isAlarm()) {
			this.alarm();
		}
		this.stop();
	}
 
	/**
	 * 钩子方法， 默认喇叭会响
	 * 
	 * @return
	 */
	protected boolean isAlarm() {
		return true;
	}
 
}