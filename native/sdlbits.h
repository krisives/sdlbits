#ifndef SDLBITS_H
#define SDLBITS_H

#include "sdlbits_java.h"
#include <SDL/SDL.h>


extern jclass JByteBuffer;
extern jclass JSDL;
extern jclass JEvent;
extern jclass JEventFilter;
extern jclass JSurface;
extern jclass JJoystick;

extern jmethodID JByteBuffer_allocateDirect;
extern jmethodID JEventFilter_filterEvent;
extern jmethodID JEvent_Constructor;
extern jmethodID JJoystick_Constructor;

extern jfieldID JJoystick_addr;

//extern jmethodID JEvent_Constructors[20];
//extern jclass JEvent_Classes[20];

jobject Wrap(JNIEnv *env, const char *type, void *data, int size);
jobject WrapBuffer(JNIEnv *env, const char *type, jobject buf);
void* Unwrap(JNIEnv *env, const char *type, jobject obj);
void Throw(JNIEnv *env, const char *name, const char *msg);

#define try_or_die(it, ondeath) if (!(it)) { printf("%s\n", (ondeath));return NULL; }

#define UnwrapPixelFormat(format) (SDL_PixelFormat*)Unwrap(env, "PixelFormat", (format))

#endif
