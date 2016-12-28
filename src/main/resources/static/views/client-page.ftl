<!DOCTYPE html>
<#assign userRole = "${userRole}">
<#import "*/components/navbar.ftl" as navbarRenderer/>
<html>
<header>
 <#include "*/includes.ftl">
 <link rel="stylesheet" type="text/css" href="./css/client.css">
</header>
<body>
    <input id="userRole" type="hidden" class="hidden" value="${userRole}" />
    <@navbarRenderer.renderNavbar userRole="${userRole}" activePage="client"/>
    <#if userRole=="ADMIN" || userRole=="AGENT" >
    <div class="container">
        <div class="col-md-11">
            <div class="col-md-2 col-md-offset-0 h2 ">
                <a href="./createClient"><button type="submit" class="btn btn-primary btn-lg">Creeaza Client</button></a>
            </div>
            <div  class="col-md-9 h2 title">
                Clienti
            </div>
        </div>
        <div class="clients-table generic-table">
        <table id="clientTable" class="table table-striped">
            <thead>
            <th class="table-header text-center">Id</th>
            <th class="table-header text-center">AgentId</th>
            <th class="table-header text-center">Nume</th>
            <th class="table-header text-center">Email</th>
            <th class="table-header text-center">Agent</th>
            <th class="table-header text-center">Detalii</th>
            </thead>
        </table>
        </div>
    </div>
    </#if>
 <script src="script/clientTable.js"></script>
</body>
</html>