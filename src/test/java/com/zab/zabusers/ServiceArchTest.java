package com.zab.zabusers;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.properties.CanBeAnnotated;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.core.domain.properties.CanBeAnnotated.Predicates.annotatedWith;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.zab.zabusers")
class ServiceArchTest {

    private static final DescribedPredicate<JavaClass> inDomainPackage = resideInAPackage("..domain..");
    private static final DescribedPredicate<CanBeAnnotated> annotatedAsServices = annotatedWith(Service.class);

    @ArchTest
    public static final ArchRule servicesShouldHaveNamesEndingService =
            classes().that(annotatedAsServices)
                    .should().haveSimpleNameEndingWith("Service");
    @ArchTest
    public static final ArchRule servicesShouldResideInTheDomainPackage =
            classes().that(annotatedAsServices)
                    .should().resideInAPackage("..domain..");
    @ArchTest
    public static final ArchRule servicesShouldBeAnnotatedWithService =
            classes().that(inDomainPackage)
                    .and().haveSimpleNameEndingWith("Service")
                    .should().beAnnotatedWith(Service.class);
}