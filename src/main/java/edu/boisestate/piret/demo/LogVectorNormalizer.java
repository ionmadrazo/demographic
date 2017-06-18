package edu.boisestate.piret.demo;

import it.unimi.dsi.fastutil.longs.Long2DoubleMap;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.function.Log10;
import org.apache.commons.math3.analysis.function.Pow;
import org.lenskit.transform.normalize.VectorNormalizer;
import org.lenskit.util.InvertibleFunction;
import org.lenskit.util.math.Vectors;

import javax.annotation.Nullable;

/**
 * Take the log of each element of a vector.
 */
public class LogVectorNormalizer implements VectorNormalizer {
    @Override
    public InvertibleFunction<Long2DoubleMap, Long2DoubleMap> makeTransformation(Long2DoubleMap reference) {
        return new LogTransform();
    }

    private static class LogTransform implements InvertibleFunction<Long2DoubleMap, Long2DoubleMap> {
        @Override
        public Long2DoubleMap unapply(Long2DoubleMap input) {
            return Vectors.transform(input, FunctionUtils.fix1stArgument(new Pow(), 10));
        }

        @Nullable
        @Override
        public Long2DoubleMap apply(@Nullable Long2DoubleMap input) {
            return Vectors.transform(input, new Log10());
        }
    }
}
