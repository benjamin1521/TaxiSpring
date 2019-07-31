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
            <div>${prp.getMessage("text.start")}</div>
            <div class="form-group">
                <select name="startStreet">
                    <#list streets as street>
                        <option value=${street.name()}>${street.name()}</option>
                    </#list>
                </select>
                <input name="startHouse" type="number" value="1" pattern="1" max="9" min="1" />
            </div>

            <div>${prp.getMessage("text.end")}</div>
            <div class="form-group">
                <select name="endStreet">
                    <#list streets as street>
                        <option value=${street.name()}>${street.name()}</option>
                    </#list>
                </select>
                <input name="endHouse" type="number" value="1" pattern="1" max="9" min="1" />
            </div>
            <div>${prp.getMessage("text.coupon")}</div>
            <div class="form-group">
                <select name="coupon">
                    <option value="0">${prp.getMessage("text.none")}</option>
                    <#list coupons as coupon>
                        <option value=${coupon.id}>
                            ${coupon.discountPercent}%
                        </option>
                    </#list>
                </select>

                <#if unavailable??>
                    <div >
                        ${unavailable}
                    </div>
                </#if>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">${prp.getMessage("text.save")}</button>
            </div>
        </form>
    </div>
</@c.page>