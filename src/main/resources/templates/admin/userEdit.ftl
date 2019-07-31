<#import "../parts/common.ftl" as c>

<@c.page>
    User editor

    <form action="/user" method="post">
        <input type="text" name="username" value="${user.username}">

        <select name="role">
            <option value="Admin">${prp.getMessage("text.admin")}</option>
            <option value="Client">${prp.getMessage("text.client")}</option>
        </select>
        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button type="submit">Save</button>
    </form>
    <form method="post">
        <input type="number" pattern="0.00" min="0.01" max="0.99" step=".01" name="percent">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button type="submit">Add</button>
    </form>
</@c.page>
