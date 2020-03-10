<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    Add new user
    <br>
    ${message?ifExists}
    <@l.login "/registration"/>
    <div>
        <a href="/login">Back to login</a>
    </div>
</@c.page>