<!DOCTYPE html>
<#assign userRole = "${userRole}">
<#import "*/components/navbar.ftl" as navbarRenderer/>
<#import "*/components/client-form.ftl" as clientFormRender/>
<head>
<#include "*/includes.ftl">
</head>
<body>
<input id="userRole" type="hidden" value="${userRole}">
<@navbarRenderer.renderNavbar userRole="${userRole}" activePage="client"/>
<#if userRole=="ADMIN" || userRole=="AGENT">
<div class="container">
    <div class="col-md-12 col-xs-12">
        <@clientFormRender.renderForm "Editare Client" userRole agents client />
    </div>
</div>
</#if>
</body>
<html>