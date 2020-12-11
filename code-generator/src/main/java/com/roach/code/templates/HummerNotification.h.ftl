
<#if notifications??>
enum NotificationType {
    <#list notifications as notification>
    NOTIFY_${notification.upperUnderscoreName} = ${notification.handleType}
    </#list>
}

<#list notifications as notification>
struct Notify${notification.name?cap_first} : public NotifyBase {
    <#list notification.parameters as parameter>
    <#if parameter.type?starts_with('uint')>
    ${parameter.type} _${parameter.name};
    <#else >
    ENGINE_NAMESPACE::${parameter.type} _${parameter.name};
    </#if>
    </#list>

    explicit Notify${notification.name?cap_first}(<@compress single_line=true>
                                                        <#list notification.parameters as parameter>
                                                            <#if parameter.annotation == ''>
                                                                <#else >${parameter.annotation}
                                                            </#if>
                                                            <#if parameter.type?starts_with("uint")>
                                                                <#else >ENGINE_NAMESPACE::
                                                            </#if>
                                                            ${parameter.type} ${parameter.prefix}${parameter.name}<#sep >, </#sep>
                                                        </#list>
                                                    </@compress>)
            : NotifyBase(NOTIFY_${notification.upperUnderscoreName}){
    <#list notification.parameters as parameter>
        _${parameter.name} = ${parameter.name};
    </#list>
    }

    virtual void marshal(mediaSox::Pack &pk) const {
        pk << _requestId << _code.code << _code.desc<@compress single_line=true>
                                                        <#list notification.parameters as parameter>
                                                            <#if parameter.name != 'requestId' && parameter.name != 'code'>
                                                             << _${parameter.name}
                                                            </#if>
                                                        </#list>
                                                    </@compress>;
    }
};

</#list>
</#if>