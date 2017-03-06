package io.muic.ooc.webapp.repositories;

import io.muic.ooc.webapp.entities.Seller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by joakimnilfjord on 3/5/2017 AD.
 */
@Repository
public interface SellerRepository extends CrudRepository<Seller, Long>{
    List<Seller> findByUsername(String username);

}
