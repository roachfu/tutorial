import com.hummer.im.model.id.ChatRoom;
import com.hummer.im.model.id.User;

import java.util.Map;
import java.util.Set;

public class ChatRoomNative {
    <#list methods as method>

    public static void ${method.name}(long requestId<@compress single_line=true>
                                        <#if method.parameters??>
                                            <#list method.parameters as parameter>
                                                <#if parameter?is_last>
                                                    <#else >
                                                        , ${parameter.type} ${parameter.name}
                                                </#if>
                                            </#list>
                                        </#if></@compress>) {
        ChatRoomEvent.Event${method.name?cap_first} event = new ChatRoomEvent.Event${method.name?cap_first}(requestId<@compress single_line=true>
            <#if method.parameters??>
                <#list method.parameters as parameter>
                    <#if parameter?is_last>
                    <#else>
                        , ${parameter.name}
                    </#if>
                </#list>
            </#if></@compress>);
        HummerNative.sdkProcess(event);
    }
    </#list>
}