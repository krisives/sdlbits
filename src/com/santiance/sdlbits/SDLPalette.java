package com.santiance.sdlbits;

import java.nio.ByteBuffer;

/** SDL color table */
public class SDLPalette {
	final int count;
	final ByteBuffer colors;

	public SDLPalette(int count, ByteBuffer colors) {
		this.count = count;
		this.colors = colors;
	}

	public int getColorCount() { return count; }	
	public int getColor(int index) { return colors.getInt(index * 4); }
}