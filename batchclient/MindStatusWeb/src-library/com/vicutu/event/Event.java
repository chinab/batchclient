package com.vicutu.event;

import org.springframework.context.ApplicationEvent;

public class Event extends ApplicationEvent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5592648695118570494L;

	private transient Object data;

	private transient String type;

	public Event(Object source) {
		super(source);
	}
	
	public Event(Object source,String type,Object data){
		this(source);
		this.data=data;
		this.type=type;
	}
	
	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

}
