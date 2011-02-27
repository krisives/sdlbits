package com.santiance.sdlbits;

import java.nio.ByteBuffer;

/**
 * A wrapped <code>SDL_VideoInfo</code> structure.
 * 
 * @author Kristopher Ives <kristopher.ives@gmail.com>
 */
public class SDLVideoInfo {
	/**
	 * Data for the <code>SDL_VideoInfo</code>
	 */
	protected final ByteBuffer data;
	
	public SDLVideoInfo(ByteBuffer data) {
		this.data = data;
	}
	
	/**
	 * Checks if hardware surfaces are available
	 * 
	 * @return true if {@link SDL#HWSURFACE SDL_HWSURFACE} should work
	 */
	public boolean isHardwareAvailable() { return data.getInt(0) != 0; }
	
	/**
	 * Checks if a window manager is being used
	 * @return true if a WM is available
	 */
	public boolean hasWindowManager() { return data.getInt(4) != 0; }
	public boolean hasHardwareBlit() { return data.getInt(8) != 0; }
	public boolean hasHardwareMaskedBlit() { return data.getInt(12) != 0; }
	public boolean hasHardwareAlphaBlit() { return data.getInt(12) != 0; }
	public int getTotalVideoMemory() { return data.getInt(32); }
	/**
	 * Gets the width of the current display.
	 * @return the width
	 */
	public int getWidth() { return data.getInt(36); }
	/**
	 * Gets the height of the current display
	 * @return the height
	 */
	public int getHeight() { return data.getInt(40); }
}
