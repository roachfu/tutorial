
<#list methods as method>
    int ${method.name}(<@compress single_line=true>
                            <#if method.parameters??>
                                <#list method.parameters as parameter>
                                    ${parameter.annotation} ${parameter.type} ${parameter.name}<#sep >, </#sep>
                                </#list>
                            </#if>
                        </@compress>) override;
</#list>