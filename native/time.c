#include "sdlbits.h"


JNIEXPORT jint JNICALL Java_com_santiance_sdlbits_SDL_getTicks(JNIEnv *env, jclass c) {
	return SDL_GetTicks();
}

JNIEXPORT void JNICALL Java_com_santiance_sdlbits_SDL_delay(JNIEnv *env, jclass c, jint ms) {
	SDL_Delay(ms);
}
