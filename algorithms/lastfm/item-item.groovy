import edu.boisestate.piret.demo.LogVectorNormalizer
import org.grouplens.lenskit.transform.threshold.RealThreshold
import org.grouplens.lenskit.transform.threshold.Threshold
import org.lenskit.api.ItemScorer
import org.lenskit.api.RatingPredictor
import org.lenskit.basic.SimpleCachingItemScorer
import org.lenskit.data.entities.EntityType
import org.lenskit.data.ratings.CountSumRatingVectorPDAO
import org.lenskit.data.ratings.EntityCountRatingVectorPDAO
import org.lenskit.data.ratings.InteractionEntityType
import org.lenskit.data.ratings.RatingVectorPDAO
import org.lenskit.knn.NeighborhoodSize
import org.lenskit.knn.item.ItemItemScorer
import org.lenskit.knn.item.ItemSimilarityThreshold
import org.lenskit.knn.item.ModelSize
import org.lenskit.knn.item.NeighborhoodScorer
import org.lenskit.knn.item.SimilaritySumNeighborhoodScorer
import org.lenskit.transform.normalize.UserVectorNormalizer
import org.lenskit.transform.normalize.VectorNormalizer

bind ItemScorer to SimpleCachingItemScorer
within (SimpleCachingItemScorer) {
    bind ItemScorer to ItemItemScorer
}
bind NeighborhoodScorer to SimilaritySumNeighborhoodScorer
set InteractionEntityType to EntityType.forName("artist-count")

bind (ItemSimilarityThreshold, Threshold) to RealThreshold

bind RatingPredictor to null

set NeighborhoodSize to 20
set ModelSize to 10000

algorithm("II-C") {
    bind RatingVectorPDAO to CountSumRatingVectorPDAO
    within (UserVectorNormalizer) {
        bind VectorNormalizer to LogVectorNormalizer
    }
}

algorithm("II-B") {
    bind RatingVectorPDAO to EntityCountRatingVectorPDAO
}
