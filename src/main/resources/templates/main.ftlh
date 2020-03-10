<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div>
        <@l.logout/>
    </div>

    <span><a href="/user">User list</a></span>

    <div>
        <form action="/main" method="post" enctype="multipart/form-data">
            <input type="text" name="text" placeholder="Enter a text">
            <input type="text" name="tag" placeholder="Enter a tag">
            <input type="file" name="file">
            <button type="submit">Enter</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
    </div>

    <br>

    <div>
        <form action="/main" method="get">
            <input type="text" name="findedTag" value="${findedTag?ifExists}">
            <button type="submit">Найти</button>
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
        <div>
            <#if message.filename??>
                <img src="/imr/${message.filename}" alt="">
            </#if>
        </div>
        <#else>
        No message
     </#list>
</@c.page>