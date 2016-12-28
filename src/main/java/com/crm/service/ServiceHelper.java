package com.crm.service;

import com.crm.util.DataTableRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * Created by Dragos on 4/16/2016.
 */
public class ServiceHelper {

    public static PageRequest getPageRequest(DataTableRequest dataTableRequest) {
        int columnIndex = Integer.parseInt(dataTableRequest.getOrder().get(0).get("column"));
        String columnName = dataTableRequest.getColumns().get(columnIndex).get("name");
        Sort.Direction direction = dataTableRequest.getOrder().get(0).get("dir").equals("asc") ? Sort.Direction.ASC :
                Sort.Direction.DESC;

        PageRequest request = new PageRequest(dataTableRequest.getStart() / dataTableRequest.getLength(), dataTableRequest.getLength(), direction, columnName);
        return request;
    }
}
