

<#if methods??>
public:
<#list methods as method>
    int ${method.name}(mediaSox::Unpack &unpack);

</#list>
</#if>

<#if notifications??>
public:
<#list notifications as notification>
    void ${notification.name}(<@compress single_line=true>
                                    <#if notification.parameters??>
                                        <#list notification.parameters as parameter>
                                            <#if parameter.annotation == ''>
                                            <#else >${parameter.annotation}
                                            </#if>
                                            ${parameter.type} ${parameter.prefix}${parameter.name}<#sep >, </#sep>
                                        </#list>
                                    </#if>
                                </@compress>);

</#list>
</#if>
