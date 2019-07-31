<#import "../parts/common.ftl" as c>

<@c.page>
    ${prp.getMessage("text.userlist")}

    <table>
        <thead>
        <tr>
            <th>${prp.getMessage("text.username")}</th>
            <th>${prp.getMessage("text.role")}</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td>${user.role}</td>
                <td><a href="/user/${user.id}">${prp.getMessage("text.edit")}</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>
