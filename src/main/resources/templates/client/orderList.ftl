<#include "../parts/security.ftl">
<#import "../parts/common.ftl" as c>

<@c.page>
    <div class="table-container">
        <table class="table">
            <thead>
            <tr>
                <th>${prp.getMessage("text.cost")}</th>
                <th>${prp.getMessage("text.distance")}</th>
                <th>${prp.getMessage("text.drivingTime")}</th>
                <th>${prp.getMessage("text.waitingTime")}</th>
                <th>${prp.getMessage("text.orderDate")}</th>
                <th>${prp.getMessage("text.type")}</th>
                <th>${prp.getMessage("text.taxi")}</th>
                <th>${prp.getMessage("text.start")}</th>
                <th>${prp.getMessage("text.end")}</th>
                <th>${prp.getMessage("text.status")}</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <#list orders as order>
                <tr class="${order.status}">
                    <td>${order.cost}$</td>
                    <td>${order.distance}</td>
                    <td>${order.drivingTime}</td>
                    <td>${order.waitingTime}</td>
                    <td>${order.orderDate}</td>
                    <td>${order.type}</td>
                    <td>${order.idTaxi.carNumber}</td>
                    <td>${order.startStreet}, ${order.startHouse}</td>
                    <td>${order.endStreet}, ${order.endHouse}</td>
                    <td>${order.status}</td>
                    <td>
                        <#--<a class="btn btn-primary" href="/orders/edit/${order.id}">-->
                        <#--${prp.getMessage("text.edit")}-->
                        <#--</a>-->
                        <#if order.status.name()=("Active")>
                            <a class="btn btn-primary" href="/orders/confirm/${order.id}">
                                ${prp.getMessage("text.confirm")}
                            </a>
                        </#if>
                        <a class="btn btn-primary" href="/orders/delete/${order.id}">
                            ${prp.getMessage("text.delete")}
                        </a>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>




<#--<div class="card-columns">-->
<#--<#list orders as order>-->
<#--<div class="card my-3">-->
<#--<div class="m-2">-->
<#--<span>${order.inspectorId.username}</span>-->
<#--<span>${order.name}</span><br/>-->
<#--<i>${order.status}</i>-->
<#--</div>-->
<#--<div class="card-footer text-muted">-->
<#--<a class="btn btn-primary" href="/orders/edit/${order.id}">-->
<#--${prp.getMessage("text.edit")}-->
<#--</a>-->
<#--<a class="btn btn-primary" href="/orders/mods/${order.id}">-->
<#--${prp.getMessage("text.mods")}-->
<#--</a>-->
<#--<a class="btn btn-primary" href="/orders/delete/${order.id}">-->
<#--${prp.getMessage("text.delete")}-->
<#--</a>-->
<#--<a class="btn btn-primary" href="/orders/change/${order.id}">-->
<#--${prp.getMessage("text.change")}-->
<#--</a>-->
<#--</div>-->
<#--</div>-->
<#--<#else>-->
<#--${prp.getMessage("text.noorders")}-->
<#--</#list>-->

<#--<div class="card my-3">-->
<#--<div class="m-2">-->
<#--<form method="post">-->
<#--<input type="text" class="form-control" name="name" placeholder="name">-->
<#--<input type="text" class="form-control" name="comment" placeholder="comment">-->
<#--<input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
<#--<div class="card-footer text-muted">-->
<#--<button type="submit" class="btn btn-primary">${prp.getMessage("text.create")}</button>-->
<#--</div>-->
<#--</form>-->
<#--</div>-->
<#--</div>-->


</@c.page>