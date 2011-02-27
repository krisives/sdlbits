package com.santiance.sdlbits;

import java.nio.ByteBuffer;

/**
 * An <code>interface</code> used in real time streaming of audio
 * data to the device using SDL.
 * 
 * @author Kristopher Ives <kristopher.ives@gmail.com>
 */
public interface SDLAudioCallback {
	/**
	 * Invoked from SDL (in an arbitrary thread) when more audio data is
	 * needed. See {@link SDL#openAudio(SDLAudioSpec) SDL_OpenAudio}.
	 * @param stream a buffer that needs to be filled (based on it's position and limit)
	 */
	public void fillAudio(ByteBuffer stream);
}
