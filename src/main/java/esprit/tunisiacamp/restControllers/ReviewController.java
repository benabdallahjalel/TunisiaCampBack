package esprit.tunisiacamp.restControllers;

import esprit.tunisiacamp.entities.camping.Review;
import esprit.tunisiacamp.services.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {
    @Autowired
    private IReviewService iReviewService;

    @PostMapping("/saveReview")
    public Review saveReview(@RequestBody Review review) {
        return iReviewService.saveReview(review);
    }

    @GetMapping("/getAllReview")
    public List<Review> getAllReview() {
        return iReviewService.getAllReview();
    }

    @DeleteMapping("deleteReview/{id}")
    public void deleteReviewById(@RequestParam long id) {
        iReviewService.deleteReviewById(id);
    }
}
