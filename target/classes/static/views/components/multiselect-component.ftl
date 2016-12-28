<#macro renderMultiSelect data>
 <div class="col-xs-5">
        <select name="from[]" id="multiselect" class="form-control" size="8" multiple="multiple">
            <#if data?has_content>
                <#list data as item>
                   <option  value="${item.id}" name="id"> ${item.name}</option>
                </#list>
             </#if>
        </select>
    </div>

    <div class="col-xs-2" style="margin-top: 34px">
        <button type="button" id="multiselect_rightAll" class="btn btn-block"><i class="glyphicon glyphicon-forward"></i></button>
        <button type="button" id="multiselect_rightSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-right"></i></button>
        <button type="button" id="multiselect_leftSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-left"></i></button>
        <button type="button" id="multiselect_leftAll" class="btn btn-block"><i class="glyphicon glyphicon-backward"></i></button>
    </div>

    <div class="col-xs-5">
        <select name="to[]" id="multiselect_to" class="form-control" size="8" multiple="multiple"></select>
 </div>
</#macro>