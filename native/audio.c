#include "sdlbits.h"

JNIEXPORT jobject JNICALL Java_com_santiance_sdlbits_SDL_openAudio(JNIEnv *env, jclass c, jobject jdesired) {
	return NULL;
}

JNIEXPORT void JNICALL Java_com_santiance_sdlbits_SDL_pauseAudio(JNIEnv *env, jclass c, jboolean isPaused) {
	SDL_PauseAudio(isPaused);
}

JNIEXPORT jint JNICALL Java_com_santiance_sdlbits_SDL_getAudioStatus(JNIEnv *env, jclass c) {
	return SDL_GetAudioStatus();
}

JNIEXPORT void JNICALL Java_com_santiance_sdlbits_SDL_lockAudio(JNIEnv *env, jclass c) {
	SDL_LockAudio();
}

JNIEXPORT void JNICALL Java_com_santiance_sdlbits_SDL_unlockAudio(JNIEnv *env, jclass c) {
	SDL_UnlockAudio();
}

JNIEXPORT void JNICALL Java_com_santiance_sdlbits_SDL_closeAudio(JNIEnv *env, jclass c) {
	SDL_CloseAudio();
}

JNIEXPORT void JNICALL Java_com_santiance_sdlbits_SDL_mixAudio(JNIEnv *env, jclass c, jobject jdst, jobject jsrc, jint len, jint volume) {
	void *src = (*env)->GetDirectBufferAddress(env, jsrc);
	void *dst = (*env)->GetDirectBufferAddress(env, jdst);
	SDL_MixAudio(dst, src, len, volume);
}
