<#macro login path>
    <form action="${path}" method="post">
        <div><label> User Name : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <#if "${path}" == "/registration">
            <div><label> Email: <input type="email" name="email" placeholder="some@some.com"/></label></div>
            <div>
                <#list roles as role>
                    <label for="">
                        <input type="checkbox" name="${role}" ${(roles)?seq_contains(role)?string("checked", "")}>
                        ${role}
                    </label>
                    <br>
                </#list>
            </div>
        </#if>

        <div><input type="submit" value="Sign In"/></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="submit" value="Sign Out"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</#macro>