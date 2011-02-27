package com.santiance.sdlbits;

/**
 * An {@link Exception} for SDL specfic errors. This helps avoid constantly
 * checking <code>return</code> values of SDL methods, which happens often in
 * <code>C</code>.
 * <p>
 * The messages are from {@link SDL#getError getError}.
 * @author Kristopher Ives <kristopher.ives@gmail.com>
 */
public class SDLException extends Exception {
	public SDLException(String str) {
		super(str);
	}
}
