<#import "parts/common.ftlh" as c>
<@c.page>
    User editor
    <form action="/user" method="post">
        <input type="hidden" value="${user.id}" name="userID">
        <input type="text" value="${user.username}" name="username">
        <br>
        <#list roles as role>
            <label for="">
                <input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>
                ${role}
            </label>
            <br>
        </#list>
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button type="submit">Save</button>
    </form>
</@c.page>

