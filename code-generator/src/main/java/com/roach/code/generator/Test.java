package com.roach.code.generator;

import com.hummer.im.chatroom.ChatRoomService;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Test {

    public static void main(String[] args) {
        Class<ChatRoomService> clazz = ChatRoomService.class;

        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            System.out.println(method.getName());
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println(parameter.getType().getTypeName());
                System.out.println(parameter.getParameterizedType());
            }
        }
    }
}
