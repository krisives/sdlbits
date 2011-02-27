package com.santiance.sdlbits;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Wraps the <code>SDL_Rect</code> structure for storing rectangles with
 * <code>16-bit</code> signed (x,y) coordinates and <code>16-bit</code> unsigned
 * dimensions.
 * <p>
 * <code>SDLRect</code> objects that are allocated (or wrapped) using a direct
 * {@link ByteBuffer}.
 * 
 * @author Kristopher Ives <kristopher.ives@gmail.com>
 */
public class SDLRect {
	/** A direct buffer (allocated or wrapped from C) */
	protected final ByteBuffer data;
	
	/** Creates an SDL_Rect object.
	 *  @param x the x coordinate (signed short)
	 *  @param y the y coordinate (signed short)
	 *  @param width the width (unsigned short)
	 *  @param height the height (unsigned short)
	*/
	public SDLRect(int x, int y, int width, int height) {
		this.data = ByteBuffer.allocateDirect(8);
		data.order(ByteOrder.nativeOrder());
		data.putShort(0, (short)x);
		data.putShort(2, (short)y);
		data.putShort(4, (short)width);
		data.putShort(6, (short)height);
	}
	
	/**
	 * Wrap an already allocated SDL_Rect structure
	 * 
	 * @param data the direct buffer
	 */
	public SDLRect(ByteBuffer data) {
		this.data = data;
	}

	/**
	 * Gets the signed <code>16-bit</code> x-coordinate of this rectangle.
	 * @return the x-coordinate
	 */
	public int getX() { return data.getShort(0); }
	/**
	 * Gets the signed <code>16-bit</code> y-coordinate of this rectangle.
	 * @return the y-coordinate
	 */
	public int getY() { return data.getShort(2); }
	/**
	 * Gets the unsigned <code>16-bit</code> width of this rectangle.
	 * @return the width
	 */
	public int getWidth() { return data.getShort(4) & 0xFFFF; }
	/**
	 * Gets the unsigned <code>16-bit</code> height of this rectangle.
	 * @return the height
	 */
	public int getHeight() { return data.getShort(6) & 0xFFFF; }
	
	/**
	 * Sets the coordinates and dimensions of this rectangle.
	 * @param x the signed 16-bit x-coordinate
	 * @param y the signed 16-bit y-coordinate
	 * @param width the unsigned 16-bit width
	 * @param height the unsigned 16-bit height
	 */
	public void setRect(int x, int y, int width, int height) {
		data.putShort(0, (short)x);
		data.putShort(2, (short)y);
		data.putShort(4, (short)width);
		data.putShort(6, (short)height);
	}
	
	/**
	 * Sets the position of this rectangle.
	 * @param x the signed 16-bit x-coordinate
	 * @param y the signed 16-bit y-coordinate
	 */
	public void setPosition(int x, int y) {
		data.putShort(0, (short)x);
		data.putShort(2, (short)y);
	}
	
	/**
	 * Sets the dimensions of this rectangle.
	 * @param width the unsigned 16-bit width
	 * @param height the unsigned 16-bit height
	 */
	public void setDimensions(int width, int height) {
		data.putShort(4, (short)width);
		data.putShort(6, (short)height);
	}
}