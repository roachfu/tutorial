
public class ChatRoomEvent {

<#list methods as method>
    public static final int EVENT_ROOM_${method.upperUnderscoreName} = ${method.handleType};
</#list>

<#list methods as method>

    public static class Event${method.name?cap_first} extends HummerEvent.EventBase {
        private long requestId;
<#list method.parameters as parameter>
    <#if parameter?is_last>
        <#else >
        private ${parameter.type} ${parameter.name};
    </#if>
</#list>

        Event${method.name?cap_first}(long requestId<@compress single_line=true>
                                        <#if method.parameters??>
                                            <#list method.parameters as parameter>
                                                <#if parameter?is_last>
                                                <#else >
                                                    , ${parameter.type} ${parameter.name}
                                                </#if>
                                            </#list>
                                        </#if></@compress>) {
            event = EVENT_ROOM_${method.upperUnderscoreName};
            this.requestId = requestId;
    <#list method.parameters as parameter>
        <#if parameter?is_last>
            <#else >
            this.${parameter.name} = ${parameter.name};
        </#if>
    </#list>
        }

        @Override
        public byte[] marshall() {
            pushInt64(requestId);
<#list method.parameters as parameter>
    <#if parameter?is_last>
        <#else >
        <#if parameter.type == 'ChatRoom'>
            pushInt(chatRoom == null ? 0 : chatRoom.getId());
            <#elseif parameter.type == 'Map<String, String>'>
            pushMap(${parameter.name}, String.class);
            <#elseif parameter.type == 'Set<String>'>
            if (${parameter.name} == null) {
                ${parameter.name} = new HashSet<>();
            }
            pushInt(${parameter.name}.size());
            for (String key : ${parameter.name}) {
                pushString16(key);
            }
            <#elseif parameter.type == 'User'>
            pushInt64(user == null ? 0 : user.getId());
            <#elseif parameter.type == 'boolean'>
            pushBool(${parameter.name});
        </#if>
    </#if>
</#list>
            return super.marshall();
        }
    }
</#list>
}



