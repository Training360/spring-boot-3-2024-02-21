package training360.employeessb3clientdemo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange("/api/employees")
public interface EmployeeService {

    @GetExchange
    List<EmployeeResource> listEmployees();

    @PostExchange
    EmployeeResource createEmployee(EmployeeResource command);
}
