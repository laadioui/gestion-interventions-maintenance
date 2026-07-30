package com.maintenx.validation;
import com.maintenx.exception.ValidationException;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
class InputValidatorTest {
    @Test void emailValide() { assertDoesNotThrow(() -> InputValidator.email("user@example.com")); }
    @Test void emailInvalide() { assertThrows(ValidationException.class, () -> InputValidator.email("bad-email")); }
    @Test void motDePasseTropCourt() { assertThrows(ValidationException.class, () -> InputValidator.password("short")); }
    @Test void montantNegatif() { assertThrows(ValidationException.class, () -> InputValidator.nonNegative(BigDecimal.valueOf(-1), "coût")); }
    @Test void dateFinAvantDebut() { assertThrows(ValidationException.class, () -> InputValidator.chronological(LocalDateTime.now(), LocalDateTime.now().minusDays(1))); }
    @Test void champObligatoireVide() { assertThrows(ValidationException.class, () -> InputValidator.required(" ", "nom")); }
}
