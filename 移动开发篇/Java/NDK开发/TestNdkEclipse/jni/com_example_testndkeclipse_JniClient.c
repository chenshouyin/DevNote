#include "com_example_testndkeclipse_JniClient.h"
#include <stdlib.h>
#include <stdio.h>
#include "string.h"
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
 *
 * 实现字符串的拼接
 */
JNIEXPORT jstring JNICALL Java_com_example_testndkeclipse_JniClient_AddStr(
		JNIEnv *env, jclass arg, jstring instringA, jstring instringB) {
	//jstring str = (*env)->NewStringUTF(env, instringA+"=="+instringB+"are from JNI");
	jstring str = (*env)->NewStringUTF(env, "I am from JNI");
	//LOGI("%d",len1);//c语言输出  可以在eclipse中打印出来  用法如printf()

	//将jstring转换成const char*指针，使用const修饰符表示其内容不可被修改
	const char* str1 = (*env)->GetStringUTFChars(env, instringA, NULL); //C的语法
	//1.访问java.lang.String对应的JNI类型jstring时，没有像访问基本数据类型一样直接使用，
	//因为它在Java是一个引用类型，所以在本地代码中只能通过GetStringUTFChars这样的JNI函数来访问字符串的内容
	const char* str2 = (*env)->GetStringUTFChars(env, instringB, NULL);
	//2.const char* str1 = env->GetStringUTFChars(instringA,NULL);//C++的语法

	//3.最后一个参数是是否拷贝的标志
	//如果B是原始字符串java.lang.String 的拷贝，则isCopy被赋值为 JNI_TRUE。
	//如果B和原始字符串指向的是JVM中的同一份数据，则 isCopy被赋值为 JNI_FALSE
	//因为你不必关心 JVM 是否会返回原始字符串的拷贝，你只需要为 isCopy传递NULL作为参数

//4.C语言中，为什么字符串可以赋值给字符指针变量
//
//	char *p,a='5';
//	p=&a;                     //显然是正确的，
//	p="abcd";              //但为什么也可以这样赋值？？
//
//	问：一直理解不了为什么可以将字串常量赋值给字符指针变量，请各位指点！
//
//	答：
//	双引号做了3件事：
//	1.申请了空间(在常量区)，存放了字符串
//	2. 在字符串尾加上了'/0'
//	3.返回地址

	//计算新字符串长度
	int size = strlen(str1) + strlen(str2);

	//char *charPoint = new char[size+1];//C++中就建议使用new和delete 为了兼容,还保留malloc
	//char *str_point = new char[size+1];
	char *n_str_point = (char *) malloc(size + 1); //因为malloc返回的指针类型为止,所以需要强制转换 (char *)
	strcpy(n_str_point, str1); //将字符串str1拷贝到字符数组

	//jstring jstr = (*env)->NewStringUTF(env,"输出字符串");//有中文报错  非UTF-8转换UTF
	//http://www.jianshu.com/p/333648d8a998  JNI日志调试中文乱码  android jni 中文乱码,该怎么解决
	//主要是环境问题 .c文件写中文的话 你保存成utf-8   要更改此c文件编码为utf-8
	jstring jstr = (*env)->NewStringUTF(env, "english"); //有中文报错  非UTF-8转换UTF
	char *outStr;
	outStr = (*env)->GetStringUTFChars(env, jstr, NULL);
//	LOGI("%s",outStr);//将中文先转换为jni层的jstring再转换为c中的字符后输出,不然会乱码
//	LOGI("%s",str1);//输出字符串
//	LOGI("%s",str2);
//
//	LOGI("%s","输出字符串长度");
//	LOGI("%d",strlen(str1));//输出字符串长度
//	LOGI("%d",strlen(str2));
//
//	LOGI("%s","输出拷贝之后的新字符串");
//	LOGI("%s",n_str_point);//输出拷贝之后的新字符串
//	LOGI("%d",strlen(n_str_point));
//
//	LOGI("%s","输出拼接之后的新字符串");
	strcat(n_str_point, str2); //函数原形char *strcat(char *dest, const char *src);
//	LOGI("%s",n_str_point);//输出拷贝之后的新字符串
//	LOGI("%d",strlen(n_str_point)); //strlen返回长度遇到第一个\n结束 http://www.jianshu.com/p/7fa9f615ee49

	jstring result = (*env)->NewStringUTF(env, n_str_point);

	//删除刚刚动态分配的内存 避免引起内存泄漏
	//delete[] n_char;//C++
	free(n_str_point); //注意只能释放动态分配的内存
	//free() 只能释放动态分配的内存空间，并不能释放任意的内存。下面的写法是错误的
	//int a[10];
	// ...
	//free(a);

	//释放指针  参照 http://blog.csdn.net/xyang81/article/details/42066665
	(*env)->ReleaseStringUTFChars(env, instringA, str1); //前面是jdata后面是cdata
	(*env)->ReleaseStringUTFChars(env, instringB, str2);

	return result; //需要转换为中间层jstring返回
}

/*
 * Class:     com_example_testndkeclipse_JniClient
 * Method:    TestDataTypeJ2C
 * Signature: (SIJFDCZBLjava/lang/String;Ljava/lang/Object;Lcom/example/testndkeclipse/MyJavaClass;)V
 */
JNIEXPORT void JNICALL Java_com_example_testndkeclipse_JniClient_TestDataTypeJ2C(
		JNIEnv * env, jclass mJclass, jshort mJshort, jint mJint, jlong mJlong,
		jfloat mJfloat, jdouble mJdouble, jchar mJchar, jboolean mJboolean,
		jbyte mJbyte, jstring mJstring, jintArray mJintArray, jobject mJobject,
		jobject mJobjectClass)

{
	// %d 有符号10进制整数%ld 长整型%hd短整型 %md,m指定的是输出字段的宽度
	// printf("s=%hd, i=%d, l=%ld, f=%f, d=%lf, c=%c, z=%c, b=%d", s, i, l, f, d, c, z, b);
	//short s, int i, long l, float f,double d, char c, boolean z, byte b
	LOGI("mJshort==>%hd\n", mJshort);
	LOGI("mJint==>%d\n", mJint);
	LOGI("mJlong==>%l\n", mJlong);
	LOGI("mJfloat==>%f\n", mJfloat);
	LOGI("mJdouble==>%lf\n", mJdouble);
	LOGI("mJchar==>%c\n", mJchar);
	LOGI("mJboolean==>%d\n", mJboolean);
	LOGI("mJbyte==>%d\n", mJbyte);
	//基本数据类型
	//jclass mJclass, jshort mJshort, jint mJint, jlong mJlong, jfloat mJfloat,
	//jdouble mJdouble, jchar mJchar, jboolean mJboolean, jbyte mJbyte

	//引用类型
	//jstring mJstring,
	//jintArray mJintArray
}

/*
 * Class:     com_example_testndkeclipse_JniClient
 * Method:    sumArray
 * Signature: ([I)[I
 *
 * 实现数组求和
 */
JNIEXPORT jintArray JNICALL Java_com_example_testndkeclipse_JniClient_sumArray(
		JNIEnv * env, jclass mJclass, jintArray mjintArray) {

	jint cSum, cLen = 0;
	//1.获取数组长度
	cLen = (*env)->GetArrayLength(env, mjintArray);
	//2.根据数组长度和数组元素的数据类型申请存放java数组元素的缓冲区

	//讲jarray转换为c可操作的数组
//	jint* cPArray = (jint*) malloc(sizeof(jint) * cLen);
//	if (cPArray == NULL)
//		return NULL; //可能申请内存空间失败
//	//3.初始化内存区 http://www.jianshu.com/p/cb8a3c004563
//	memset(cPArray, 0, sizeof(jint) * cLen);
//	LOGI("cLen==length>%d\n", cLen);
//	LOGI("cLen==sizeof>%d\n", sizeof(jint)*cLen);
//	//4. 拷贝Java数组中的所有元素到缓冲区中
//	(*env)->GetIntArrayRegion(env, mjintArray, 0, cLen, cPArray); //得到数组方式1  把数据放在缓冲区


	// 可能数组中的元素在内存中是不连续的，JVM可能会复制所有原始数据到缓冲区，然后返回这个缓冲区的指针
	jint* cPArray  = (*env)->GetIntArrayElements(env,mjintArray,NULL);
	if (cPArray == NULL) {
	        return 0;   // JVM复制原始数据到缓冲区失败
	}

	jint i;
	for (i = 0; i < cLen; i++) {//求数组和
		cSum = cSum + cPArray[i];
	}
	LOGI("jSum==>%d\n", cSum);

	//给java层返回数组方式1
	jint cInts[cLen];//定义一个数组
	for (i = 0; i < cLen; i++) {
		cInts[i] = cPArray[i];
	}
	//length =  sizeof(array) / sizeof(array[0]);  c/c++中求数组长度
	 jintArray result;
	 result = (*env)->NewIntArray(env, cLen);
	 if (result == NULL) {
	     return NULL; /* out of memory error thrown */
	 }
	 //move from the temp structure to the java structure  将native数组转换为java层数组
	(*env)->SetIntArrayRegion(env,result,0,sizeof(cInts) / sizeof(cInts[0]),cInts);

	 (*env)->ReleaseIntArrayElements(env,mjintArray, cPArray, 0); // 释放可能复制的缓冲区
	return result;
}
#ifdef __cplusplus
}
#endif
