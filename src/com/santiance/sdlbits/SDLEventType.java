package com.santiance.sdlbits;

public interface SDLEventType {
	/** Unused (do not remove) */
	public static final int NOEVENT = 0;
	/** Application loses/gains visibility */
	public static final int ACTIVEEVENT = 1;
	/** Keys pressed */
	public static final int KEYDOWN = 2;
	/** Keys released */
	public static final int KEYUP = 3;
	/** Mouse moved */
	public static final int MOUSEMOTION = 4;
	/** Mouse button pressed */
	public static final int MOUSEBUTTONDOWN = 5;
	/** Mouse button released */
	public static final int MOUSEBUTTONUP = 6;
	/** Joystick axis motion */
	public static final int JOYAXISMOTION = 7;
	/** Joystick trackball motion */
	public static final int JOYBALLMOTION = 8;
	/** Joystick hat position change */
	public static final int JOYHATMOTION = 9;
	/** Joystick button pressed */
	public static final int JOYBUTTONDOWN = 10;
	/** Joystick button released */
	public static final int JOYBUTTONUP = 11;
	/** User-requested quit */
	public static final int QUIT = 12;
	/** System specific event */
	public static final int SYSWMEVENT = 13;
	/** Reserved for future use.. */
	public static final int EVENT_RESERVEDA = 14;
	/** Reserved for future use.. */
	public static final int EVENT_RESERVEDB = 15;
	/** User resized video mode */
	public static final int VIDEORESIZE = 16;
	/** Screen needs to be redrawn */
	public static final int VIDEOEXPOSE = 17;
}
