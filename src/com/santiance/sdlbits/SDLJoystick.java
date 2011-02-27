package com.santiance.sdlbits;

/**
 * An <code>Object</code> that wraps the <code>SDL_Joystick*</code> structure
 * in <code>C</code>. Similar to the native structure, it's simply a type-safe
 * handle for use with the SDL joystick methods.
 * 
 * @author Kristopher Ives <kristopher.ives@gmail.com>
 */
public class SDLJoystick {
	/** Value of the <code>SDL_Joystick*</code> pointer */
	protected final long addr;
	/** Index for SDL joystick methods */
	protected final int index;
	
	/**
	 * Wraps an <code>SDL_Joystick*</code> with <code>index</code>.
	 * @param addr the pointer
	 * @param index the SDL index
	 */
	public SDLJoystick(long addr, int index) {
		this.addr = addr;
		this.index = index;
	}
	
	/**
	 * Gets the index for this joystick for use with SDL methods.
	 * @return the SDL index
	 */
	public int getIndex() { return index; }
}//