package esprit.tunisiacamp.repositeries;

import esprit.tunisiacamp.entities.shopping.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepositery extends CrudRepository<Transaction,Long> {
}
