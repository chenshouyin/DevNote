LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := TestNdk
LOCAL_SRC_FILES := com_example_testndkeclipse_JniClient.c
LOCAL_LDLIBS += -llog
include $(BUILD_SHARED_LIBRARY)
