#include "sdlbits.h"

int mouseX = 0;
int mouseY = 0;
int mouseButton = 0;

JNIEXPORT void JNICALL Java_com_santiance_sdlbits_SDL_getMouseState(JNIEnv *env, jclass c) {
	mouseButton = SDL_GetMouseState(&mouseX, &mouseY);
}

JNIEXPORT jint JNICALL Java_com_santiance_sdlbits_SDL_getMouseX(JNIEnv *env, jclass c) {
	return mouseX;
}

JNIEXPORT jint JNICALL Java_com_santiance_sdlbits_SDL_getMouseY(JNIEnv *env, jclass c) {
	return mouseY;
}

