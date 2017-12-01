package com.mdstech.sample;

import io.prometheus.client.Counter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class CustomerService {
    private Counter customerAccessCount;
    private Map<Integer, Customer> customerMap = new ConcurrentHashMap<>();

    @PostConstruct
    private void initCounter() {
        customerAccessCount = Counter.build("total_customer_requests",
                "Total no of Customer resource end point access").register();
        customerMap.put(1, Customer.builder().id(1).name("test1").email("test1@test.org").build());
        customerMap.put(2, Customer.builder().id(2).name("test2").email("test2@test.org").build());
        customerMap.put(3, Customer.builder().id(3).name("test3").email("test3@test.org").build());
        customerMap.put(4, Customer.builder().id(4).name("test4").email("test4@test.org").build());
        customerMap.put(5, Customer.builder().id(5).name("test5").email("test5@test.org").build());
        customerMap.put(6, Customer.builder().id(6).name("test6").email("test6@test.org").build());
        customerMap.put(7, Customer.builder().id(7).name("test7").email("test7@test.org").build());
        customerMap.put(8, Customer.builder().id(8).name("test8").email("test8@test.org").build());
        customerMap.put(9, Customer.builder().id(9).name("test9").email("test9@test.org").build());
        customerMap.put(10, Customer.builder().id(10).name("test10").email("test10@test.org").build());
    }

    public Customer findById(Integer id) {
        customerAccessCount.inc();
        return Optional.ofNullable(customerMap.get(id)).orElse(Customer.builder().id(-1).name("N/A").email("na@test.org").build());
    }
}
