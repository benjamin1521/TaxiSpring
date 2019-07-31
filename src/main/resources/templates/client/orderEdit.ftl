<#import "../parts/common.ftl" as c>

<@c.page>


    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control"
                       name="comment" placeholder="comment"/>
            </div>
            <div>${prp.getMessage("text.name")}</div>
            <div class="form-group">
                <input type="text" class="form-control"
                        <#--value="<#if order??>${order.name}</#if>" name="name" placeholder="name">-->
                >
            </div>


            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <#--<input type="hidden" name="id" value="<#if order??>${order.id}</#if>"/>-->
            <div class="form-group">
                <button type="submit" class="btn btn-primary">${prp.getMessage("text.save")}</button>
            </div>
        </form>
    </div>


</@c.page>