package com.roach.code.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.*;

public class CodeGenerator {

    private static final String TEMPLATE_PATH = "/Users/roach/Public/project/java/tutorial/code-generator/src/main/java/com/roach/code/templates";

    public static void main(String[] args) {
        // step1 创建freeMarker配置实例
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);

        Writer out = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            // step3 创建数据模型
            List<Method> methods = new ArrayList<>();

            Parameter chatRoom = Parameter.builder().annotation("@NonNull").type("ChatRoom").name("chatRoom").build();
            Parameter attributes = Parameter.builder().annotation("@NonNull").type("Map<String, String>").name("attributes").build();
            Parameter completion = Parameter.builder().annotation("").type("HMR.Completion").name("completion").build();
            Parameter stringKeys = Parameter.builder().annotation("").type("Set<String").name("keys").build();

            methods.add(new Method().setName("updateRoomBasicAttributes")
                    .setCompatible(true)
                    .addParameter(chatRoom)
                    .addParameter(attributes)
                    .addParameter(Parameter.builder().annotation("").type("RoomBasicAttributesOptions").name("options").build())
                    .addParameter(completion));

            methods.add(new Method().setName("setRoomExtraAttributes")
                    .addParameter(chatRoom)
                    .addParameter(attributes)
                    .addParameter(Parameter.builder().annotation("").type("ChatRoomAttributeOptions").name("options").build())
                    .addParameter(completion));

            methods.add(new Method().setName("fetchRoomExtraAttributes")
                    .addParameter(chatRoom)
                    .addParameter(stringKeys)
                    .addParameter(Parameter.builder().annotation("").type("HMR.CompletionArg<ChatRoomExtraAttribute>").name("completion").build()));

            Map<String, Object> dataMap = new HashMap<>(2, 1);
            dataMap.put("methods", methods);
            // step4 加载模版文件
            Template template = configuration.getTemplate("ChatRoomServiceImpl.ftl");
            // step5 生成数据
            String filePath = "/Users/roach/Public/project/java/tutorial/code-generator/src/main/java/com/hummer/im/chatroom/_internals/ChatRoomServiceImpl.java";
            out = new OutputStreamWriter(new FileOutputStream(filePath));
            // step6 输出文件
            template.process(dataMap, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}