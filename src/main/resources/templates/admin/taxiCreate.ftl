<#import "../parts/common.ftl" as c>

<@c.page>
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div>${prp.getMessage("text.type")}</div>
            <div class="form-group">
                <select name="type">
                    <#list types as type>
                        <option value=${type}>${type.name()}</option>
                    </#list>
                </select>
            </div>
            <div>${prp.getMessage("text.number")}</div>
            <div class="form-group">
                <input type="text" class="form-control" name="number">
            </div>
            <div>${prp.getMessage("text.driverName")}</div>
            <div class="form-group">
                <input type="text" class="form-control" name="driverName">
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">${prp.getMessage("text.save")}</button>
            </div>
        </form>
    </div>
</@c.page>