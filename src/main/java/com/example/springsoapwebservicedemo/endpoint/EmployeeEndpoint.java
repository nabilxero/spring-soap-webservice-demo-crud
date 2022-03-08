package com.example.springsoapwebservicedemo.endpoint;

import com.example.springsoapwebservicedemo.jaxb.employeeinfo.*;
import com.example.springsoapwebservicedemo.model.Employee;
import com.example.springsoapwebservicedemo.service.impl.EmployeeServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class EmployeeEndpoint {

    private static final String NAMESPACE_URI = "com/example/springsoapwebservicedemo/jaxb/employeeInfo";

    private final EmployeeServiceImpl service;

    public EmployeeEndpoint(EmployeeServiceImpl service) {
        this.service = service;
    }

    @PayloadRoot(namespace = NAMESPACE_URI,localPart = "addEmployeeRequest")
    @ResponsePayload
    public AddEmployeeResponse addEmployee(@RequestPayload AddEmployeeRequest request) {
        AddEmployeeResponse response = new AddEmployeeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        Employee employee = new Employee();
        BeanUtils.copyProperties(request.getEmployeeInfo(), employee);
        service.addEmployee(employee);
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Added Successfully");
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeByIdRequest")
    @ResponsePayload
    public GetEmployeeResponse getEmployee(@RequestPayload GetEmployeeByIdRequest request) {
        GetEmployeeResponse response = new GetEmployeeResponse();
        EmployeeInfo employeeInfo = new EmployeeInfo();
        BeanUtils.copyProperties(service.getEmployeeById(request.getEmployeeId()), employeeInfo);
        response.setEmployeeInfo(employeeInfo);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEmployeeRequest")
    @ResponsePayload
    public UpdateEmployeeResponse updateEmployee(@RequestPayload UpdateEmployeeRequest request) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(request.getEmployeeInfo(), employee);
        service.updateEmployee(employee);
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Updated Successfully");
        UpdateEmployeeResponse response = new UpdateEmployeeResponse();
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEmployeeRequest")
    @ResponsePayload
    public DeleteEmployeeResponse deleteEmployee(@RequestPayload DeleteEmployeeRequest request) {
        service.deleteEmployee(request.getEmployeeId());
        ServiceStatus serviceStatus = new ServiceStatus();

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Deleted Successfully");
        DeleteEmployeeResponse response = new DeleteEmployeeResponse();
        response.setServiceStatus(serviceStatus);
        return response;
    }
}
