#include "sdlbits.h"

const char *JEvent_ClassNames[] = {
	"com/santiance/sdlbits/SDLEvent",
	NULL, //SDL_ACTIVEEVENT
	"com/santiance/sdlbits/SDLEvent$Keyboard", //SDL_KEYDOWN
	"com/santiance/sdlbits/SDLEvent$Keyboard", //SDL_KEYUP
	"com/santiance/sdlbits/SDLEvent$MouseMotion", //SDL_MOUSEMOTION
	"com/santiance/sdlbits/SDLEvent$MouseButton", //SDL_MOUSEBUTTONDOWN
	"com/santiance/sdlbits/SDLEvent$MouseButton", //SDL_MOUSEBUTTONUP
	"com/santiance/sdlbits/SDLEvent$JoyAxis", //SDL_JOYAXISMOTION
	NULL, //SDL_JOYBALLMOTION
	NULL, //SDL_JOYHATMOTION
	"com/santiance/sdlbits/SDLEvent$JoyButton", //SDL_JOYBUTTONDOWN
	"com/santiance/sdlbits/SDLEvent$JoyButton", //SDL_JOYBUTTONUP
	"com/santiance/sdlbits/SDLEvent$Quit", //SDL_QUIT
	"com/santiance/sdlbits/SDLEvent$System", //SDL_SYSWMEVENT
	NULL, //SDL_EVENT_RESERVEDA
	NULL, //SDL_EVENT_RESERVEDB
	NULL, //SDL_VIDEORESIZE
	NULL //SDL_VIDEOEXPOSE
};

//jclass JEvent_Classes[20];
//jmethodID JEvent_Constructors[20];
jobject eventFilter = NULL;
JavaVM *jvm = NULL;

jobject WrapEvent(JNIEnv *env, const SDL_Event *event, jobject byteBuffer) {
	const char *className = "com/santiance/sdlbits/SDLEvent";
	jobject jevent = NULL;
	
	if (byteBuffer == NULL || event == NULL) {
		return NULL;
	}
	
	if (event->type < 18 && event->type >= 0 && (className = JEvent_ClassNames[event->type])) {
		jclass clazz = /*JEvent_Classes[event->type];
		if (!clazz) clazz = JEvent_Classes[event->type] = */(*env)->FindClass(env, className);
		
		if (clazz) {
			jmethodID ctor = /*JEvent_Constructors[event->type];
			if (!ctor) ctor = JEvent_Constructors[event->type] = */(*env)->GetMethodID(env, clazz, "<init>", "(Ljava/nio/ByteBuffer;)V");
			
			if (ctor) {
				jevent = (*env)->NewObject(env, clazz, ctor, byteBuffer);
			}
		}
	}
	
	if (!jevent) {
		jevent = (*env)->NewObject(env, JEvent, JEvent_Constructor, byteBuffer);
	}
	
	//(*env)->DeleteLocalRef(env, jevent);
	return jevent;
}

int SDLbits_EventFilter(const SDL_Event *event) {
	if (!eventFilter || !JEventFilter || !JEventFilter_filterEvent) return 1;
	JNIEnv *env;
	(*jvm)->AttachCurrentThread(jvm, (void **)&env, NULL);
	jobject byteBuffer = (*env)->NewDirectByteBuffer(env, (void*)event, sizeof(SDL_Event));
	jobject jevent = WrapEvent(env, event, byteBuffer);
	return (*env)->CallBooleanMethod(env, JEventFilter, JEventFilter_filterEvent, jevent);
}


JNIEXPORT void JNICALL Java_com_santiance_sdlbits_SDL_setEventFilter(JNIEnv *env, jclass c, jobject filter) {
	(*env)->GetJavaVM(env, &jvm);
	
	eventFilter = filter;
	SDL_SetEventFilter(SDLbits_EventFilter);
}

JNIEXPORT jobject JNICALL Java_com_santiance_sdlbits_SDL_pollEvent(JNIEnv *env, jclass c) {
	jobject byteBuffer;
	static SDL_Event event;
	
	//try_or_die(byteBuffer = (*env)->CallStaticObjectMethod(env, JByteBuffer, JByteBuffer_allocateDirect, sizeof(SDL_Event)), "Unable to allocate ByteBuffer");
	try_or_die(byteBuffer = (*env)->NewDirectByteBuffer(env, &event, sizeof(SDL_Event)), "Can't get byte buffer");
	//try_or_die(event = (SDL_Event*)(*env)->GetDirectBufferAddress(env, byteBuffer), "Cant allocate direct buffer");
	
	if (SDL_PollEvent(&event) == 0) {
		return NULL;
	}
	
	return WrapEvent(env, &event, byteBuffer);
}
