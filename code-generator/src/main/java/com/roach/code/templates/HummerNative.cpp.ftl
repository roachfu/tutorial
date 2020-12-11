
<#if methods??>

void HummerNative::initEventHandlers() {
<#list methods as method >
    m_eventHandlers[EVENT_ROOM_${method.upperUnderscoreName}] = &HummerNative::${method.name};
</#list>
}

<#list methods as method>

int HummerNative::${method.name}(mediaSox::Unpack &unpack) {
    Event${method.name?cap_first} event;
    HUMMER_UNMARSHAL_EVENT(event, unpack);
    return IRTMEngine::instance()->getChatRoomService()->${method.name}(<@compress single_line=true>
                                                                            <#if method.parameters??>
                                                                                <#list method.parameters as parameter>
                                                                                    event.${parameter.name}<#sep>, </#sep>
                                                                                </#list>
                                                                            </#if>
                                                                        </@compress>);
}
</#list>

</#if>

<!-- 通知的代码自动生成 -->
<#if notifications??>
<#list notifications as notification>
void HummerNative::${notification.name}(<@compress single_line=true>
                                            <#if notification.parameters??>
                                                <#list notification.parameters as parameter>
                                                    <#if parameter.annotation == ''>
                                                    <#else >${parameter.annotation}
                                                    </#if>
                                                    ${parameter.type} ${parameter.prefix}${parameter.name}<#sep >, </#sep>
                                                </#list>
                                            </#if>
                                        </@compress>){
    Notify${notification.name?cap_first} *notify = new Notify${notification.name?cap_first}(<@compress single_line=true>
                                                                                                <#if notification.parameters??>
                                                                                                    <#list notification.parameters as parameter>
                                                                                                        ${parameter.name}<#sep>, </#sep>
                                                                                                    </#list>
                                                                                                </#if>
                                                                                            </@compress>);
    notify->type = NOTIFY_${notification.upperUnderscoreName};
    sendNotification(notify);
}

</#list>
</#if>