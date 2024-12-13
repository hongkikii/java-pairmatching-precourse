package pairmatching;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PairInfoTest {
    @DisplayName("같은 레벨에서 이전에 매칭되었던 사람과 또 매칭될 시, 재매칭을 3회까지 시도한다.")
    @Test
    void 재매칭_테스트() {
        // Given
        MockTwoPairInfo mockTwoPairInfo = new MockTwoPairInfo();
        MockThreePairInfo mockThreePairInfo = new MockThreePairInfo();

        // When
        Section section = new Section(Course.BACKEND, Level.LEVEL1, Mission.BASEBALL);

        // Then
        Assertions.assertThatThrownBy(() -> mockTwoPairInfo.match(section))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 매칭에 실패했습니다.");
        assertEquals(mockTwoPairInfo.tryCount, 3);

        Assertions.assertThatThrownBy(() -> mockThreePairInfo.match(section))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 매칭에 실패했습니다.");
        assertEquals(mockThreePairInfo.tryCount, 3);
    }
}
