package com.santiance.sdlbits;

public interface SDLConstants {
	// INIT constants
	public final static int INIT_TIMER = 0x01;
	/** The audio subsystem */
	public final static int INIT_AUDIO = 0x10;
	/** The video subsystem */
	public final static int INIT_VIDEO = 0x20;
	/** The cdrom subsystem */
	public final static int INIT_CDROM = 0x100;
	/** The joystick subsystem */
	public final static int INIT_JOYSTICK = 0x200;
	/** All of the subsystems */
	public final static int INIT_EVERYTHING = 0xFFFF;
	/** Prevents SDL from catching fatal signals */
	public final static int INIT_NOPARACHUTE = 0x00100000;
	/** Runs the event manager in a separate thread */
	public final static int INIT_EVENTTHREAD = 0x01000000;
	
	// Video Constants
	/** Create the video surface in system memory */
	public final static int SWSURFACE = 0x00000000;
	/** Create the video surface in video memory */
	public final static int HWSURFACE = 0x00000001;
	/**
	 * Enables the use of asynchronous updates of the display surface. This will
	 * usually slow down blitting on single CPU machines, but may provide a
	 * speed increase on SMP systems.
	 */
	public final static int ASYNCBLIT = 0x00000004;
	/**
	 * Normally, if a video surface of the requested bits-per-pixel (bpp) is not
	 * available, SDL will emulate one with a shadow surface. Passing
	 * SDL_ANYFORMAT prevents this and causes SDL to use the video surface,
	 * regardless of its pixel depth.
	 */
	public final static int ANYFORMAT = 0x10000000;
	/**
	 * Give SDL exclusive palette access. Without this flag you may not always
	 * get the colors you request with {@link #setColors} or {@link #setPalette}.
	 */
	public final static int HWPALETTE = 0x20000000;
	/**
	 * Enable hardware double buffering; only valid with SDL_HWSURFACE. Calling
	 * SDL_Flip will flip the buffers and update the screen. All drawing will
	 * take place on the surface that is not displayed at the moment. If double
	 * buffering could not be enabled then SDL_Flip will just perform a
	 * SDL_UpdateRect on the entire screen.
	 */
	public final static int DOUBLEBUF = 0x40000000;
	/**
	 * SDL will attempt to use a fullscreen mode. If a hardware resolution
	 * change is not possible (for whatever reason), the next higher resolution
	 * will be used and the display window centered on a black background.
	 */
	public final static int FULLSCREEN = 0x80000000;
	/**
	 * Create an OpenGL rendering context. You should have previously set OpenGL
	 * video attributes with SDL_GL_SetAttribute.
	 */
	public final static int OPENGL = 0x00000002;
	/**
	 * Create an OpenGL rendering context, like above, but allow normal blitting
	 * operations. The screen (2D) surface may have an alpha channel, and
	 * SDL_UpdateRects must be used for updating changes to the screen surface.
	 * NOTE: This option is kept for compatibility only, and will be removed in
	 * next versions. Is not recommended for new code.
	 */
	public final static int OPENGLBLIT = 0x0000000A;
	/**
	 * Create a resizable window. When the window is resized by the user a
	 * SDL_VIDEORESIZE event is generated and SDL_SetVideoMode can be called
	 * again with the new size.
	 */
	public final static int RESIZABLE = 0x00000010;
	/**
	 * If possible, SDL_NOFRAME causes SDL to create a window with no title bar
	 * or frame decoration. Fullscreen modes automatically have this flag set.
	 */
	public final static int NOFRAME = 0x00000020;
	public final static int HWACCEL = 0x00000100;
	public final static int SRCCOLORKEY = 0x00001000;
	public final static int RLEACCELOK = 0x00002000;
	public final static int RLEACCEL = 0x00004000;
	public final static int SRCALPHA = 0x00010000;
	public final static int PREALLOC = 0x01000000;
	
	public final static int 
		LSHIFT=(0x0001),
		RSHIFT=(0x0002),
		LCTRL=(0x0040),
		RCTRL=(0x0080),
		LALT=(0x0100),
		RALT=(0x0200),
		LMETA=(0x0400),
		RMETA=(0x0800),
		NUM=(0x1000),
		CAPS=(0x2000),
		MODE=(0x4000),
		RESERVED=(0x8000);
	
	public final static int AUDIO_STOPPED = 0;
	public final static int AUDIO_PLAYING = 1;
	public final static int AUDIO_PAUSED = 2;
}
