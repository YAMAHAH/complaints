package com.axon.axondemo.queryside.repository.saga;

import com.axon.axondemo.queryside.entity.OrderEntry;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "orders", path = "orders")
public interface OrderEntryRepository extends PagingAndSortingRepository<OrderEntry,String>{
        //PagingAndSortingRepository<OrderEntry, String> {
}