package com.maintenx.util;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class PasswordHasherTest {
    @Test void hashNonClairEtVerifiable() { var hash = PasswordHasher.hash("Secret123!"); assertNotEquals("Secret123!", hash); assertTrue(PasswordHasher.verify("Secret123!", hash)); }
}
