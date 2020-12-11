
enum EventType {
<#list methods as method>
    EVENT_ROOM_${method.upperUnderscoreName} = ${method.handleType},
</#list>
}

<#list methods as method>
struct Event${method.name?cap_first} : public EventBase
{
    ENGINE_NAMESPACE::RTMRequestId requestId;
<#list method.parameters as parameter>
    <#if parameter?is_first>
        <#else >
    ${parameter.type} ${parameter.name};
    </#if>
</#list>

    Event${method.name?cap_first}() : EventBase((uint32_t) EVENT_ROOM_${method.upperUnderscoreName}) {}

    virtual void marshal(mediaSox::Pack& pk) const{}

    virtual void unmarshal(const mediaSox::Unpack& up) {
        up >> <@compress single_line=true>
                <#if method.parameters??>
                    <#list method.parameters as parameter>
                        ${parameter.name}<#sep > >> </#sep>
                    </#list>
                </#if>
               </@compress>;
    }
};

</#list>