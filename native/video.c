#include "sdlbits.h"

#define WrapSurface(surf)		Wrap(env, "com/santiance/sdlbits/SDLSurface", (surf), sizeof(SDL_Surface))
#define UnwrapSurface(surf)		(SDL_Surface*)Unwrap(env, "com/santiance/sdlbits/SDLSurface", (surf))

#define UnwrapRect(rect)		(SDL_Rect*)Unwrap(env, "com/santiance/sdlbits/SDLRect", (rect))
#define WrapRect(rect)			Wrap(env, "com/santiance/sdlbits/SDLRect", (rect), sizeof(SDL_Rect))

JNIEXPORT jobject JNICALL Java_com_santiance_sdlbits_SDL_getVideoSurface(JNIEnv *env, jclass c) {
	return WrapSurface(SDL_GetVideoSurface());
}

JNIEXPORT jobject JNICALL Java_com_santiance_sdlbits_SDL_getVideoInfo(JNIEnv *env, jclass c) {
	SDL_VideoInfo *info = (SDL_VideoInfo*)SDL_GetVideoInfo();
	
	return Wrap(env, "VideoInfo", info, sizeof(SDL_VideoInfo));
}

JNIEXPORT jstring JNICALL Java_com_santiance_sdlbits_SDL_getVideoDriverName(JNIEnv *env, jclass c) {
	
}

JNIEXPORT jobjectArray JNICALL Java_com_santiance_sdlbits_SDL_listModes(JNIEnv *env, jclass c, jobject jpixelformat, jint flags) {
	//SDL_ListModes(format, flags);
}

JNIEXPORT jint JNICALL Java_com_santiance_sdlbits_SDL_videoModeOK(JNIEnv *env, jclass c, jint w, jint h, jint bpp, jint flags) {
	return SDL_VideoModeOK(w, h, bpp, flags);
}

JNIEXPORT jobject JNICALL Java_com_santiance_sdlbits_SDL_setVideoMode(JNIEnv *env, jclass c, jint w, jint h, jint bpp, jint flags) {
	SDL_Surface *surf = SDL_SetVideoMode(w, h, bpp, flags);
	
	return WrapSurface(surf);
}

JNIEXPORT void JNICALL Java_com_santiance_sdlbits_SDL_updateRect(JNIEnv *env, jclass c, jobject jsurf, jint x, jint y, jint w, jint h) {
	
}

JNIEXPORT void JNICALL Java_com_santiance_sdlbits_SDL_updateRects(JNIEnv *env, jclass c, jobjectArray rects) {

}

JNIEXPORT jint JNICALL Java_com_santiance_sdlbits_SDL_flip(JNIEnv *env, jclass c, jobject jsurf) {
	SDL_Surface *surf = UnwrapSurface(jsurf);
	
	return SDL_Flip(surf);
}

JNIEXPORT jint JNICALL Java_com_santiance_sdlbits_SDL_setColors(JNIEnv *env, jclass c, jobject jsurf, jintArray colors) {
	
}

JNIEXPORT jint JNICALL Java_com_santiance_sdlbits_SDL_setPalette(JNIEnv *env, jclass c, jobject jsurf, jint flags, jintArray colors) {
	
}

JNIEXPORT jint JNICALL Java_com_santiance_sdlbits_SDL_setGamma(JNIEnv *env, jclass c, jfloat r, jfloat g, jfloat b) {

}

JNIEXPORT jint JNICALL Java_com_santiance_sdlbits_SDL_mapRGB(JNIEnv *env, jclass c, jobject jsurf, jint r, jint g, jint b) {

}

JNIEXPORT jint JNICALL Java_com_santiance_sdlbits_SDL_mapRGBA(JNIEnv *env, jclass c, jobject jsurf, jint r, jint g, jint b, jint a) {

}

JNIEXPORT jobject JNICALL Java_com_santiance_sdlbits_SDL_createRGBSurface(JNIEnv *env, jclass c,
	jint flags, jint w, jint h, jint bpp, jint rmask, jint gmask, jint bmask, jint amask
) {
	SDL_Surface *surf = SDL_CreateRGBSurface(flags, w, h, bpp, rmask, gmask, bmask, amask);
	
	if (!surf) {
		Throw(env, "com/santiance/sdlbits/SDL$Exception", SDL_GetError());
	}
	
	return WrapSurface(surf);
}

JNIEXPORT jobject JNICALL Java_com_santiance_sdlbits_SDL_createRGBSurfaceFrom(JNIEnv *env, jclass c,
	jobject jsurf, jint w, jint h, jint bpp, jint pitch, jint rmask, jint gmask, jint bmask, jint amask
) {
	SDL_Surface *surf = UnwrapSurface(jsurf);
	SDL_Surface *created = SDL_CreateRGBSurfaceFrom(surf, w, h, bpp, pitch, rmask, gmask, bmask, amask);
	
	if (!created) {
		Throw(env, "com/santiance/sdlbits/SDL$Exception", SDL_GetError());
	}
	
	return WrapSurface(created);
}

JNIEXPORT void JNICALL Java_com_santiance_sdlbits_SDL_freeSurface(JNIEnv *env, jclass c, jobject jsurf) {
	SDL_Surface *surf = UnwrapSurface(jsurf);
	
	if (surf != NULL) {
		SDL_FreeSurface(surf);
	}
}

JNIEXPORT void JNICALL Java_com_santiance_sdlbits_SDL_lockSurface(JNIEnv *env, jclass c, jobject jsurf) {
	// TODO check for exception
	SDL_LockSurface(UnwrapSurface(jsurf));
}

JNIEXPORT void JNICALL Java_com_santiance_sdlbits_SDL_unlockSurface(JNIEnv *env, jclass c, jobject jsurf) {
	SDL_UnlockSurface(UnwrapSurface(jsurf));
}

JNIEXPORT jobject JNICALL Java_com_santiance_sdlbits_SDL_convertSurface(JNIEnv *env, jclass c, jobject jsurf, jobject jformat, jint flags) {
	SDL_Surface *surf = UnwrapSurface(jsurf);
	SDL_PixelFormat *format = UnwrapPixelFormat(jformat);
	
	SDL_Surface *converted = SDL_ConvertSurface(surf, format, flags);
	
	return WrapSurface(converted);
}

JNIEXPORT jobject JNICALL Java_com_santiance_sdlbits_SDL_displayFormat(JNIEnv *env, jclass c, jobject jsurf) {
	SDL_Surface *surf = UnwrapSurface(jsurf);
	SDL_Surface *converted = SDL_DisplayFormat(surf);
	
	return WrapSurface(converted);
}

JNIEXPORT jobject JNICALL Java_com_santiance_sdlbits_SDL_displayFormatAlpha(JNIEnv *env, jclass c, jobject jsurf) {
	SDL_Surface *surf = UnwrapSurface(jsurf);
	SDL_Surface *converted = SDL_DisplayFormatAlpha(surf);
	
	return WrapSurface(converted);
}


JNIEXPORT jobject JNICALL Java_com_santiance_sdlbits_SDL_loadBMP(JNIEnv *env, jclass c, jstring jname) {
  
}

JNIEXPORT void JNICALL Java_com_santiance_sdlbits_SDL_saveBMP(JNIEnv *env, jclass c, jobject jsurf, jstring jname) {
	
}

JNIEXPORT jint JNICALL Java_com_santiance_sdlbits_SDL_setColorKey(JNIEnv *env, jclass c, jobject jsurf, jint flags, jint key) {
	SDL_Surface *surf = UnwrapSurface(jsurf);
	
	return SDL_SetColorKey(surf, flags, key);
}

JNIEXPORT jint JNICALL Java_com_santiance_sdlbits_SDL_setAlpha(JNIEnv *env, jclass c, jobject jsurf, jint alpha) {
	SDL_Surface *surf = UnwrapSurface(jsurf);
	
	return SDL_SetAlpha(surf, 0, alpha);
}

JNIEXPORT void JNICALL Java_com_santiance_sdlbits_SDL_setClipRect(JNIEnv *env, jclass c, jobject jsurf, jobject jrect) {
	SDL_Surface *surf = UnwrapSurface(jsurf);
	SDL_Rect *rect = UnwrapRect(jrect);
	
	SDL_SetClipRect(surf, rect);
}

JNIEXPORT jint JNICALL Java_com_santiance_sdlbits_SDL_blitSurface(JNIEnv *env, jclass c,
	jobject jsrc, jobject jsrcRect, jobject jdst, jobject jdstRect
) {
	SDL_Surface *src = UnwrapSurface(jsrc);
	SDL_Rect *srcRect = UnwrapRect(jsrcRect);
	SDL_Surface *dst = UnwrapSurface(jdst);
	SDL_Rect *dstRect = UnwrapRect(jdstRect);
	
	return SDL_BlitSurface(src, srcRect, dst, dstRect);
}

JNIEXPORT void JNICALL Java_com_santiance_sdlbits_SDL_fillRect__Lcom_santiance_sdlbits_SDLSurface_2Lcom_santiance_sdlbits_SDLRect_2I(JNIEnv *env, jclass c, jobject jsurf, jobject jrect, jint color) {
	SDL_Surface *surf = UnwrapSurface(jsurf);
	SDL_Rect *rect = UnwrapRect(jrect);
	SDL_FillRect(surf, rect, color);
}



JNIEXPORT void JNICALL Java_com_santiance_sdlbits_SDL_fillRect__Lcom_santiance_sdlbits_SDLSurface_2IIIII(JNIEnv *env, jclass c, jobject jsurf, jint x, jint y, jint w, jint h, jint color) {
	SDL_Surface *surf = UnwrapSurface(jsurf);
	static SDL_Rect rect;
	
	rect.x = x;
	rect.y = y;
	rect.w = w & 0xFFFF;
	rect.h = h & 0xFFFF;
	
	SDL_FillRect(surf, &rect, color);
}

JNIEXPORT jint JNICALL Java_com_santiance_sdlbits_SDL_gl_1getAttribute(JNIEnv *env, jclass c, jint attr) {
	int value = 0;
	
	if (SDL_GL_GetAttribute((SDL_GLattr)attr, &value)) {
		Throw(env, "com/santiance/sdlbits/SDL$Exception", SDL_GetError());
	}
	
	return value;
}

JNIEXPORT void JNICALL Java_com_santiance_sdlbits_SDL_gl_1setAttribute(JNIEnv *env, jclass c, jint attr, jint value) {
	if (SDL_GL_SetAttribute((SDL_GLattr)attr, value)) {
		Throw(env, "com/santiance/sdlbits/SDL$Exception", SDL_GetError());
	}
	
}

JNIEXPORT void JNICALL Java_com_santiance_sdlbits_SDL_gl_1swapBuffers(JNIEnv *env, jclass c) {
	SDL_GL_SwapBuffers();
}



