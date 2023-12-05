package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.camping.Review;
import esprit.tunisiacamp.repositories.ReviewRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements IReviewService{
    @Autowired
    private ReviewRepositories reviewRepositories;
    @Override
    public Review saveReview(Review review) {
        return reviewRepositories.save(review);
    }

    @Override
    public List<Review> getAllReview() {
        return (List<Review>) reviewRepositories.findAll();
    }

    @Override
    public void deleteReviewById(long id) {
        reviewRepositories.deleteById(id);
    }
}
