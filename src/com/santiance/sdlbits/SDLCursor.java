package com.santiance.sdlbits;

import java.nio.ByteBuffer;

public class SDLCursor {
	protected final ByteBuffer data;
	protected final SDLRect area;
	
	public SDLCursor(ByteBuffer data) {
		this.data = data;
		this.area = new SDLRect(data);
	}
	
	public int getHotX() { return data.getShort(8); }
	public int getHotY() { return data.getShort(10); }
}
