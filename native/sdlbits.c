#include "sdlbits.h"

jclass JByteBuffer = NULL;
jclass JSDL = NULL;
jclass JEvent = NULL;
jclass JEventFilter = NULL;
jclass JSurface = NULL;
jclass JJoystick = NULL;

jmethodID JEventFilter_filterEvent = NULL;
jmethodID JByteBuffer_allocateDirect = NULL;
jmethodID JEvent_Constructor = NULL;
jmethodID JJoystick_Constructor = NULL;

jfieldID JJoystick_addr = NULL;

JNIEXPORT jint JNI_OnLoad(JavaVM *vm, void *reserved) {
	return JNI_VERSION_1_6;
}

JNIEXPORT void JNI_OnUnload(JavaVM *vm, void *reserved) {
	SDL_Quit();
}

JNIEXPORT jboolean JNICALL Java_com_santiance_sdlbits_SDL_initSDLbits(JNIEnv *env, jclass c) {	
	if (!(
		(JSDL = c) &&
		(JByteBuffer = (*env)->FindClass(env, "java/nio/ByteBuffer")) &&
		(JEvent = (*env)->FindClass(env, "com/santiance/sdlbits/SDLEvent")) &&
		(JSurface = (*env)->FindClass(env, "com/santiance/sdlbits/SDLSurface")) &&
		(JJoystick = (*env)->FindClass(env, "com/santiance/sdlbits/SDLJoystick")) &&
		(JEventFilter = (*env)->FindClass(env, "com/santiance/sdlbits/SDLEventFilter")) &&
		(JEventFilter_filterEvent = (*env)->GetMethodID(env, JEventFilter, "filterEvent", "(Lcom/santiance/sdlbits/SDLEvent;)Z")) &&
		(JByteBuffer_allocateDirect = (*env)->GetStaticMethodID(env, JByteBuffer, "allocateDirect", "(I)Ljava/nio/ByteBuffer;")) &&
		(JEvent_Constructor = (*env)->GetMethodID(env, JEvent, "<init>", "(Ljava/nio/ByteBuffer;)V")) &&
		(JJoystick_Constructor = (*env)->GetMethodID(env, JJoystick, "<init>", "(JI)V")) &&
		(JJoystick_addr = (*env)->GetFieldID(env, JJoystick, "addr", "J"))
	)) {
		return 0;
	}
	
	return 1;
}

JNIEXPORT jstring JNICALL Java_SDL_getError(JNIEnv *env, jclass c) {
	return NULL;
}

JNIEXPORT void JNICALL Java_SDL_quit(JNIEnv *env, jclass c) {
	SDL_Quit();
}


JNIEXPORT void JNICALL Java_SDL_init(JNIEnv *env, jclass c, jint flags) {
	SDL_Init(flags);
}

JNIEXPORT void JNICALL Java_com_santiance_sdlbits_SDL_init(JNIEnv *env, jclass c, jint flags) {
	SDL_Init(flags);
}
