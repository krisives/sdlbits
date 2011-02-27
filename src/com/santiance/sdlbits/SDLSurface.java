package com.santiance.sdlbits;

import java.nio.ByteBuffer;

/**
 * A wrapped <code>SDL_Surface*</code> structure
 */
public class SDLSurface {
	protected final ByteBuffer data, pixels;
	protected final SDLPixelFormat format;
	
	public SDLSurface(ByteBuffer data, ByteBuffer pixels, SDLPixelFormat format) {
		this.data = data;
		this.pixels = pixels;
		this.format = format;
	}
	
	public int getFlags() { return data.getInt(0); }
	public SDLPixelFormat getFormat() { return format; }
	//SDL_PixelFormat *format;		/* Read-only */
	public int getWidth() { return data.getInt(8); }
	public int getHeight() { return data.getInt(12); }
	//Uint16 pitch;				/* Read-only */
	public int getPitch() { return data.getInt(16); }
	//void *pixels;				/* Read-write */
	public ByteBuffer getPixels() { return pixels; }
	//int offset;				/* Private */
}