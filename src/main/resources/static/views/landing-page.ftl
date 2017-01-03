<!DOCTYPE html>
<#assign userRole = "${userRole}">
<#import "*/components/navbar.ftl" as navbarRenderer />
<#import "*/components/multiselect-component.ftl" as multiselect />
<html>
<head>
<#include "*/includes.ftl">
    <link rel="stylesheet" type="text/css" href="css/home.css" />
    <link rel="stylesheet" type="text/css" href="libs/summernote/dist/summernote.css">
    <link rel="stylesheet" type="text/css" href="css/mediaQueries/home-media-queries.css" />

</head>
<body>
<input id="userRole" type="hidden" class="hidden" value="${userRole}" />
<@navbarRenderer.renderNavbar userRole="${userRole}" activePage="home" userHasProjects=userHasProjects />
<#if userRole == 'ADMIN'>
<input id="activePage" type="hidden" class="hidden" value="${activePage}" />
<div class="container">
   <!-- <div class="row margin-top">
        <div class="col-md-12 col-xs-12" >
          <ul class="nav nav-tabs nav-justified">
              <li role="presentation" ${(activePage =="client")?string('class="active"','')}><a href="./home">Clienti</a></li>
              <li role="presentation" ${(activePage =="warehouse")?string('class="active"','')}><a href="./home?type=warehouse">Depozite</a></li>
              <li role="presentation" ${(activePage =="agent")?string('class="active"','')}><a href="./home?type=agent">Agenti</a></li>
          </ul>
        </div>
    </div>
    <div class="row margin-top">
         <@multiselect.renderMultiSelect data/>
    </div>
    <div class="row margin-top">
        <div class="col-md-12 col-xs-12" >
            <div id="error-user-not-selected" class="alert alert-danger" role="alert" style="display:none">Nu ati selectat nici un utilizator.</div>
            <div id="error-empty-text" class="alert alert-danger" role="alert" style="display:none">Nu ati introdus nici un text.</div>
        </div>
    </div> -->
    <div class="row margin-top">
        <div class="col-md-12 col-xs-12" >
            <div class="roleBox">
                <div id="summernote"></div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 col-xs-12" >
           <button id="save-btn" type="button" class="btn btn-info btn-lg save-btn">Salveaza</button>
     </div>
    <div class="row">
        <div id="wygwys-content" >

        </div>
    </div>
</div>
</div>
<#else>
<div class="container">
    <div class="row">
            <#if content?has_content>
                 <#list content as item>
                     <#if item??>
                         <div class="col-md-12 col-xs-12" >
                             ${item}
                         </div>
                     </#if>
                 </#list>
            <#else>
                Momentat nu este nici o informatie!
            </#if>
    </div>
</div>
</#if>
<#if errors?? && errors?has_content>
    <@errorBox.renderErrors errors />
</#if>
<script src="script/home.js"></script>
<script src="libs/summernote/dist/summernote.js"></script>
<script src="libs/multiselect-two-sides/dist/js/multiselect.min.js"></script>
<script src="libs/summernote/lang/summernote-ro-RO.js"></script>

</body>
</html>