#include "com_example_testndkeclipse_JniClient.h"
#include <stdlib.h>
#include <stdio.h>

// 引入log头文件
#include  <android/log.h>
// log标签
#define  TAG    "=====CSY====="
// 定义info信息
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)
// 定义debug信息
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)
// 定义error信息
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__)

#ifdef __cplusplus
extern "C"
{
#endif
/*
 * Class:     com_example_testndkeclipse_JniClient
 * Method:    AddStr
 * Signature: (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_example_testndkeclipse_JniClient_AddStr
  (JNIEnv *env, jclass arg, jstring instringA, jstring instringB)
{
    //jstring str = (*env)->NewStringUTF(env, instringA+"=="+instringB+"are from JNI");
    jstring str = (*env)->NewStringUTF(env,"I am from JNI");

	int len1 = strlen(instringA);
	int len2 = strlen(instringB);
//	char str1[len1] = instringA;
//	char str2[len2] = instringB;
//	char str2[len2] ;
//	str2 = instringA;
	LOGI("%d",len1);//c语言输出  可以在eclipse中打印出来  用法如printf()
    return str;
}

/*
* Class:     com_example_testndkeclipse_JniClient
* Method:    AddInt
* Signature: (II)I
*/
JNIEXPORT jint JNICALL Java_com_example_testndkeclipse_JniClient_AddInt
  (JNIEnv *env, jclass arg, jint a, jint b)
{
    return a + b;
}

#ifdef __cplusplus
}
#endif
