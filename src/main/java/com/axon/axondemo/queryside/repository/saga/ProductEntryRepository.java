package com.axon.axondemo.queryside.repository.saga;
import com.axon.axondemo.queryside.entity.ProductEntry;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductEntryRepository extends PagingAndSortingRepository<ProductEntry,String>{
        //PagingAndSortingRepository<ProductEntry, String> {
}
