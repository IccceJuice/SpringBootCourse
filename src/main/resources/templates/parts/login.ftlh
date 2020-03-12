<#macro login path>
    <form action="${path}" method="post">
        <div><label> Username : <input type="text" name="username" value="<#if user??>${user.username}</#if>"
                                       class="${(usernameError??)?string('is-invalid', '')}"
                                       placeholder="Enter a username"/> </label></div>
        <#if usernameError??>
            <div class="invalid-feedback">
                ${usernameError}
            </div>
        </#if>
        <div><label> Password: <input type="password" name="password" class="${(passwordError??)?string('is-invalid', '')}"
                                      placeholder="Enter a password"/> </label></div>
        <#if passwordError??>
            <div class="invalid-feedback">
                ${passwordError}
            </div>
        </#if>
        <#if "${path}" == "/registration">
            <div><label> Retype password: <input type="password" name="password2" class="${(password2Error??)?string('is-invalid', '')}"
                                                 placeholder="Retype password"/></label></div>
            <#if password2Error??>
                <div class="invalid-feedback">
                    ${password2Error}
                </div>
            </#if>
            <div><label> Email: <input type="email" name="email" value="<#if user??>${user.email}</#if>"
                                       class="${(emailError??)?string('is-invalid', '')}"
                                       placeholder="some@some.com"/></label></div>
            <#if emailError??>
                <div class="invalid-feedback">
                    ${emailError}
                </div>
            </#if>
            <div>
                <#if roles??>
                    <#list roles as role>
                        <label for="">
                            <input type="checkbox" name="${role}" ${(roles)?seq_contains(role)?string("checked", "")}>
                            ${role}
                        </label>
                        <br>
                    </#list>
                    <#else >

                </#if>
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