<#import "parts/common.ftlh" as c>
<@c.page>
    <h5>${username}</h5>
    <form method="post">
        <div><label> User Name : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <div><label> Email: <input type="email" name="email" placeholder="some@some.com" /></label></div>
        <div><input type="submit" value="Save"/></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</@c.page>