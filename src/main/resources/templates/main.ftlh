<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div>
        <@l.logout/>
    </div>
    <div>
        <form action="add" method="post">
            <input type="text" name="text" placeholder="Enter a text">
            <input type="text" name="tag" placeholder="Enter a tag">
            <button type="submit">Enter</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
    </div>
    <br>
    <div>
        <form action="/main" method="get">
            <input type="text" name="findedTag">
            <button type="submit">Find</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
    </div>
    <div>List of messages</div>
    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>${message.tag}</i>
            <strong>${message.authorName}</strong>
        </div>
     </#list>
</@c.page>