<html>
<head>
<#include "*/includes.ftl">
<#import "*/components/navbar.ftl" as navbarRenderer/>
<#import "*/components/client-form.ftl" as clientFormRenderer />
</head>

<body>
<input id="userRole" type="hidden" value="${userRole!'sdasd'}" />
<@navbarRenderer.renderNavbar userRole "client" />
<#if userRole=="ADMIN" || userRole=="AGENT">
<div class="container">
    <div class="col-md-9 col-xs-7">
        <#if error??>
             <div class="alert alert-danger margin-top" role="alert">Adresa de mail este folosita.Va rugam introduceti o adresa valida.</div>
        </#if>
        <@clientFormRenderer.renderForm "Creeaza Client" userRole agents ""/>
    </div>
</div>
</#if>
</body>
</html>