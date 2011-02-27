package com.santiance.sdlbits;

import java.nio.ByteBuffer;

//TODO Dynamic linking (Load / Call library stuff)

/**
 * Bindings for the Simple Direct-media Library.
 * <h2>General</h2>
 * These are methods that you'll probably use all the time:
 * <ul>
 * <li>{@link #init}</li>
 * <li>{@link #intSubSystem}</li>
 * <li>{@link #delay}</li>
 * <li>{@link #getTicks}</li>
 * <li>{@link #quit}</li>
 * </ul>
 * 
 * <h2>Video</h2>
 * Hardware accelerated 2D graphics are provided with the following
 * methods:
 * <ul>
 * <li>{@link #setVideoMode}</li>
 * <li>{@link #fillRect}</li>
 * <li>{@link #blitSurface}</li>
 * </ul>
 * SDL can aslo prepare OpenGL&trade; for use with any <code>GL</code> bindings for
 * Java&trade; by using:
 * <ul>
 * <li>{@link #gl_setAttribute(int, int)}</li>
 * <li>{@link #setVideoMode} with {@link SDLConstants#OPENGL SDL_OPENGL}</li>
 * <li>{@link #gl_swapBuffers}</li>
 * </ul>
 * 
 * <h2>Joystick</h2>
 * Joystick devices can be queried and managed with:
 * <ul>
 * <li>{@link #numJoysticks}</li>
 * <li>{@link #joystickOpen}</li>
 * <li>{@link #joystickNumButtons}</li>
 * <li>{@link #joystickNumAxes}</li>
 * <li>{@link #joystickGetButton}</li>
 * <li>{@link #joystickGetAxis}</li>
 * <li>{@link #joystickClose}</li>
 * <li>{@link #joystickOpened}</li>
 * <li>{@link #joystickUpdate}</li>
 * </ul>
 * 
 * <h2>Audio</h2>
 * Simple streaming audio output is available using an {@link SDLAudioCallback} and
 * these methods:
 * <ul>
 * <li>{@link #openAudio}</li>
 * <li>{@link #pauseAudio}</li>
 * <li>{@link #lockAudio}</li>
 * <li>{@link #unlockAudio}</li>
 * <li>{@link #mixAudio}</li>
 * <li>{@link #closeAudio}</li>
 * </ul>
 * 
 * @author Kristopher Ives <kristopher.ives@gmail.com>
 */
public class SDL implements SDLConstants {
	static {
		// Load the native library
		System.loadLibrary("sdlbits");

		// Let the native library hook together with classes and stuff
		if (!initSDLbits()) {
			throw new RuntimeException("Unable to initialize SDL wrapper");
		}
	}

	/**
	 * An internal method used for setting up the JNI interface and possibly
	 * setting some <code>static final</code> members that are architecture
	 * dependent.
	 * 
	 * @return <code>true</code> if everything is ok, <code>false</code> if
	 * fatal error
	 */
	private static native boolean initSDLbits();

	/**
	 * Initializes the Simple DirectMedia Library and the subsystems specified
	 * by <code>flags</code>. It should be called before all other SDL
	 * functions. This method does not return an <code>int</code> because
	 * instead an {@link Exception SDL.Exception} will be thrown.
	 * <p>
	 * Note : Unless the {@link #INIT_NOPARACHUTE} flag is set, it will install
	 * cleanup signal handlers for some commonly ignored fatal signals (like
	 * SIGSEGV).
	 * 
	 * @param flags SDL subsystem(s) to initialize. The flags can be
	 * bitwise-ORed together, e.g. (<code>{@link #INIT_AUDIO} |
	 * {@link #INIT_VIDEO}</code>). You should specify the subsystems which you
	 * will be using in your application.
	 * 
	 * @throws SDL.Exception instead of returning an int like
	 * <code>SDL_init</code> did in C
	 */
	public static native void init(int flags);

	/**
	 * After SDL has been initialized with {@link #init(int) SDL_Init} you may
	 * initialize uninitialized subsystems with <code>initSubSystem</code>. The
	 * flags parameter is the same as that used in {@link #init(int) SDL_Init}.
	 * 
	 * @param flags SDL subsystem(s) to ensure are initialized (see
	 * {@link #init(int) SDL_Init})
	 * @see #init(int)
	 */
	public static native void initSubSystem(int flags);

	/**
	 * Shuts down a subsystem that has been previously initialized by
	 * {@link #init(int) SDL_Init} or {@link #initSubSystem(int)
	 * SDL_InitSubSystem}. The <code>flags</code> tells
	 * <code>quitSubSystem</code> which subsystems to shut down, it uses the
	 * same values that are passed to {@link #init(int) SDL_Init}.
	 * 
	 * @param flags SD subsystem(s) to shutdown (see {@link #init(int) SDL_Init}
	 * )
	 */
	public static native void quitSubSystem(int flags);

	/**
	 * Shuts down all SDL subsystems, unloads the dynamically linked library and
	 * frees the allocated resources.
	 * <p>
	 * <small><strong>Note:</strong> While this should be called when you are
	 * done using SDL, it will be called automatically before the SDL class is
	 * unloaded.</small>
	 */
	public static native void quit();

	/**
	 * Checks if a subsystem (or set of subsystems) has already been initialized
	 * by either {@link #init(int) SDL_Init} or {@linkte #initSubSystem(int)
	 * SDL_InitSubSystem}.
	 * 
	 * @param flags what subsystems to check (see {@link #init(int) SDL_Init})
	 * @return a bitwised OR'd combination of the initialized subsystems.
	 */
	public static native int wasInit(int flags);

	/**
	 * Gets information about the last internal SDL error that may have occured.
	 * 
	 * @return the error
	 */
	public static native String getError();

	/**
	 * Deletes all information about the last internal SDL error. Useful if the
	 * error has been handled by the program.
	 */
	public static native void clearError();

	// General
	/**
	 * Gets the current display surface. If SDL is doing format conversion on
	 * the display surface, this function returns the publicly visible surface,
	 * not the real video surface.
	 * 
	 * @return the video surface or <code>null</code> on error
	 */
	public static native SDLSurface getVideoSurface();

	/**
	 * Gets information about the video hardware. If invoked before
	 * {@link #setVideoMode(int, int, int, int) SDL_SetVideoMode}, the vfmt
	 * member of the returned structure will contain the pixel format of the
	 * best video mode.
	 * 
	 * @return an object that describes the video environment
	 */
	public static native SDLVideoInfo getVideoInfo();

	/**
	 * Gets the name of the current video driver.
	 * 
	 * @return the name of the driver or <code>null</code> if {@link #init
	 * SDL_Init} wasnt called
	 */
	public static native String getVideoDriverName();

	/**
	 * Gets available screen dimensions for a given pixel format and video mode.
	 * 
	 * @param format the pixel format
	 * @param flags the video flags (see {@link #setVideoMode SDL_SetVideoMode})
	 * @return an array of all the possible modes or <code>null</code> if any
	 * may be used
	 * @throws SDL.Exception if no modes with that pixel format exist
	 */
	public static native SDLRect[] listModes(SDLPixelFormat format, int flags);

	/**
	 * Checks whether the requested mode is supported by the current video
	 * device. The arguments passed to this function are the same as those you
	 * would pass to {@link #setVideoMode SDL_SetVideoMode}.
	 * 
	 * @param width video width
	 * @param height video height
	 * @param bpp video bits per pixel
	 * @param flags video flags
	 * @return <code>0</code> if the requested mode is not supported under any
	 * bit depth.
	 * <p>
	 * Otherwise, the bits per pixel of the closest available mode with the
	 * given parameters. The bits per pixel value returned is only a suggested
	 * mode. You can usually request any bpp you want when setting the video
	 * mode and SDL will emulate that color depth with a shadow video surface.
	 */
	public static native int videoModeOK(int width, int height, int bpp,
			int flags);

	/**
	 * Sets the video mode.
	 * <p>
	 * Note 1: Use SDL_SWSURFACE if you plan on doing per-pixel manipulations,
	 * or blit surfaces with alpha channels, and require a high framerate. When
	 * you use hardware surfaces (by passing the flag SDL_HWSURFACE as
	 * parameter), SDL copies the surfaces from video memory to system memory
	 * when you lock them, and back when you unlock them. This can cause a major
	 * performance hit. Be aware that you may request a hardware surface, but
	 * receive a software surface because the video driver doesn't support
	 * hardware surface. Many platforms can only provide a hardware surface when
	 * using SDL_FULLSCREEN. The SDL_HWSURFACE flag is best used when the
	 * surfaces you'll be blitting can also be stored in video memory.
	 * <p>
	 * Note 2: If you want to control the position on the screen when creating a
	 * windowed surface, you may do so by setting the environment variables
	 * SDL_VIDEO_CENTERED=center or SDL_VIDEO_WINDOW_POS=x,y. You can also set
	 * them via SDL_putenv.
	 * <p>
	 * Note 3: This function should be called in the main thread of your
	 * application.
	 * <p>
	 * User note 1: Some have found that enabling OpenGL attributes like
	 * SDL_GL_STENCIL_SIZE (the stencil buffer size) before the video mode has
	 * been set causes the application to simply ignore those attributes, while
	 * enabling attributes after the video mode has been set works fine.
	 * <p>
	 * User note 2: Also note that, in Windows, setting the video mode resets
	 * the current OpenGL context. You must execute again the OpenGL
	 * initialization code (set the clear color or the shade model, or reload
	 * textures, for example) after calling SDL_SetVideoMode. In Linux, however,
	 * it works fine, and the initialization code only needs to be executed
	 * after the first call to SDL_SetVideoMode (although there is no harm in
	 * executing the initialization code after each call to SDL_SetVideoMode,
	 * for example for a multiplatform application).
	 * 
	 * @param width the desired width in pixels of the video mode to set
	 * @param height the desired height in pixels of the video mode to set. As
	 * of SDL 1.2.10, if width and height are both 0, SDL_SetVideoMode will use
	 * the width and height of the current video mode (or the desktop mode, if
	 * no mode has been set).
	 * @param bpp the desired bits per pixel of the video mode to set. If
	 * bitsperpixel is 0, it is treated as the current display bits per pixel. A
	 * bitsperpixel of 24 uses the packed representation of 3 bytes per pixel.
	 * For the more common 4 bytes per pixel mode, please use a bitsperpixel of
	 * 32. Somewhat oddly, both 15 and 16 bits per pixel modes will request a 2
	 * bytes per pixel mode, but with different pixel formats.
	 * @param flags The possible values for the flags parameter are the same
	 * used by the SDL_Surface structure. OR'd combinations of the following
	 * values are valid.
	 * @return A surface that is freed by {@link #quit SDL_Quit} and must not be
	 * freed by the caller. This rule also includes consecutive calls to
	 * <code>setVideoMode</code> (i.e. resize or resolution change) because the
	 * existing surface will be released automatically. Whatever flags
	 * <code>setVideoMode</code> could satisfy are set in the flags member of
	 * the returned surface.
	 */
	public static native SDLSurface setVideoMode(int width, int height,
			int bpp, int flags);

	public static native void updateRect(SDLSurface surf, int x, int y, int w,
			int h);

	public static native void updateRects(SDLRect[] rects);

	/**
	 * On hardware that supports <em>double buffering</em>, this function sets
	 * up a flip and returns. The hardware will wait for vertical retrace, and
	 * then swap video buffers before the next video surface blit or lock will
	 * return. On hardware that doesn't support double-buffering or if
	 * {@link SDLConstants#SWSURFACE SDL_SWSURFACE} was set, this is equivalent
	 * to calling {@link #updateRect} (screen, 0, 0, 0, 0).
	 * <p>
	 * A software screen surface is also updated automatically when parts of a
	 * SDL window are redrawn, caused by overlapping windows or by restoring
	 * from an iconified state. As a result there is no proper double buffer
	 * behavior in windowed mode for a software screen, in contrast to a full
	 * screen software mode.
	 * <p>
	 * The {@link SDLConstants#DOUBLEBUF SDL_DOUBLEBUF} flag must have been
	 * passed to {@link #setVideoMode}, when setting the video mode for this
	 * function to perform hardware flipping.
	 * <p>
	 * <small>Note : If you want to swap the buffers of an initialized OpenGL
	 * context, use the function {@link #gl_swapBuffers()} instead.</small>
	 * 
	 * @param surf
	 * @return
	 */
	public static native int flip(SDLSurface surf);

	public static native int setColors(SDLSurface surf, int[] colors);

	public static native int setPalette(SDLSurface surf, int flags, int[] colors);

	public static native int setGamma(float red, float green, float blue);

	// public static native int getGammaRamp()
	// public static native int setGammaRamp()SDL_GLattr attr, int *value
	public static native int mapRGB(SDLPixelFormat format, int r, int g, int b);

	public static native int mapRGBA(SDLPixelFormat format, int r, int g,
			int b, int a);

	// Surface stuff
	/**
	 * Allocate an empty surface (must be called after {@link #setVideoMode}).
	 * <p>
	 * If <code>bitsPerPixel</code> is 8 an empty palette is allocated for the
	 * surface, otherwise a 'packed-pixel' {@link SDLPixelFormat} is created
	 * using the <code>[<em>RGBA</em>]mask</code>'s provided (see
	 * {@link SDLPixelFormat}). The flags specifies the type of surface that
	 * should be created, it is an OR'd combination of the following possible
	 * values.
	 * 
	 * @param flags a combination of {@link SDLConstants#SWSURFACE},
	 * {@link SDLConstants#HWSURFACE}, {@link SDLConstants#SRCCOLORKEY},
	 * {@link SDLConstants#SRCALPHA}
	 * @param w surface width
	 * @param h surface height
	 * @param bpp bits per pixel
	 * @param rmask red mask
	 * @param gmask green mask
	 * @param bmask blue mask
	 */
	public static native SDLSurface createRGBSurface(int flags, int w, int h,
			int bpp, int rmask, int gmask, int bmask, int amask);

	/**
	 * Creates an {@link SDLSurface} from the provided pixel data.
	 * <p>
	 * No copy is made from the pixel data. A special undocumented surface flag
	 * is set. The pixel data won't be deallocated automatically when
	 * {@link #freeSurface} is invoked with the surface and it should not be
	 * freed until the surface has been freed. The data stored in pixels is
	 * assumed to have depth bits per pixel. Pitch is the size of the scanline
	 * of the surface, in bytes, i.e. <code>widthInPixels*bytesPerPixel</code>.
	 * The scanline is the width of the image multiplied by bytes per pixel,
	 * plus any bytes added for alignment (that is if pixels points to the
	 * leftmost pixel on the first row of the surface, then pixels+pitch points
	 * to the leftmost pixel on the second row of the surface).
	 * <p>
	 * The pixel data is considered to be in software memory. If the pixel data
	 * lies in hardware memory (as pixel data from a hardware surface), the
	 * appropriate surface flag has to be set manually.
	 * <p>
	 * See {@link #createRGBSurface} for a more detailed description of the
	 * other parameters.
	 * 
	 * @param pixels the pixel data
	 * @param w width of pixels
	 * @param h height of pixels
	 * @param bpp bits per pixel
	 * @param pitch
	 * @param rmask
	 * @param gmask
	 * @param bmask
	 * @param amask
	 * @return
	 */
	public static native SDLSurface createRGBSurfaceFrom(ByteBuffer pixels,
			int w, int h, int bpp, int pitch, int rmask, int gmask, int bmask,
			int amask);

	/**
	 * Frees the resources used by a previously created {@link SDLSurface}. If
	 * the surface was created using {@link #createRGBSurfaceFrom} then the
	 * pixel data is not freed.
	 * 
	 * @param surf the surface to free
	 */
	public static native void freeSurface(SDLSurface surf);

	/**
	 * Sets up a surface for directly accessing the pixels. Between calls to
	 * <code>lockSurface</code> and {@link #unlockSurface}, you can write to and
	 * read from <code>surface->pixels</code>, using the pixel format stored in
	 * <code>surface->format</code>. Once you are done accessing the surface,
	 * you should use <code>unlockSurface</code> to release the lock.
	 * <p>
	 * Not all surfaces require locking. If <code>SDL_MUSTLOCK(surface)</code>
	 * evaluates to <code>false</code>, then reading and writing pixels to the
	 * surface can be performed at any time, and the pixel format of the surface
	 * will not change.
	 * <p>
	 * No operating system or library calls should be made between the
	 * lock/unlock pairs, as critical system locks may be held during this time.
	 * <p>
	 * <small><strong>Note</strong>: Since SDL 1.1.8, the surface locks are
	 * recursive. This means that you can lock a surface multiple times, but
	 * each lock must have a matching unlock.</small>
	 * 
	 * @param surf the surface to lock
	 */
	public static native void lockSurface(SDLSurface surf);

	/**
	 * Surfaces that were previously locked using {@link #lockSurface} must be
	 * unlocked with <code>unlockSurface</code>. Surfaces should be unlocked as
	 * soon as possible.
	 * <p>
	 * <small><strong>Note:</strong> Since 1.1.8, the surface locks are
	 * recursive. See {@link #lockSurface} for more information.</small>
	 * 
	 * @param surf the surface to unlock
	 */
	public static native void unlockSurface(SDLSurface surf);

	public static native SDLSurface convertSurface(SDLSurface surf,
			SDLPixelFormat format, int flags);

	public static native SDLSurface displayFormat(SDLSurface surf);

	public static native SDLSurface displayFormatAlpha(SDLSurface surf);

	public static native SDLSurface loadBMP(String file);

	public static native void saveBMP(SDLSurface surf, String file);

	public static native int setColorKey(SDLSurface surf, int flags, int key);

	public static native int setAlpha(SDLSurface surf, int alpha);

	public static native void setClipRect(SDLSurface surf, SDLRect rect);

	public static native void getClipRect(SDLSurface surf, SDLRect rect);

	public static native int blitSurface(SDLSurface src, SDLRect srcRect,
			SDLSurface dst, SDLRect dstRect);

	/**
	 * Performs a fast fill of the given rectangle with color. If
	 * <code>rect</code> is <code>null</code>, the whole surface will be filled
	 * with <code>color</code>.
	 * <p>
	 * <code>color</code> should be a pixel of the format used by the surface,
	 * and can be generated by the {@link #mapRGB} or {@link #mapRGBA} methods.
	 * If <code>color</code> contains an alpha value then the destination is
	 * simply "filled" with that alpha information, no blending takes place.
	 * <p>
	 * If there is a clip rectangle set on the destination (set via
	 * {@link #setClipRect}), then this function will clip based on the
	 * intersection of the clip rectangle and the <code>rect</code> rectangle,
	 * and the dstrect rectangle will be modified to represent the area actually
	 * filled.
	 * <p>
	 * If you call this on the video surface (ie: the value of
	 * {@link #getVideoSurface}) you may have to update the video surface to see
	 * the result. This can happen if you are using a shadowed surface that is
	 * not double buffered in Windows XP using build 1.2.9.
	 * 
	 * @param surf the surface to draw on
	 * @param rect area to fill (or <code>null</code> for entire area)
	 * @param color pixel color
	 */
	public static native void fillRect(SDLSurface surf, SDLRect rect, int color);

	/**
	 * Same as {@link #fillRect(SDLSurface, SDLRect, int) fillRect}, except it
	 * takes multiple <code>int</code> arguments instead of an {@link SDLRect}.
	 * 
	 * @param surf
	 * @param x the x coordinate (signed short)
	 * @param y the y coordinate (signed short)
	 * @param w the width (unsigned short)
	 * @param h the height (unsigned short)
	 * @param color the pixel color
	 * @see #fillRect(SDLSurface, SDLRect, int)
	 */
	public static native void fillRect(SDLSurface surf, int x, int y, int w,
			int h, int color);

	// OpenGL stuff
	public static native int gl_getAttribute(int attr);

	public static native void gl_setAttribute(int attr, int value);

	public static native void gl_swapBuffers();

	// Events

	/**
	 * Pumps the event loop, gathering events from the input devices.
	 * <p>
	 * <code>SDL_PumpEvents</code> gathers all the pending input information
	 * from devices and places it on the event queue. Without calls to
	 * <code>SDL_PumpEvents</code> no events would ever be placed on the queue.
	 * Often the need for calls to <code>SDL_PumpEvents</code> is hidden from
	 * the user since {@link SDL#pollEvent SDL_PollEvent} and
	 * {@link SDL#waitEvent SDL_WaitEvent} implicitly call
	 * <code>SDL_PumpEvents</code>. However, if you are not polling or waiting
	 * for events (e.g. you are filtering them), then you must call
	 * <code>pumpEvents</code> to force an event queue update.
	 * <p>
	 * <strong>Note:</strong> You can only call this function in the thread that
	 * set the video mode.
	 */
	public static native void pumpEvents();

	/**
	 * Polls for currently pending events. Unlike it's native counterpart
	 * <code>pollEvent</code> returns an {@link SDLEvent} instead of an
	 * <code>int</code>, and will return <code>null</code> when no more events
	 * are pending.
	 * <p>
	 * As this function implicitly calls {@link SDL#pumpEvents SDL_PumpEvents},
	 * you can only call this function in the thread that set the video mode.
	 * 
	 * @return the polled event or <code>null</code> if none are pending
	 */
	public static native SDLEvent pollEvent();

	/**
	 * The event queue can actually be used as a two way communication channel.
	 * Not only can events be read from the queue, but the user can also push
	 * their own events onto it. event is a pointer to the event structure you
	 * wish to push onto the queue. The event is copied into the queue, and the
	 * caller may dispose of the memory pointed to after <code>pushEvent</code>
	 * returns.
	 * <p>
	 * <strong>Note:</strong> Pushing device input events onto the queue doesn't
	 * modify the state of the device within SDL. <strong>This function is
	 * thread safe</strong>, and can be called from other threads safely.
	 * 
	 * @param event the event to push onto the SDL event queue
	 */
	public static native void pushEvent(SDLEvent event);

	/**
	 * Sets an {@link SDLEventFilter} to filter SDL messages. In C this will
	 * call <code>SDL_SetEventFilter</code> with the same function which invokes
	 * {@link SDLEventFilter#filterEvent}.
	 * 
	 * @param filter an SDL event filter
	 */
	public static native void setEventFilter(SDLEventFilter filter);

	// Joystick

	/**
	 * Counts the number of joysticks detected by SDL.
	 * 
	 * @return the total count of joysticks
	 */
	public static native int numJoysticks();

	/**
	 * Gets the name of a joystick by it's SDL index.
	 * 
	 * @param index the SDL index number of the joystick
	 * @return the name of the joystick or <code>null</code> on error
	 */
	public static native String joystickName(int index);

	/**
	 * Opens a joystick for use with SDL.
	 * 
	 * @param index the index number for the joystick
	 * @return the opened SDL joystick or <code>null</code> on error
	 */
	public static native SDLJoystick joystickOpen(int index);

	/**
	 * Updates the state (position, buttons, etc.) of all open joysticks. If
	 * joystick events have been enabled with {@link #joystickEventState} then
	 * this is called automatically in the event loop.
	 */
	public static native void joystickUpdate();

	/**
	 * Checks if an SDL joystick has already been opened.
	 * 
	 * @param index the SDL joystick index
	 * @return true if it's already open
	 */
	public static native boolean joystickOpened(int index);

	/**
	 * Gets the SDL index number for a Joystick object.
	 * 
	 * @param joy the joystick object
	 * @return the index
	 */
	public static native int joystickIndex(SDLJoystick joy);

	/**
	 * Gets the number of buttons for an already opened SDL joystick.
	 * 
	 * @param joy the open Joystick object
	 * @return the number of buttons for that joystick
	 */
	public static native int joystickNumButtons(SDLJoystick joy);

	/**
	 * Gets the number of axes for a {@link SDLJoystick}.
	 * 
	 * @param joy the joystick opened from {@link #joystickOpen}
	 * @return the number of axes for the joystick
	 */
	public static native int joystickNumAxes(SDLJoystick joy);

	/**
	 * Gets the state of a button for an open SDL joystick.
	 * 
	 * @param joy the open Joystick object
	 * @param button the button to get the state of
	 * @return 1 if the button is pressed or 0 if unpressed
	 */
	public static native int joystickGetButton(SDLJoystick joy, int button);

	/**
	 * Gets the current state of the given axis on the given joystick.
	 * <p>
	 * On most modern joysticks the X axis is usually represented by axis
	 * <code>0</code> and the Y axis by axis <code>1</code>. The value returned
	 * by <code>joystickGetAxis</code> is a signed integer (<code>-32768</code>
	 * to <code>32767</code>) representing the current position of the axis, it
	 * may be necessary to impose certain tolerances on these values to account
	 * for jitter.
	 * <p>
	 * <small><strong>Note:</strong> Some joysticks use axes 2 and 3 for extra
	 * buttons.</small>
	 * 
	 * @param joystick the joystick opened with {@link #joystickOpen}
	 * @param axis the axis index (<code>0</code> to {@link #joystickNumAxes}
	 * exclusive)
	 * @return the state of the joystick axis
	 */
	public static native int joystickGetAxis(SDLJoystick joystick, int axis);

	/**
	 * Closes a previously opened Joystick object.
	 * 
	 * @param joy the open joystick object
	 */
	public static native void joystickClose(SDLJoystick joy);

	// Audio

	public static native SDLAudioSpec openAudio(SDLAudioSpec desired);

	public static native void pauseAudio(boolean isPaused);

	public static native int getAudioStatus();

	public static native void lockAudio();

	public static native void unlockAudio();

	public static native void closeAudio();

	public static native void mixAudio(ByteBuffer dst, ByteBuffer src, int len,
			int volume);

	// Time

	/**
	 * Gets the number of milliseconds since SDL library was initialized. This
	 * value wraps around if the program runs for more than 49.7 days.
	 * 
	 * @return the milliseconds since SDL was initialized
	 */
	public static native int getTicks();

	/**
	 * Waits a specified number of milliseconds before returning with a delay
	 * granularity is at least <code>10 ms</code>. (Some platforms have shorter
	 * clock ticks but this is the most common)
	 * 
	 * @param ms milliseconds to block for
	 */
	public static native void delay(int ms);

	// Mouse
	/**
	 * Toggles if the window manager cursor is visible on the screen.
	 * <p>
	 * <small><strong>Note:</strong> The functionality for
	 * <code>SDL_QUERY</code> was moved to {@link #isCursorVisible()} to make
	 * this easier to use.</small>
	 * 
	 * @param isVisible <code>true</code> if visible, <code>false</code> to hide
	 */
	public static native void showCursor(boolean isVisible);

	/**
	 * Checks if the cursor is visible.
	 * 
	 * @return <code>true</code> if the cursor is visible
	 */
	public static native boolean isCursorVisible();

	/**
	 * Queries the state of the mouse. This must be used before
	 * {@link #getMouseX()}, {@link #getMouseY()}, and {@link #getMouseButton()}.
	 * <p>
	 * <small>
	 * <em><strong>Note:</strong> This is because the native <code>SDL_GetMouseState</code>
	 * uses multiple pointers and this was an efficient and simple solution.</em>
	 * </small>
	 */
	public static native void getMouseState();

	/**
	 * Gets the <code>x</code> coordinate of after having invoked
	 * {@link #getMouseState()}.
	 * 
	 * @return the x coordinate
	 */
	public static native int getMouseX();

	/**
	 * Gets the <code>y</code> coordinate of after having invoked
	 * {@link #getMouseState()}.
	 * 
	 * @return the y coordinate
	 */
	public static native int getMouseY();

	/**
	 * Gets the mouse button mask after having invoked {@link #getMouseState()}.
	 * 
	 * @return the mouse button mask
	 */
	public static native int getMouseButton();
}
