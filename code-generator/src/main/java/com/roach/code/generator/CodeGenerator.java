package com.roach.code.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.*;

public class CodeGenerator {

    private static final String TEMPLATE_PATH = "/Users/roach/Public/project/java/tutorial/code-generator/src/main/java/com/roach/code/templates";

    private static final Configuration configuration;

    static {
        // step1 创建freeMarker配置实例
        configuration = new Configuration(Configuration.VERSION_2_3_23);
        try {
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        javaCodeGenerator();
        cppCodeGenerator();
        cppNotificationCodeGenerator();
    }

    public static void javaCodeGenerator() {
        // step3 创建数据模型
        List<Method> methods = new ArrayList<>();

        Parameter chatRoom = Parameter.builder().annotation("@NonNull").type("ChatRoom").name("chatRoom").build();
        Parameter attributes = Parameter.builder().annotation("@NonNull").type("Map<String, String>").name("attributes").build();
        Parameter completion = Parameter.builder().annotation("").type("HMR.Completion").name("completion").build();
        Parameter stringKeys = Parameter.builder().annotation("").type("Set<String>").name("keys").build();

        methods.add(new Method().setName("fetchRoomExtraAttributes")
                .setHandleType(308)
                .addParameter(chatRoom)
                .addParameter(stringKeys)
                .addParameter(Parameter.builder().annotation("").type("HMR.CompletionArg<ChatRoomExtraAttribute>").name("completion").build()));

        methods.add(new Method().setName("setRoomExtraAttributes")
                .setHandleType(309)
                .addParameter(chatRoom)
                .addParameter(attributes)
                .addParameter(Parameter.builder().annotation("").type("ChatRoomAttributeOptions").name("options").build())
                .addParameter(completion));

        methods.add(new Method().setName("updateRoomBasicAttributes")
                .setCompatible(true)
                .setHandleType(310)
                .addParameter(chatRoom)
                .addParameter(attributes)
                .addParameter(Parameter.builder().annotation("").type("RoomBasicAttributesOptions").name("options").build())
                .addParameter(completion));

        Map<String, Object> data = new HashMap<>(2, 1);
        data.put("methods", methods);

        String serviceFilePath = "/Users/roach/Public/project/java/tutorial/code-generator/src/main/java/com/hummer/im/chatroom/_internals/ChatRoomServiceImpl.java";
        createFile(data, "ChatRoomServiceImpl.java.ftl", serviceFilePath);

        String nativeFilePath = "/Users/roach/Public/project/java/tutorial/code-generator/src/main/java/com/hummer/im/chatroom/_internals/helper/ChatRoomNative.java";
        createFile(data, "ChatRoomNative.java.ftl", nativeFilePath);

        String eventFilePath = "/Users/roach/Public/project/java/tutorial/code-generator/src/main/java/com/hummer/im/chatroom/_internals/helper/ChatRoomEvent.java";
        createFile(data, "ChatRoomEvent.java.ftl", eventFilePath);
    }

    public static void cppCodeGenerator() {
        List<Method> methods = new ArrayList<>();

        Parameter requestId = Parameter.builder().annotation("").type("RTMRequestId").name("requestId").build();
        Parameter roomId = Parameter.builder().annotation("").type("ChatRoomId").name("roomId").build();
        Parameter attributes = Parameter.builder().annotation("").type("RTMDictionary").name("attributes").build();
        Parameter stringKeys = Parameter.builder().annotation("").type("std::set<std::string>").name("keys").build();

        methods.add(new Method().setName("fetchRoomExtraAttributes")
                .setHandleType(308)
                .addParameter(requestId)
                .addParameter(roomId)
                .addParameter(stringKeys));

        methods.add(new Method().setName("setRoomExtraAttributes")
                .setHandleType(309)
                .addParameter(requestId)
                .addParameter(roomId)
                .addParameter(attributes)
                .addParameter(Parameter.builder().annotation("").type("RoomAttributeOptions").name("options").build()));

        methods.add(new Method().setName("updateRoomBasicAttributes")
                .setHandleType(310)
                .addParameter(requestId)
                .addParameter(roomId)
                .addParameter(attributes)
                .addParameter(Parameter.builder().annotation("").type("RoomAttributeOptions").name("options").build()));

        Map<String, Object> data = new HashMap<>(2, 1);
        data.put("methods", methods);

        String cppNativeFilePath = "/Users/roach/Public/project/java/tutorial/code-generator/src/main/cpp/HummerNative.cpp";
        createFile(data, "HummerNative.cpp.ftl", cppNativeFilePath);

        String hNativeFilePath = "/Users/roach/Public/project/java/tutorial/code-generator/src/main/cpp/HummerNative.h";
        createFile(data, "HummerNative.h.ftl", hNativeFilePath);

        String hEventFilePath = "/Users/roach/Public/project/java/tutorial/code-generator/src/main/cpp/HummerEvent.h";
        createFile(data, "HummerEvent.h.ftl", hEventFilePath);
    }


    public static void cppNotificationCodeGenerator() {
        List<Method> notifications = new ArrayList<>();

        Parameter requestId = Parameter.builder().annotation("").type("RTMRequestId").prefix("").name("requestId").build();
        Parameter code = Parameter.builder().annotation("const").type("RTMCode").name("code").build();
        Parameter roomId = Parameter.builder().annotation("const").type("ChatRoomId").name("roomId").build();
        Parameter attributes = Parameter.builder().annotation("const").type("RTMDictionary").name("attributes").build();
        Parameter userId = Parameter.builder().annotation("const").type("RTMUserId").name("userId").build();
        Parameter stringKeys = Parameter.builder().annotation("").type("std::set<std::string>").name("keys").build();
        Parameter updateTimestamp = Parameter.builder().annotation("").type("uint64_t").prefix("").name("updateTimestamp").build();

        notifications.add(new Method().setName("onFetchRoomExtraAttributesResult")
                .setHandleType(441)
                .addParameter(requestId)
                .addParameter(code)
                .addParameter(roomId)
                .addParameter(attributes)
                .addParameter(userId)
                .addParameter(updateTimestamp));

        notifications.add(new Method().setName("onSetRoomExtraAttributesResult")
                .setHandleType(442)
                .addParameter(requestId)
                .addParameter(code)
                .addParameter(roomId)
                .addParameter(attributes)
                .addParameter(Parameter.builder().annotation("const").type("RoomAttributeOptions").name("options").build()));

        notifications.add(new Method().setName("onUpdateRoomExtraAttributesResult")
                .setHandleType(443)
                .addParameter(requestId)
                .addParameter(code)
                .addParameter(roomId)
                .addParameter(attributes)
                .addParameter(Parameter.builder().annotation("const").type("RoomAttributeOptions").name("options").build()));

        Map<String, Object> data = new HashMap<>(2, 1);
        data.put("notifications", notifications);

        String hummerNotificationH = "/Users/roach/Public/project/java/tutorial/code-generator/src/main/cpp/HummerNotification.h";
        createFile(data, "HummerNotification.h.ftl", hummerNotificationH);

        String hummerNativeH = "/Users/roach/Public/project/java/tutorial/code-generator/src/main/cpp/HummerNative.h";
        createFile(data, "HummerNative.h.ftl", hummerNativeH, true);

        String hummerNativeCpp = "/Users/roach/Public/project/java/tutorial/code-generator/src/main/cpp/HummerNative.cpp";
        createFile(data, "HummerNative.cpp.ftl", hummerNativeCpp);
    }

    public static void createFile(Map<String, Object> data, String templateFileName, String targetFilePath) {
        createFile(data, templateFileName, targetFilePath, false);
    }

    public static void createFile(Map<String, Object> data, String templateFileName, String targetFilePath, boolean append) {
        Writer out = null;
        try {
            // step4 加载模版文件
            Template template = configuration.getTemplate(templateFileName);
            // step5 生成数据
            out = new OutputStreamWriter(new FileOutputStream(targetFilePath, append));
            // step6 输出文件
            template.process(data, out);
            out.flush();

        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}