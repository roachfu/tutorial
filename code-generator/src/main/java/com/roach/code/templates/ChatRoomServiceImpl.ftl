package com.hummer.im.chatroom._internals;

import com.hummer.im.HMR;
import com.hummer.im._internals.utility.CompletionUtils;
import com.hummer.im._internals.utility.HMRCompletion;
import com.hummer.im._internals.utility.ReportFunction;
import com.hummer.im.chatroom.ChatRoomService;
import com.hummer.im.chatroom.model.attribute.RoomBasicAttributesOptions;
import com.hummer.im.chatroom.model.id.ChatRoom;
import com.hummer.im.model.RequestId;
import lombok.NonNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatRoomServiceImpl implements ChatRoomService{
    <#list methods as method>

    @Override
    public void ${method.name}(<@compress single_line=true>
            <#if method.parameters??>
                <#list method.parameters as parameter>
                    <#if parameter.annotation == ''>
                    <#else >${parameter.annotation}
                    </#if>
                    ${parameter.type} ${parameter.name}<#sep>, </#sep>
                </#list>
            </#if></@compress>) {
        final RequestId requestId = newRequestId();
        <#if method.compatible>
        HMRCompletion c = createHMRCompletion(requestId, completion, ReportFunction.UPDATE_ROOM_BASIC_ATTRIBUTES);
        <#else >
        HMRCompletion c = new HMRCompletion(requestId, completion);
        </#if>

        Error error = validRoomId(chatRoom);
        if (error != null) {
            CompletionUtils.dispatchFailure(c, error);
            return;
        }

        if (completion != null) {
            CHAT_ROOM_COMPLETIONS.put(requestId, c);
        }
        ChatRoomNative.${method.name}(requestId.getId()<@compress single_line=true>
            <#if method.parameters??>
                <#list method.parameters as parameter>
                    <#if parameter?is_last>
                    <#else>
                        , ${parameter.name}
                    </#if>
                </#list>
            </#if></@compress>);
    }
    </#list>

    private static Error validRoomId(ChatRoom chatRoom) {
        return null;
    }

    private RequestId newRequestId() {
        return null;
    }

    private HMRCompletion createHMRCompletion(@NonNull final RequestId requestId,
                                            final HMR.Completion completion,
                                            final String functionName) {
        return null;
    }

    private static final Map<RequestId, HMRCompletion> CHAT_ROOM_COMPLETIONS = new ConcurrentHashMap<>();
}