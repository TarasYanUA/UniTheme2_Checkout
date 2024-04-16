package hooks;

import org.assertj.core.api.SoftAssertions;

public class CollectAssertMessages {

    private static final ThreadLocal<SoftAssertions> threadLocal = new ThreadLocal<>();

    public static SoftAssertions getSoftAssertions() {
        return threadLocal.get();
    }

    public static void setSoftAssertions(SoftAssertions softAssertions) {
        threadLocal.set(softAssertions);
    }
}