package io.muic.ooc.repository;

/**
 * Created by joakimnilfjord on 3/10/2017 AD.
 */
import io.muic.ooc.model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
