package com.santiance.sdlbits;

/**
 * A Java interface for the SDL callback that filters
 * which events are delivered.
 */
public interface SDLEventFilter {
	public boolean filterEvent(SDLEvent e);
}
