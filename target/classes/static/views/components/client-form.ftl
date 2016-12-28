<#macro renderForm titlePage userRole agents="" client="">
<div id ="client-details">
    <form class="create-form form-horizontal" role="form" action="./createClient" method="POST" enctype="multipart/form-data">
        <#if client?has_content>
            <input type="hidden" name="id" value="${client.id}"/>
        </#if>
        <div class="form-group">
            <h2>${titlePage}</h2>
        </div>

        <div class="form-group">
            <div class="col-md-3 col-xs-3">
                <label for="client-mail" class="control-label">Email:</label>
            </div>
            <div class="col-md-6 col-xs-6">
                <#if client?has_content>
                    <input type="email" class="form-control" id="client-mail" value="${client.mail!''}" name="mail" placeholder="Introduceti adresa de email a clientului."  oninput="ErrorMessages(this)" oninvalid="ErrorMessages(this)" required>
                <#else>
                    <input type="email" class="form-control" id="client-mail" name="mail" placeholder="Introduceti adresa de email a clientului."  oninput="ErrorMessages(this)" oninvalid="ErrorMessages(this)" required>
                </#if>
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-3 col-xs-3">
                <label for="client-name" class="control-label">Name:</label>
            </div>
            <div class="col-md-6 col-xs-6">
                <#if client?has_content>
                    <input type="text" class="form-control" id="client-name" value="${client.name!''}" name="name" placeholder="Introduceti numele clientului."  oninput="ErrorMessages(this)" oninvalid="ErrorMessages(this)" required>
                <#else>
                    <input type="text" class="form-control" id="client-name"  name="name" placeholder="Introduceti numele clientului."  oninput="ErrorMessages(this)" oninvalid="ErrorMessages(this)" required>
                </#if>
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-3 col-xs-3">
                <label for="client-address" class="control-label">Adresa:</label>
            </div>
            <div class="col-md-6 col-xs-6">
                <#if client?has_content>
                    <input type="text" class="form-control" id="client-address" value="${client.address!''}" name="address" placeholder="Introduceti adresa clientului."  oninput="ErrorMessages(this)" oninvalid="ErrorMessages(this)" required>
                <#else>
                    <input type="text" class="form-control" id="client-address"  name="address" placeholder="Introduceti adresa clientului." oninput="ErrorMessages(this)" oninvalid="ErrorMessages(this)" required>
                </#if>
            </div>
        </div>

        <div class="form-group">
            <div class="col-md-3 col-xs-3">
                <label for="client-orderLimit" class="control-label">Limita de comanda:</label>
            </div>
            <div class="col-md-6 col-xs-6">
                <#if client?has_content>
                    <input type="number" class="form-control" id="client-orderLimit" value="${client.orderLimit}" name="orderLimit" placeholder="Introduceti limita client"  oninput="ErrorMessages(this)" oninvalid="ErrorMessages(this)" required>
                <#else>
                    <input type="number" class="form-control" id="client-orderLimit"  name="orderLimit" placeholder="Introduceti limita client" oninput="ErrorMessages(this)" oninvalid="ErrorMessages(this)" required>
                </#if>
            </div>
        </div>

      <#if userRole != "AGENT" >
        <div class="form-group">
            <div class="col-md-3 col-xs-3">
                <label for="client-agent" class="control-label">Agent:</label>
            </div>
            <div class="col-md-6 col-xs-6">
                <select id="category-dropdown" name="agent" class="form-control">
                    <#if agents?has_content>
                        <#list agents as agent>
                            <#if client?has_content>
                                <option  value="${agent.id}" name="id" ${(client.agent.id == agent.id)?string('selected','')} > ${agent.name}</option>
                            <#else>
                                <option  value="${agent.id}" name="id"> ${agent.name}</option>
                            </#if>
                        </#list>
                    </#if>
                </select>
            </div>
        </div>
      </#if>

        <div class="form-group">
            <div class="col-md-4 col-xs-4">
                <button class="btn btn-default btn-save" type="submit">Salveaza</button>
                <a href="/clients"><button class="btn btn-default" type="button" id="cancel-btn">Anuleaza</button></a>
            </div>
        </div>
    </form>
</div>
</#macro>