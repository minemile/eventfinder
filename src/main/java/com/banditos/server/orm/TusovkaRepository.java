package com.banditos.server.orm;

import com.banditos.server.model.Tusovka;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface TusovkaRepository extends CrudRepository<Tusovka, Long> {

    Tusovka findByPlaceOrderByDateAsc(String place);

}
