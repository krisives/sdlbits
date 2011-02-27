package com.santiance.sdlbits;

import java.nio.ByteBuffer;

/**
 * Details about the format of the pixels for an SDL surface.
 */
public class SDLPixelFormat {
	protected final SDLPalette palette = null;
	protected final ByteBuffer data;
	
	/**
	 * Wrap an existing <code>SDL_VideoInfo</code> structure.
	 * @param data
	 */
	public SDLPixelFormat(ByteBuffer data) {
		this.data = data;
	}
	
	/**
	 * The number of bits used to represent each pixel in a surface. Usually
	 * 8, 16, 24 or 32. (1 to 7 are not allowed when creating a surface or
	 * open a video mode)
	 */
	public int getBitsPerPixel() { return data.get(0) & 0xFF; }
	
	/** The number of bytes used to represent each pixel in a surface. Usually one to four. */
	public int getBytesPerPixel() { return data.get(1) & 0xFF; }
	
	public int getLoss() { return data.getInt(2); }
	public int getShift() { return data.getInt(6); }
	public int getRedMask() { return data.getInt(10); }
	public int getGreenMask() { return data.getInt(14); }
	public int getBlueMask() { return data.getInt(18); }
	/** Pixel value of transparent pixels */
	public int getColorKey() { return data.getInt(22); }
	/** Overall surface alpha value */
	public int getAlpha() { return data.get(26) & 0xFF; }
}