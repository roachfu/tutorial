

<#list methods as method>
int HummerChatRoomListener::${method.name}(<@compress single_line=true>
                                                <#if method.parameters??>
                                                    <#list method.parameters as parameter>
                                                        ${parameter.annotation} ${parameter.type} ${parameter.name}<#sep >, </#sep>
                                                    </#list>
                                                </#if>
                                            </@compress>){
    HummerNative::instance().${method.name}(<@compress single_line=true>
                                                <#if method.parameters??>
                                                    <#list method.parameters as parameter>
                                                        ${parameter.name}<#sep >, </#sep>
                                                    </#list>
                                                </#if>
                                            </@compress>);
    return 0;
}

</#list>