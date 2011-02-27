package com.santiance.sdlbits;

import java.nio.ByteBuffer;


/**
 * A base class for SDL events. In <code>C</code> this would be the
 * <code>SDL_Event</code> union. You can check if it's an <code>instanceof</code>
 * one of these subclasses:
 * <ul>
 * <li>{@link SDLEvent.Keyboard}</li>
 * <li>{@link SDLEvent.MouseButton}</li>
 * <li>{@link SDLEvent.MouseMotion}</li>
 * <li>{@link SDLEvent.JoyButton}</li>
 * <li>{@link SDLEvent.JoyAxis}</li>
 * <li>{@link SDLEvent.Quit}</li>
 * </ul>
 * You can also compare {@link #getType()} to an <code>int</code>
 * SDL constant, such as {@link SDLEventType#QUIT}.
 * @author Kristopher Ives <kristopher.ives@gmail.com>
 */
public class SDLEvent {
	/** Wrapped or allocated ByteBuffer */
	protected final ByteBuffer data;
	/*
	public SDLEvent(int type, int extraSize) {
		if (extraSize < 0) {
			throw new IllegalArgumentException("Size is negative");
		}
		
		this.data = ByteBuffer.allocateDirect(1 + extraSize);
		data.order(ByteOrder.nativeOrder());
		setType(type);
	}
	*/
	/**
	 * Wrap a ByteBuffer that contains the <code>SDL_Event</code> union.
	 * @param data structure data to wrap
	 */
	public SDLEvent(ByteBuffer data) {
		this.data = data;
	}
	
	public int getType() { return data.get(0) & 0xFF; }
	public void setType(int type) { data.put(0, (byte)type); }
	
	/** An SDL event delivered when the application is asked to quit */
	public static class Quit extends SDLEvent {
		public Quit(ByteBuffer data) { super(data); }
	}
	
	/** Keyboard */
	public static class Keyboard extends SDLEvent {
		public Keyboard(ByteBuffer data) {
			super(data);
		}
		
		public int getState() { return data.get(1) & 0xFF; }
		// Just brought the keysym in
		public int getScancode() { return data.get(2) & 0xFF; }
		public int getSym() { return data.getInt(3); }
		public int getMod() { return data.getInt(7); }
		public int getUnicode() { return data.getShort(11) & 0xFFFF; }
	}
	
	/** MouseMotion */
	public static class MouseMotion extends SDLEvent {
		public MouseMotion(ByteBuffer data) { super(data); }
		public int getState() { return data.get(1) & 0xFF; }
		public int getX() { return data.getShort(2) & 0xFFFF; }
		public int getY() { return data.getShort(4) & 0xFFFF; }
		public int getRelX() { return data.getShort(6); }
		public int getRelY() { return data.getShort(8); }
	}
	
	/** MouseButton */
	public static class MouseButton extends SDLEvent {
		public MouseButton(ByteBuffer data) { super(data); }
		public int getWhich() { return data.get(1) & 0xFF; }
		public int getButton() { return data.get(2) & 0xFF; }
		public int getState() { return data.get(3) & 0xFF; }
		public int getX() { return data.getShort(4) & 0xFFFF; }
		public int getY() { return data.getShort(6) & 0xFFFF; }
	}
	
	/** JoyButton */
	public static class JoyButton extends SDLEvent {
		public JoyButton(ByteBuffer data) { super(data); }
		public int getWhich() { return data.get(1) & 0xFF; }
		public int getButton() { return data.get(2) & 0xFF; }
		public int getState() { return data.get(3) & 0xFF; }
	}
	
	/** JoyAxis */
	public static class JoyAxis extends SDLEvent {
		public JoyAxis(ByteBuffer data) { super(data); }
		public int getWhich() { return data.get(1) & 0xFF; }
		public int getAxis() { return data.get(2) & 0xFF; }
		public int getValue() { return data.getShort(3); }
	}
	
	public static class System extends SDLEvent {
		public System(ByteBuffer data) { super(data); }
	}
}
