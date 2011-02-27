#include "sdlbits.h"

jobject Wrap(JNIEnv *env, const char *type, void *data, int size) {
	if (data == NULL) {
		return NULL;
	}
	
	jclass clazz;
	//jclass buffers;
	jmethodID ctor;
	jobject buf;
	jobject wrapped;
	
	try_or_die(clazz = (*env)->FindClass(env, type), "Unable to load class");
	//try_or_die(buffers = (*env)->FindClass(env, "java/nio/ByteBuffer"), "Unable to find ByteBuffer class");
	try_or_die(ctor = (*env)->GetMethodID(env, clazz, "<init>", "(Ljava/nio/ByteBuffer;)V"), "Unable to hook ctor");
	try_or_die(buf = (*env)->NewDirectByteBuffer(env, data, size), "Unable to allocate ByteBuffer");
	try_or_die(wrapped = (*env)->NewObject(env, clazz, ctor, buf), "Unable to allocate");
	
	(*env)->DeleteLocalRef(env, buf);
	(*env)->DeleteLocalRef(env, clazz);
	
	return wrapped;
}

jobject WrapBuffer(JNIEnv *env, const char *type, jobject buf) {
	return NULL;
}

void* Unwrap(JNIEnv *env, const char *type, jobject obj) {
	if (obj == NULL) return NULL;
	
	jclass clazz = (*env)->FindClass(env, type);
	//jclass buffers = (*env)->FindClass(env, "java/nio/ByteBuffer");
	if (clazz == NULL) return NULL;
	
	jfieldID fid = (*env)->GetFieldID(env, clazz, "data", "Ljava/nio/ByteBuffer;");
	jobject buf = (*env)->GetObjectField(env, obj, fid);
	
	if (buf == NULL) {
		return NULL;
	}
	
	return (*env)->GetDirectBufferAddress(env, buf);
}

void Throw(JNIEnv *env, const char *name, const char *msg) {
	jclass clazz = (*env)->FindClass(env, name);
	
	if (!clazz) {
		return;
	}
	
	(*env)->ThrowNew(env, clazz, msg);
}


