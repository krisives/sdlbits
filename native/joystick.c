#include "sdlbits.h"
#include <SDL/SDL_joystick.h>

//#define UnwrapJoy(joy)		(SDL_Joystick*)Unwrap(env, "com/santiance/sdlbits/SDLJoystick", (joy))
//#define WrapJoy(joy)		Wrap(env, "com/santiance/sdlbits/SDLJoystick", (joy), sizeof(SDL_Joystick))

#define UnwrapJoy(jjoy) ((SDL_Joystick*)(*env)->GetLongField(env, (jjoy), JJoystick_addr))

JNIEXPORT void JNICALL Java_com_santiance_sdlbits_SDL_joystickUpdate(JNIEnv *env, jclass c) {
	SDL_JoystickUpdate();
}

JNIEXPORT jint JNICALL Java_com_santiance_sdlbits_SDL_numJoysticks(JNIEnv *env, jclass c) {
	return SDL_NumJoysticks();
}

JNIEXPORT jstring JNICALL Java_com_santiance_sdlbits_SDL_joystickName(JNIEnv *env, jclass c, jint index) {
	const char *buf = SDL_JoystickName(index);
	if (!buf) return NULL;
	return (*env)->NewStringUTF(env, buf);
}

JNIEXPORT jobject JNICALL Java_com_santiance_sdlbits_SDL_joystickOpen(JNIEnv *env, jclass c, jint index) {
	SDL_Joystick *joy = SDL_JoystickOpen(index);
	
	if (!joy) {
		//Throw("com/santiance/sdlbits/SDL$Exception", "Unable to open joystick");
		return NULL;
	}
	
	jlong addr = (long)joy;
	jobject jjoy;
	
	try_or_die(JJoystick = (*env)->FindClass(env, "com/santiance/sdlbits/SDLJoystick"), "Unable to find Joystick class");
	try_or_die(JJoystick_Constructor = (*env)->GetMethodID(env, JJoystick, "<init>", "(JI)V"), "Unable to find Joystick constructor");
	try_or_die(jjoy = (*env)->NewObject(env, JJoystick, JJoystick_Constructor, addr, index), "Unable to allocate Joystick object");
	
	return jjoy;
}

JNIEXPORT jboolean JNICALL Java_com_santiance_sdlbits_SDL_joystickOpened(JNIEnv *env, jclass c, jint index) {
	return SDL_JoystickOpened(index);
}

JNIEXPORT jint JNICALL Java_com_santiance_sdlbits_SDL_joystickIndex(JNIEnv *env, jclass c, jobject jjoy) {
	SDL_Joystick *joy = UnwrapJoy(jjoy);
	if (!joy) return 0;
	return SDL_JoystickIndex(joy);
}

JNIEXPORT jint JNICALL Java_com_santiance_sdlbits_SDL_joystickNumButtons(JNIEnv *env, jclass c, jobject jjoy) {
	SDL_Joystick *joy = UnwrapJoy(jjoy);
	if (!joy) return 0;
	return SDL_JoystickNumButtons(joy);
}

JNIEXPORT jint JNICALL Java_com_santiance_sdlbits_SDL_joystickGetButton(JNIEnv *env, jclass c, jobject jjoy, jint button) {
	SDL_Joystick *joy = UnwrapJoy(jjoy);
	if (!joy) return 0;
	return SDL_JoystickGetButton(joy, button);
}

JNIEXPORT void JNICALL Java_com_santiance_sdlbits_SDL_joystickClose(JNIEnv *env, jclass c, jobject jjoy) {
	SDL_Joystick *joy = UnwrapJoy(jjoy);
	if (!joy) return;
	SDL_JoystickClose(joy);
}

JNIEXPORT jint JNICALL Java_com_santiance_sdlbits_SDL_joystickGetAxis(JNIEnv *env, jclass c, jobject jjoy, jint axis) {
	SDL_Joystick *joy = UnwrapJoy(jjoy);
	return SDL_JoystickGetAxis(joy, axis);
}

JNIEXPORT jint JNICALL Java_com_santiance_sdlbits_SDL_joystickNumAxes(JNIEnv *env, jclass c, jobject jjoy) {
	SDL_Joystick *joy = UnwrapJoy(jjoy);
	return SDL_JoystickNumAxes(joy);
}
