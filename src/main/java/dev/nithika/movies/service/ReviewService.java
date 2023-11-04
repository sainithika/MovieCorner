package dev.nithika.movies.service;

import dev.nithika.movies.Movie;
import dev.nithika.movies.repo.ReviewRepository;
import dev.nithika.movies.Reviews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    public Reviews createReviewBy(String reviewBody, String imdbId){
        Reviews reviews = reviewRepository.insert(new Reviews(reviewBody));
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(reviews))
                .first();
        return reviews;
    }
}