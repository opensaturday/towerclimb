package com.oops.game.towerclimb.common;

public class Action {

	public static int TYPE_PHYSICS = 0;
	public static int TYPE_PSYCHIC = 1;
	public static int TYPE_ITEM = 2;
	
	public static int ATTRIBUTE_WATER = 0;
	public static int ATTRIBUTE_FIRE = 1;
	public static int ATTRIBUTE_WIND = 2;
	public static int ATTRIBUTE_LAND = 3;
	public static int ATTRIBUTE_HOLY = 4;
	public static int ATTRIBUTE_DARK = 5;
	public static int ATTRIBUTE_CURE = 6;
	
	public int type = TYPE_PHYSICS;
	public int attribute = ATTRIBUTE_WATER;
	public int power = 10;
	
	public Action ( int type, int attribute, int power ) {
		this.type = type;
		this.attribute = attribute;
		this.power = power;
	}
	
}
