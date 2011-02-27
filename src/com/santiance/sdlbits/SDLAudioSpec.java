package com.santiance.sdlbits;

import java.nio.ByteBuffer;

public class SDLAudioSpec {
	protected final ByteBuffer data;
	
	public SDLAudioSpec(ByteBuffer data) {
		this.data = data;
	}
	
	public int getFrequency() { return data.getInt(0); }
	public int getFormat() { return data.getShort(4) & 0xFFFF; }
	public int getChannels() { return data.get(6) & 0xFF; }
	public int getSilence() { return data.get(7) & 0xFF; }
	public int getSamples() { return data.getShort(8) & 0xFFFF; }
	public int getSize() { return data.getInt(10); }
	
//  int freq;
//  Uint16 format;
//  Uint8 channels;
//  Uint8 silence;
//  Uint16 samples;
//  Uint32 size;
//  void (*callback)(void *userdata, Uint8 *stream, int len);
//  void *userdata;
}
