package com.github.ilyasyoy.demoarchunit;

import com.github.ilyasyoy.demoarchunit.annotation.AllowStatic;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

@AnalyzeClasses(packagesOf = DemoArchUnitApplication.class)
public final class ArchUnitTests {


    @ArchTest
    static final ArchRule no_field_injection =
            ArchRuleDefinition.noFields()
                    .should()
                    .beAnnotatedWith(Autowired.class)
                    .because("You can do better! Try to do @Bean!");


    @ArchTest
    static final ArchRule built_in_field_injection_check = NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

    @ArchTest
    static final ArchRule no_static_methods = ArchRuleDefinition.noMethods()
            .that()
            .areDeclaredInClassesThat()
            .areNotAnnotatedWith(SpringBootApplication.class)
            .and()
            .areDeclaredInClassesThat()
            .areNotAnnotatedWith(AllowStatic.class)
            .should()
            .beStatic();

    @ArchTest
    static final ArchRule no_impl_class = ArchRuleDefinition.noClasses()
            .should()
            .haveSimpleNameEndingWith("Impl");
}
